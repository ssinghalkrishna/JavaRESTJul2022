package definitions;

import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.json.JSONArray;
import org.json.JSONObject;
import support.RestWrapper;
import support.TestContext;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.RestWrapper.ARTIST;
import static support.TestContext.*;

public class iTunesShareDataStepdefs {
    @Given("^I lookup an artist by iTunes artist Id$")
    public void iLookupAnArtistByITunesArtistId() throws FileNotFoundException, UnirestException {
        HashMap<String, String> artistDetails = getArtist();
        String id = artistDetails.get("id");

        JSONObject artist = new RestWrapper().getArtistById(id);

        assertThat(artist.get("resultCount").equals(1)).isTrue();

        JSONArray arr = (JSONArray) artist.get("results");

        JSONObject result = (JSONObject) arr.get(0);

        String actualArtistName = result.getString("artistName");

        setTestData("artistName", actualArtistName);
    }

    @And("^I verify that result contains that artist Id$")
    public void iVerifyThatResultContainsThatArtistId() throws FileNotFoundException {
        HashMap<String, String> artistDetails = getArtist();
        String name = artistDetails.get("name");

        String actualArtistName = getStringTestData("artistName");

        assertThat(actualArtistName.equals(name)).isTrue();
    }

    @Given("^I lookup an artist by iTunes artist Id another way$")
    public void iLookupAnArtistByITunesArtistIdAnotherWay() throws FileNotFoundException, UnirestException {
        HashMap<String, String> artistDetails = getArtist();
        String id = artistDetails.get("id");

        JSONObject artist = new RestWrapper().getArtistById(id);

        assertThat(artist.get("resultCount").equals(1)).isTrue();

        JSONArray arr = (JSONArray) artist.get("results");

        JSONObject result = (JSONObject) arr.get(0);

        TestContext.setTestData(ARTIST, result);
    }

    @And("^I verify that result contains that artist Id another way$")
    public void iVerifyThatResultContainsThatArtistIdAnotherWay() throws FileNotFoundException {
        HashMap<String, String> artistDetails = getArtist();
        String expectedName = artistDetails.get("name");

        JSONObject actualArtist = getJsonTestData(ARTIST);
        String actualName = actualArtist.getString("artistName");

        assertThat(expectedName.equals(actualName)).isTrue();
    }
}
