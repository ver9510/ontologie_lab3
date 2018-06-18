package ontologie_lab3.utils.jsonquery;

import ontologie_lab3.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MuseumQueries {
    private static final Logger logger = LogManager.getLogger(MuseumQueries.class.getName());

    private static final String BASE_OBJECT_URL = "http://www.vam.ac.uk/api/json/museumobject/";
    private static final String BASE_IMAGE_URL = "http://media.vam.ac.uk/media/thira/collection_images/";
    private static final String AND = "&";
    private static final String SEARCH = "search?";
    private static final String QUERY = "objectnamesearch=";
    private static final String WITH_IMAGES = "&images=1";
    private static final String PLACE = "placesearch=";
    private static final String BEFORE_YEAR = "before=";
    private static final String AFTER_YEAR = "after=";
    public static final String JPG = ".jpg";

    public List<MuseumObject> search(String word, String startDate, String endDate, String country, boolean hasImages) {
        String query = new StringBuilder()
                .append(BASE_OBJECT_URL)
                .append(SEARCH)
                .append(QUERY).append(word).append(AND)
                .append(PLACE).append(country).append(AND)
                .append(BEFORE_YEAR).append(endDate).append(AND)
                .append(AFTER_YEAR).append(startDate)
                .append(WITH_IMAGES).toString();
        List<MuseumObject> foundObjects = new ArrayList<>();
        String jsonString = JSON.readJSON(query);
        JSONObject response = new JSONObject(jsonString);
        JSONArray jsonArray = response.getJSONArray("records");
        int size = jsonArray.length();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("fields");
            String object_number = jsonObject.getString("object_number");
            MuseumObject museumObject = getObject(object_number);
            foundObjects.add(museumObject);
        }

        return foundObjects;
    }

    private MuseumObject createMuseumObjectFromJson(JSONObject jsonObject) {
        MuseumObject museumObject = new MuseumObject(
                jsonObject.getString("object"),
                jsonObject.getString("object_number"),
                jsonObject.getString("place"),
                jsonObject.getString("artist"),
                jsonObject.getString("date_text")
        );
        String imageId = jsonObject.getString("primary_image_id");
        museumObject.setImageUrl(getImageUrl(imageId));
        return museumObject;
    }


    public MuseumObject getObject(String objectId) {
        String query = BASE_OBJECT_URL + objectId;
        System.out.println(query);
        String jsonString = JSON.readJSON(query);
        JSONArray jsonArray = new JSONArray(jsonString);
        JSONObject jsonObject = jsonArray.getJSONObject(0).getJSONObject("fields");
        MuseumObject museumObject = createFullMuseumObjectFromJson(jsonObject);

        return museumObject;
    }

    private MuseumObject createFullMuseumObjectFromJson(JSONObject jsonObject) {
        MuseumObject museumObject = new MuseumObject(
                jsonObject.getString("object"),
                jsonObject.getString("object_number"),
                jsonObject.getString("collection_code"),
                jsonObject.getString("place"),
                jsonObject.getString("artist"),
                jsonObject.getString("year_start"),
                jsonObject.getString("year_end"),
                jsonObject.getString("descriptive_line"),
                jsonObject.getString("materials_techniques")
        );
        JSONArray image_set = jsonObject.getJSONArray("image_set");
        String imageId = Constants.NO_IMAGE_PATH;
        if (image_set.length() > 0) {
            imageId = image_set.getJSONObject(0).getJSONObject("fields").getString("image_id");
        }
        museumObject.setImageUrl(getImageUrl(imageId));
        return museumObject;
    }

    public String getImage(String imageId) {
        String url = getImageUrl(imageId);
        return null;
    }

    private String getImageUrl(String imageId) {
        return BASE_IMAGE_URL + imageId.substring(0, 6) + "/" + imageId + JPG;
    }

    public static void main(String[] args) {
        MuseumQueries museumQueries = new MuseumQueries();
        MuseumObject weddingDress = museumQueries.getObject("O167093");
        List<MuseumObject> foundObjects = museumQueries.search("dress", "1900", "1910", "England", true);

    }

}
