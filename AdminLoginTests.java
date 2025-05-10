import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class AdminLoginTests {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    WebElement username;
    WebElement password;
    WebElement submit;

    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.get("https://automationintesting.online/admin");
        Thread.sleep(2000L);
        this.username = this.driver.findElement(By.id("username"));
        this.password = this.driver.findElement(By.id("password"));
        this.submit = this.driver.findElement(By.id("doLogin"));
    }

    @DataProvider(name = "admin-data")
    public Object[][] adminData() {
        return new Object[][]{
                {"admin", "password"},
                {"user", "pass"},
                {"", ""}
        };
    }

    @Test(dataProvider = "admin-data")
    public void loginTest(String user, String pass) throws InterruptedException {
        this.username.sendKeys(user);
        Thread.sleep(500);
        this.password.sendKeys(pass);
        Thread.sleep(500);
        this.submit.click();
        Thread.sleep(2000);
        if(driver.getCurrentUrl()=="https://automationintesting.online/admin/rooms"){
            test.pass("Login Success");
        }else {
            test.fail("Login fail");
        }
    }


    @AfterClass
    public void closeReport(){
        report.flush();
    }
    @AfterMethod
    public void finish() {
        this.driver.quit();
    }
}