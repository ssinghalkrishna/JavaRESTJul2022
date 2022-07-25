package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.json.JSONArray;
import org.json.JSONObject;
import support.RestWrapper;

import static org.assertj.core.api.Assertions.assertThat;

public class ItunesStepdefs {
    @Given("^I go to url and search for artist by iTunes artist ID \"([^\"]*)\"$")
    public void iGoToUrlAndSearchForArtistByITunesArtistID(String artistId) throws Throwable {
        JSONObject artist = new RestWrapper().getArtistById(artistId);

        assertThat(artist.get("resultCount").equals(1)).isTrue();

        JSONArray arr = (JSONArray) artist.get("results");

        JSONObject result = (JSONObject) arr.get(0);

        assertThat(result.getString("artistName").equals("Jack Johnson")).isTrue();
    }

    @Given("^I go to url and search for all \"([^\"]*)\" audio and video content$")
    public void iGoToUrlAndSearchForAllAudioAndVideoContent(String name) throws Throwable {
        JSONObject artists = new RestWrapper().getArtistsByName(name);

        JSONArray arr = (JSONArray) artists.get("results");
    }

    @Given("^I go to url and search for all \"([^\"]*)\" audio and video content and return only the first \"([^\"]*)\" items$")
    public void iGoToUrlAndSearchForAllAudioAndVideoContentAndReturnOnlyTheFirstItems(String name, String count) throws Throwable {
        JSONObject artists = new RestWrapper().getArtistsByNameAndLimit(name, count);
        assertThat(artists.get("resultCount").equals(25)).isTrue();
    }

    @Given("^I search for only \"([^\"]*)\" of \"([^\"]*)\"$")
    public void iSearchForOnlyOf(String entity, String name) throws Throwable {
        if (entity.equalsIgnoreCase("music videos")) {
            entity = "musicVideo";
        }
        JSONObject artists = new RestWrapper().getArtistsByNameAndEntity(name, entity);

        int resultCount = (int) artists.get("resultCount");
        JSONArray arr = (JSONArray) artists.get("results");

        for (int i = 0; i < resultCount; i++) {
            JSONObject result = (JSONObject) arr.get(i);
            assertThat(result.getString("kind").equals("music-video")).isTrue();
        }
    }

    @Given("^I search for only \"([^\"]*)\" audio and video content and return only the results from the \"([^\"]*)\" iTunes Store$")
    public void iSearchForOnlyAudioAndVideoContentAndReturnOnlyTheResultsFromTheITunesStore(String name, String country) throws Throwable {
        if (country.equalsIgnoreCase("canada")) {
            country = "ca";
        }
        JSONObject artists = new RestWrapper().getArtistsByNameAndCountry(name, country);

        int resultCount = (int) artists.get("resultCount");
        System.out.println("Number of records returned: " + resultCount);

        JSONArray arr = (JSONArray) artists.get("results");

        for (int i = 0; i < resultCount; i++) {
            JSONObject result = (JSONObject) arr.get(i);
            assertThat(result.getString("country").equals("CAN")).isTrue();
        }
    }

    @Given("^I search for applications titled \"([^\"]*)\" and return only the results from the \"([^\"]*)\" iTunes Store$")
    public void iSearchForApplicationsTitledAndReturnOnlyTheResultsFromTheITunesStore(String title, String country) throws Throwable {
        if (country.equalsIgnoreCase("United States")) {
            country = "us";
        }
        JSONObject artists = new RestWrapper().getArtistsByTitleAndCountry(title, country);

        int resultCount = (int) artists.get("resultCount");
        System.out.println("Number of records returned: " + resultCount);

        JSONArray arr = (JSONArray) artists.get("results");

        for (int i = 0; i < resultCount; i++) {
            JSONObject result = (JSONObject) arr.get(i);
            assertThat(result.getString("currency").equals("USD")).isTrue();
            assertThat(result.getString("wrapperType").equals("software")).isTrue();
        }
    }

}


