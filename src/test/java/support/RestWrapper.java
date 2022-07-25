package support;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;

public class RestWrapper {

    private String baseUrlLookup = "https://itunes.apple.com/lookup";
    private String baseUrlSearch = "https://itunes.apple.com/search";
    private final String CONTENT_TYPE = "Content-Type";
    private final String JSON = "application/json";

    public JSONObject getArtistById(String id) throws UnirestException {
        HttpRequest request = Unirest.get(baseUrlLookup)
                .queryString("id", id);

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject artistJson = response.getBody().getObject();

        System.out.println("Returned artist info: " + artistJson);
        return artistJson;
    }

    public JSONObject getArtistsByName(String name) throws UnirestException {
        String term = name.replace(' ', '+');

        HttpRequest request = Unirest.get(baseUrlSearch)
                .queryString("term", term);

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject artistsJson = response.getBody().getObject();
        System.out.println("Returned artists info: " + artistsJson);
        return artistsJson;
    }

    public JSONObject getArtistsByNameAndLimit(String name, String limit) throws UnirestException {
        String term = name.replace(' ', '+');

        HttpRequest request = Unirest.get(baseUrlSearch)
                .queryString("term", term)
                .queryString("limit", limit);

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject artistsJson = response.getBody().getObject();
        System.out.println("Returned artists info: " + artistsJson);
        return artistsJson;
    }

    public JSONObject getArtistsByNameAndEntity(String name, String entity) throws UnirestException {
        String term = name.replace(' ', '+');

        HttpRequest request = Unirest.get(baseUrlSearch)
                .queryString("term", term)
                .queryString("entity", entity);

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject artistsJson = response.getBody().getObject();
        System.out.println("Returned artists info: " + artistsJson);
        return artistsJson;
    }

    public JSONObject getArtistsByNameAndCountry(String name, String country) throws UnirestException {
        String term = name.replace(' ', '+');

        HttpRequest request = Unirest.get(baseUrlSearch)
                .queryString("term", term)
                .queryString("country", country);

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject artistsJson = response.getBody().getObject();
        System.out.println("Returned artists info: " + artistsJson);
        return artistsJson;
    }

    public JSONObject getArtistsByTitleAndCountry(String title, String country) throws UnirestException {
        HttpRequest request = Unirest.get(baseUrlSearch)
                .queryString("term", title)
                .queryString("country", country)
                .queryString("entity", "software");

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject artistsJson = response.getBody().getObject();
        System.out.println("Returned artists info: " + artistsJson);
        return artistsJson;
    }

}