import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.time.Duration;

public class contactform extends util {

    @BeforeMethod
    public ExtentReports getReportInstance() {
        if (extent == null) {
            reporter = new ExtentSparkReporter("test-output/Contact Form.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    @BeforeMethod
    public void elements(){
        driver.get("https://automationintesting.online");
        report = getReportInstance();
        test = report.createTest("Contact Form");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        name = driver.findElement(By.id("name"));
        email = driver.findElement(By.id("email"));
        phone = driver.findElement(By.id("phone"));
        subject = driver.findElement(By.id("subject"));
        message = driver.findElement(By.id("description"));
        submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
    }


    @Test(dataProvider = "contact-data")
    public void Contacttest(String Name, String Email, String Phone, String Sub, String Mess){
        name.sendKeys(Name);
        email.sendKeys(Email);
        phone.sendKeys(Phone);
        subject.sendKeys(Sub);
        message.sendKeys(Mess);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        if(submit.isEnabled()){submit.click();}
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='contact']")));
        text = driver.findElement(By.xpath("//*[@id='contact']"));

        if (text.getText().contains("Thanks for getting in touch")){
            test.pass("message created");
        }
        else if(text.getText().contains("must be a well-formed email address")){
            test.fail("email isn't correct");
        }
        else{
            test.fail("input fields isn't correct or empty");
        }

    }

}

