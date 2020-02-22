package helloworld;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.tools.javac.util.Pair;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

public class S3Stream {
    private final String IMAGE_FOLDER = "images";
    private final String INFO_FOLDER = "info";

    private String bucketName;
    private AmazonS3Client client;

    public S3Stream(String bucketName){
        this.bucketName=bucketName;
        this.client=new AmazonS3Client();
    }
    public S3Stream(){
        this.client=new AmazonS3Client();
    }


    /**
     * Given a folder with bucket name get all the files in that folder
     * that are in the sub folder info and find the closes matching image
     * if one does not have the same name.
     *
     * @param folder
     * @return
     */
    public List<Pair<JSONPObject, String>> getZipStream(String folder) {
        ListObjectsV2Request req = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(folder);


        S3Object object = client.getObject(bucketName, zipFileName);
        InputStream contents = object.getObjectContent();
        return new ZipInputStream(contents);
    }

    /**
     * Determines if a file name is an image of not
     * @param fileName name to be determined if its image like
     * @return Matcher.matches() which is true if image like name otherwise false
     */
    private boolean isImage(String fileName){
        final String IMAGE_PATTERN = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$";
        Matcher matcher = Pattern.compile(IMAGE_PATTERN).matcher(fileName);
        return matcher.matches();
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
