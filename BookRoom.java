import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookRoom extends util{
    
    @BeforeMethod
    public ExtentReports getReportInstance() {
        if (extent == null) {
            reporter = new ExtentSparkReporter("test-output/Admin Login.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    @Test(dataProvider = "CheckIn")
    public void Checkin(String Url,String frist, String last, String mail, String num) {
        driver.get(url+Url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doReservation")));
        reserv = driver.findElement(By.id("doReservation"));
        wait.until(ExpectedConditions.elementToBeClickable(reserv));
        reserv.click();
        fristname = driver.findElement(By.name("fristname"));
        lastname = driver.findElement(By.name("lastname"));
        email = driver.findElement(By.name("email"));
        phone = driver.findElement(By.name("phone"));
        submit = driver.findElement(By.xpath("//button[@type='button']"));
        fristname.sendKeys(frist);
        lastname.sendKeys(last);
        email.sendKeys(mail);
        phone.sendKeys(num);
        submit.click();
        text = driver.findElement(By.xpath("//h2[@class='card-title fs-4 fw-bold mb-3']"));
        if(text.getText().contains("Booking Confirmed")){test.pass("Booking Confirmed");}
        else {test.fail("Booking Failed");}
    }

}