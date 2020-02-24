package helloworld;

import com.microsoft.azure.cognitiveservices.search.imagesearch.BingImageSearchAPI;
import com.microsoft.azure.cognitiveservices.search.imagesearch.BingImageSearchManager;
import com.microsoft.azure.cognitiveservices.search.imagesearch.models.ImageObject;
import com.microsoft.azure.cognitiveservices.search.imagesearch.models.ImagesModel;

/***
 * Used for searching images of manufactures
 */
public class ImageSearcher {
    private static ImageSearcher instance;
    static final String HOST = "https://4n6.cognitiveservices.azure.com";
    static final String PATH = "/bing/v7.0/images/search";

    private ImageSearcher(){}


    public static ImageSearcher getInstance() {
        if(instance==null){
            instance= new ImageSearcher();
        }
        return instance;
    }

    /**
     *
     * @param search name for what we are searching for
     * @return
     */
    public static String getFirstImageLink(String search)
    throws  Exception
    {
        final String SUBSCRIPTION_KEY ="1a9b7b7814844313a46ea9823c75c2f1";
                //System.getenv("BING_SEARCH_V7_SUBSCRIPTION_KEY");
        BingImageSearchAPI client = BingImageSearchManager.authenticate(SUBSCRIPTION_KEY);
        String searchTerm = search + " " + "logo";

        ImagesModel imageResults = client.bingImages().search()
                .withQuery(searchTerm)
                .withMarket("en-us")
                .execute();
        ImageObject firstImageResult;
        if (imageResults != null && imageResults.value().size() > 0) {
            // Image results
            firstImageResult = imageResults.value().get(0);
        }else{
            return "https://mustangnews.net/wp-content/uploads/2019/05/swallowpride.jpg";
        }
        return  firstImageResult.contentUrl();
    }
}
