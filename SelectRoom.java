import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectRoom extends util {

    @BeforeMethod
    public ExtentReports getReportInstance() {
        if (extent == null) {
            reporter = new ExtentSparkReporter("test-output/Select A Room.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    @BeforeMethod
    public void Set() {
        driver.get(url);
        test_output = "Select A Room";
        report = getReportInstance();
        test = report.createTest("Select A Room");
    }

    @Test
    public void selectroom() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/reservation/4?checkin=2025-05-19&checkout=2025-05-20']")));
        submit = driver.findElement(By.xpath("//a[@href='/reservation/4?checkin=2025-05-19&checkout=2025-05-20']"));
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();
        if (driver.getCurrentUrl().contains("/reservation/")) {
            test.pass("Successfully selected single room");
        } else {
            test.fail(" failed");
        }
    }
}