package definitions;

import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import support.RestWrapper;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

import java.io.*;
import java.util.Scanner;

import static support.TestContext.*;

public class sunsetStepdefs {

    @Given("^I geocode the zip code to get its latitude and longitude$")
    public void iGeocodeTheZipCodeToGetItsLatitudeAndLongitude() throws FileNotFoundException, UnirestException {
        String fileName = "sunset";
        HashMap<String, String> sunsetDetails = getData(fileName);
        String zip = sunsetDetails.get("zip");

        JSONObject geoCode = new RestWrapper().getGeoCodeByZip(zip);
        JSONArray arr = (JSONArray) geoCode.get("results");

        JSONObject result = (JSONObject) arr.get(0);

        String longitude = String.valueOf(result.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
        String latitude = String.valueOf(result.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));

        setTestData("longitude", longitude);
        setTestData("latitude", latitude);
    }

    @Then("^I get the sunset time in UTC given the latitude and longitude$")
    public void iGetTheSunsetTimeInUTCGivenTheLatitudeAndLongitude() throws UnirestException, FileNotFoundException {
        String latitude = getStringTestData("latitude");
        String longitude = getStringTestData("longitude");

        String fileName = "sunset";
        HashMap<String, String> sunsetDetails = getData(fileName);
        String date = sunsetDetails.get("date");

        JSONObject sunriseSunset = new RestWrapper().getSunriseSunsetByLocationDate(latitude, longitude, date);

        JSONObject results = (JSONObject) sunriseSunset.get("results");
        String sunsetTime = results.getString("sunset");
        setTestData("sunsetTime", sunsetTime);
    }

    @Then("^I convert UTC to local time at the zip code$")
    public void iConvertUTCToLocalTimeAtTheZipCode() throws UnirestException, ParseException, UnsupportedEncodingException {
        String latitude = getStringTestData("latitude");
        String longitude = getStringTestData("longitude");
        String sunsetTime = getStringTestData("sunsetTime");

        String location = latitude + "," + longitude;
        System.out.println("location: " + location);
        System.out.println("sunsetTime: " + sunsetTime);

        ZonedDateTime zdt = ZonedDateTime.parse(sunsetTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        long timestampInSeconds = (zdt.toInstant().toEpochMilli())/1000;
        System.out.println("timestamp in seconds: " + timestampInSeconds);
        String timestamp = String.valueOf(timestampInSeconds);

        JSONObject localTimeJson = new RestWrapper().convertUTCTimeToTimeAtLocalZip(location, timestamp);
        System.out.println("localTimeJson: " + localTimeJson);

        int rawOffset = localTimeJson.getInt("rawOffset");
        int dstOffset = localTimeJson.getInt("dstOffset");

        long localTimeInSeconds = timestampInSeconds + rawOffset + dstOffset;
        System.out.println("localTimeInSeconds: " + localTimeInSeconds);

        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(localTimeInSeconds, 0, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mm a", Locale.ENGLISH);
        String formattedDate = dateTime.format(formatter);
        System.out.println("Sunset time at that location in local time: " + formattedDate);
    }

    @Given("^I read zip and date from a csv file$")
    public void iReadZipAndDateFromACsvFile() throws FileNotFoundException {
        //parsing a CSV file into Scanner class constructor
                Scanner sc = new Scanner(new File("src/test/resources/config/ziptime.csv"));
                sc.useDelimiter(",");   //sets the delimiter pattern
                while (sc.hasNext())  //returns a boolean value
                {
                    System.out.print(sc.next());  //find and returns the next complete token from this scanner
                }
                sc.close();  //closes the scanner
            }

}
