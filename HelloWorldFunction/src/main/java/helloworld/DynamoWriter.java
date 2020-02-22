package helloworld;

import com.amazonaws.services.s3.event.S3EventNotification;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Singleton class that takes the files from the s3 put event
 * ands adds them as devices in dynamoDb formated as a Brand.
 *
 */
public class DynamoWriter {

    private static String bucketName;
    private DynamoWriter instance;
    private DynamoWriter(){}

    public DynamoWriter getInstance() {
        if (this.instance == null)
            this.instance= new DynamoWriter();
        return instance;
    }

    private void write(final S3EventNotification input) {
        try {

            List<S3EventNotification.S3Entity> folders = input.getRecords()
                    .stream().map(f -> f.getS3()).collect(Collectors.toList());


            BrandBuilder bb;

            // Step 1 - get bucket name
            bucketName=folders.get(0).getBucket().getName();

            /* Step 2 - go through each folder created (each contains two folders
                        1.images(.jpg)
                        2.info(jsons)

                        Each folder represents a brand
             */
            for (S3EventNotification.S3Entity folder : folders) {
                // key includes sub folders
                String key = folder.getObject().getKey();

                // Read from folder going through each device
                bb=new BrandBuilder()
                // Write Brand to


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }


}
