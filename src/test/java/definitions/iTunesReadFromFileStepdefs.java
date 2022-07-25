package definitions;

import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.en.Given;
import org.json.JSONArray;
import org.json.JSONObject;
import support.RestWrapper;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class iTunesReadFromFileStepdefs {
    @Given("^I search for artist by iTunes artist ID$")
    public void iSearchForArtistByITunesArtistID() throws FileNotFoundException, UnirestException {
        String fileName = "artist";
        HashMap<String, String > artistDetails = getData(fileName);
        String id = artistDetails.get("id");

        JSONObject artist = new RestWrapper().getArtistById(id);

        assertThat(artist.get("resultCount").equals(1)).isTrue();

        JSONArray arr = (JSONArray) artist.get("results");

        JSONObject result = (JSONObject) arr.get(0);

        assertThat(result.getString("artistName").equals("Jack Johnson")).isTrue();
    }

    @Given("^I search for all audio and video content by a name$")
    public void iSearchForAllAudioAndVideoContentByAName() throws FileNotFoundException, UnirestException {
        String fileName = "artist";
        HashMap<String, String > artistDetails = getData(fileName);
        String name = artistDetails.get("name");

        JSONObject artists = new RestWrapper().getArtistsByName(name);

        int resultCount = (int) artists.get("resultCount");
        System.out.println("Number of records returned: " + resultCount);

        JSONArray arr = (JSONArray) artists.get("results");

        int count = 0;
        for (int i = 0; i < resultCount; i++) {
            JSONObject result = (JSONObject) arr.get(i);

            if (result.getString("artistName").equals(name)){
                ++count;
              //  System.out.println(result);
            }
        }
System.out.println("Number of records with artist name " + name + " : " + count);
    }
}
