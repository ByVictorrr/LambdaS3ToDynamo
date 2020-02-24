package helloworld;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FilenameUtils;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class S3Stream {
    private final String IMAGE_FOLDER = "images";

    private static String bucketName;
    private static AmazonS3Client client;
    private static List<String> imageNames;

    public S3Stream(String bucketName){
        this.bucketName=bucketName;
        this.client=new AmazonS3Client();
        this.imageNames = getImageNames(bucketName);
    }
    public S3Stream(){
        this.client=new AmazonS3Client();
    }

    private List<String> getImageNames(String bucketName){
        String imageName;
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().
                withBucketName(bucketName).
                withPrefix("images/");

        List<String> commonPrefixes = client.listObjects(listObjectsRequest).
                getObjectSummaries().stream().
                map(s-> FilenameUtils.getName(s.getKey())) .
                collect(Collectors.toList());

        return commonPrefixes;
    }



    public List<String> readFolders(){
        // reads all folders in root dir of s3 bucket
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().
                withBucketName(bucketName).
                withDelimiter("/");

        ObjectListing objectListing = client.listObjects(listObjectsRequest);
        List<String> commonPrefixes = objectListing.getCommonPrefixes().
                stream().
                map(s->s.replace("/","")).
                collect(Collectors.toList());

        return commonPrefixes;
    }

    public List<String> readFiles(String folder) {
        String delimiter = "/";
        if (!folder.endsWith(delimiter)) {
            folder+= delimiter;
        }

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketName).withPrefix(folder)
                .withDelimiter(delimiter);

        ObjectListing objects = client.listObjects(listObjectsRequest);
        List<String> files = objects.getObjectSummaries().stream().map(s->s.getKey()).collect(Collectors.toList());

        return files;
    }
    public InputStream getFileContents(String key){

        InputStream is;
        S3Object object = client.getObject(bucketName, key);
        is=object.getObjectContent();
        return is;
    }
    public String findCloseImage(String fileName){
        // finds the image matching the filename or close
        String imageName;
        imageName = StringMatcher.getTheClosestMatch(imageNames, fileName);
        return client.getUrl(bucketName, "images/"+imageName).toExternalForm();

    }


    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
