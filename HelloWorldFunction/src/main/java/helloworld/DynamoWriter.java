package helloworld;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.gson.JsonObject;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Singleton class that takes the files from the s3 put event
 * ands adds them as devices in dynamoDb formated as a Brand.
 *
 */
public class DynamoWriter {

    private static DynamoWriter instance;
    private DynamoWriter(){}

    public static DynamoWriter getInstance() {
        if (instance == null)
            instance= new DynamoWriter();
        return instance;
    }

    /**
     * All the magic is in here
     * @param bucketName
     */
    public void write(String bucketName, String dynamoTable)
    throws Exception
    {
        S3Stream s3Stream = new S3Stream(bucketName);
        Device device;
        // step 1 - read bucket for all root folders names
        List<String> folders = s3Stream.readFolders();
        folders.remove("images"); // exclude images
        // step 2 - for every folder get each image and build
        for (String folder: folders){
            ManufacturerBuilder manufacturerBuilder = new ManufacturerBuilder(folder);
            List<String> files = s3Stream.readFiles(folder);
            // step 3 - get each input stream and form a Device object
            for (String key: files){
                // step 4 - Parse input stream to device(device builder)
                String file = FilenameUtils.getName(key);
                InputStream is = s3Stream.getFileContents(key);

                // try to find closest matching image
                String image = s3Stream.findCloseImage(file);

                device = DeviceFactory.getDevice(file, is, image);
                manufacturerBuilder.append(device);
            }
            // step 4- write manufacture builder to json object to dynamodb entry
            writeHelper(manufacturerBuilder.build(), dynamoTable);
            System.out.println(folder);

        }

    }

    // Writes to dyanmodb
    private void writeHelper(JSONObject manufacture, String DynamoTable){
        AmazonDynamoDB build = AmazonDynamoDBClient.builder().build();
        DynamoDB dynamoDB = new DynamoDB(build);
        Table table = dynamoDB.getTable(DynamoTable);
        Item item = Item.fromJSON(manufacture.toString());
        table.putItem(item);
    }




}
