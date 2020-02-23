package helloworld;

/***
 * Used for searching images of manufactures
 */
public class ImageSearcher {
    private static ImageSearcher instance;
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
    public static String getFirstImageLink(String search){
        ;
        return null;
    }
}
