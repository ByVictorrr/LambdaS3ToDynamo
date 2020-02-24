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


        String image_link;
        // Step 1 - add name to brand jsn object
        this.manufacturer = new JSONObject();
        this.devices= new JSONArray();
        JSONArray attributes = new JSONArray();

        try {
            manufacturer.put("name", brandName);
            manufacturer.put("attributes", attributes);
            image_link=ImageSearcher.getFirstImageLink(brandName);
            attributes.put(0, image_link);
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


}
