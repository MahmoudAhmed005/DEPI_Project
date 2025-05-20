import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;

public class util extends Declare {

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
        url = "https://automationintesting.online/";
    }

    @DataProvider(name = "admin-data")
    public Object[][] adminData() {
        return new Object[][]{
                {"admin", "password"},
                {"user", "pass"},
                {"", ""}
        };
    }


    @DataProvider(name = "contact-data")
    public Object[][] contactData() {
        return new Object[][]{
            {"Mahmoud","xyz@x.z","00000000000","testing","test the contact us form"},
            {"Ahmed","xyz.x.com","00000000000","testing","test the contact us form"},
            {" "," "," "," "," "}
        };
    }

    @DataProvider(name = "CheckIn")
    public Object[][] CheckInData() {
        return new Object[][]{
                {"reservation/1?checkin=2025-05-20&checkout=2025-05-27","mahmoud","ahmed","xyz@x.z","00000000000"},
                {"reservation/1?checkin=2025-05-27&checkout=2025-05-27","mahmoud","ahmed","xyz@x.z","00000000000"},
                {"reservation/1?checkin=2025-05-20&checkout=2025-05-27"," "," "," "," "},
                {"reservation/1?checkin=2025-05-07&checkout=2025-05-11","mahmoud","ahmed","xyz@x.z","00000000000"}
        };
    }

    @DataProvider(name="Room")
    public Object[][] RoomData() {
        return new Object[][]{
                {"105","100"},
                {"106",""}
        };
    }

    @AfterMethod
    public void report() {
        report.flush();
    }

    @AfterMethod
    public void finish() {
        driver.quit();
    }
}