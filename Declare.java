import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Declare {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports report;
    ExtentSparkReporter reporter;
    ExtentTest test;
    WebElement username;
    WebElement password;
    WebElement name;
    WebElement email;
    WebElement phone;
    WebElement subject;
    WebElement message;
    WebElement text;
    WebElement submit;
    WebElement fristname;
    WebElement lastname;
    WebElement reserv;
    WebElement Price;
    WebElement op;
    Select option;
    WebElement acc;
    Select accessible;
    WebElement wifibox;
    WebElement tvbox;
    WebElement delete;
    ExtentReports extent;
    String test_output;
    String url;
    String roomId;
}
