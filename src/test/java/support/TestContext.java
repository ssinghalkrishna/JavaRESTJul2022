package support;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static HashMap<String, Object> testData = new HashMap<>();

    public static void setTestData(String key, Object value) {
        testData.put(key, value);
    }

    public static String getStringTestData(String key) {
        return (String)testData.get(key);
    }

    public static JSONObject getJsonTestData(String key) {
        return (JSONObject)testData.get(key);
    }

    public static HashMap<String, String> getData(String fileName) throws FileNotFoundException {
        String path = System.getProperty("user.dir") + "/src/test/resources/config/" + fileName + ".yml";
        File sender = new File(path);
        InputStream stream = new FileInputStream(sender);
        Yaml yaml = new Yaml();
        return yaml.load(stream);
    }

    public static HashMap<String, String> getArtist() throws FileNotFoundException {
        return getData("artist");
    }

}
