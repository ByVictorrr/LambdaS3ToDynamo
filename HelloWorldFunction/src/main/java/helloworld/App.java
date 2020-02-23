package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import java.util.HashMap;
import java.util.Map;


/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<SNSEvent, Object> {

    private final String DYNAMODB_TABLE = "digitaln-mobilehub-2069871194-MobileBrands";

    public Object handleRequest(final SNSEvent input, final Context context) {
        DynamoWriter dynamoWriter = DynamoWriter.getInstance();
        try{
            String srcBucket = input.getRecords().get(0).getSNS().getMessage();
            dynamoWriter.write(srcBucket, DYNAMODB_TABLE);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        return new GatewayResponse("{}", headers, 200);
    }







}
