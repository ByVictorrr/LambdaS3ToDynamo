package helloworld;

import Models.MobileDO;
import Utility.Pair;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.event.S3EventNotification.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<S3EventNotification, Object> {

    private AmazonDynamoDB client = new AmazonDynamoDBClient();
    private String DYNAMODB_TABLE_NAME = "";


    public Object handleRequest(final S3EventNotification input, final Context context) {
        // Step 1 - turn input into a list of records
        // Step 2 - go through each taking the contents of it and parsing for it
        // Step 3 -


        return true;
    }






    //================================Dynamodb init=======================\\

}
