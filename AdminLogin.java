import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

public class AdminLogin extends util{

    @BeforeMethod
    public ExtentReports getReportInstance() {
        if (extent == null) {
            reporter = new ExtentSparkReporter("test-output/Admin Login.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    @BeforeMethod
    public void elements() {
        driver.get(url+"admin");
        test_output = "Admin Login";
        report = getReportInstance();
        test = report.createTest("Admin Login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username = driver.findElement(By.id("username"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password = driver.findElement(By.id("password"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doLogin")));
        submit = driver.findElement(By.id("doLogin"));
    }

    @Test(dataProvider = "admin-data")
    public void loginTest(String user, String pass) {

        username.sendKeys(user);
        password.sendKeys(pass);
        submit.click();

        try {
            wait.until(ExpectedConditions.urlContains("rooms"));
            test.pass("Login test passed");
        }catch (TimeoutException e){
            test.fail("Login test failed");
        }
    }

}