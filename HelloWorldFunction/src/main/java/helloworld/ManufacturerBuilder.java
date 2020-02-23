package helloworld;



import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Helps build a brand
 *
 */
public class ManufacturerBuilder {


    // The object it self
    private JSONObject manufacturer;
    private JSONArray devices;


    /**
     *  Builds a brand by brandName and variable arguments
     *  then it goes queries and looks for an image based on brandName
     *
     * @param brandName
     */

    public ManufacturerBuilder(String brandName){


        // Step 1 - add name to brand jsn object
        this.manufacturer = new JSONObject();
        this.devices= new JSONArray();
        JSONArray attributes = new JSONArray();

        try {
            manufacturer.put("name", brandName);
            manufacturer.put("attributes", attributes);
            attributes.put(0, "https://image_link");
            attributes.put(1,devices);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ManufacturerBuilder append(Device d){

        Gson gson = new Gson();
        String jsonInString = gson.toJson(d);
        try {
            devices.put(devices.length(), new JSONObject(jsonInString));
        }catch (Exception e){
            e.printStackTrace();
        }
       return this;
    }
    public JSONObject build(){
        return this.manufacturer;
    }
    /*
    private static String getBrandImage(String BrandName){
        String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String query = "java tutorial";
        String charset = "UTF-8";

        URL url = new URL(address + URLEncoder.encode(query, charset));

        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));
        String str;

        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }

        in.close();
    }

     */




}
