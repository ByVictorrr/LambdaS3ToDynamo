package helloworld;


/**
 * This class assumes that in the S3 bucket there is an image for every info file
 *
 */

public class DeviceFactory {

    private final String IMAGE_EXT = "jpg";
    /**
     * Given a very file of baseName go try to find its corresponding image.
     * If one does not exist then try to get the closest one
     *
     * This function will be invoked in BrandBuilder. Each brand has its own
     * folder in S3.
     *
     * @param fileName of the file to be searched for in folder images
     * @return a device
     */
    public Device getDevice(String fileName, String BucketName){
        Device device = new Device();

        // Step 1 - for a given fileName get the contents of it using S3Stream

        // Step 2 - After getting contents use json parser to parse important fields

        // Step 3 - find the corresponding image in the image directory or use a close file-named image
        device.setLink(s3Client.getUrl(AWS_BUCKET, s3RelativeFilePath).toExternalForm();

        // Step 4 - set the corresponding fields for


    }


    private String imageBuilder(String key, String bucketName) {
        //return "https://" + bucketName + ".s3.amazonaws.com/" + key;
        return "https://digitalforensics-userfiles-mobilehub-1666815180.s3.us-east-2.amazonaws.com/" + key;
    }

}
