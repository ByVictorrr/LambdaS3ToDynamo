package helloworld;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class assumes that in the S3 bucket there is an image for every info file
 *
 */

public class DeviceFactory {

    private final String IMAGE_EXT = "jpg";

    public static Device getDevice(String fileName, InputStream in, String image)
            throws Exception
    {

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        Device d= new Device();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject toParse = new JSONObject(responseStrBuilder.toString());

        DeviceParser deviceParser = new DeviceParser();
        // Get Devices field names as a string
        List<String> fields = Arrays.asList(Device.class.getDeclaredFields()).stream().
                map(f->f.getName()).
                filter(f->!f.equals("image")).
                collect(Collectors.toList());


        for(String field: fields){
            String found = deviceParser.find(toParse,field);
            d.setField(field, found);
        }

        // last set set image
        d.setImage(image);


        return d;
    }


    /**
     * Class used to help parse the incoming Input stream
     */
    static class DeviceParser {

        private DeviceParser instance;

        private DeviceParser() {
        }

        /**
         * Could return a JSONObject or string with its value being the key of input
         *
         * HARD CODEDED(come back)
         * @return
         */
        String find(JSONObject jObj, String k) throws Exception{
            ObjectNode node = new ObjectMapper().readValue(jObj.toString(), ObjectNode.class);
            String value;
            value=node.get(k).asText();
            return value;
        }


    }

}
