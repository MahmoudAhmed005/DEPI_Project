import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Room extends util{

    @BeforeMethod
    public ExtentReports getReportInstance() {
        if (extent == null) {
            reporter = new ExtentSparkReporter("test-output/Opreations on Rooms.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    @BeforeMethod
    public void login(){
        driver.get(url+"admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username = driver.findElement(By.id("username"));
        username.sendKeys("admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password = driver.findElement(By.id("password"));
        password.sendKeys("password");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doLogin")));
        submit = driver.findElement(By.id("doLogin"));
        submit.click();
    }

    @BeforeMethod
    public void set(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        name = driver.findElement(By.id("roomName"));
        op = driver.findElement(By.id("type"));
        option = new Select(op);
        acc = driver.findElement(By.id("accessible"));
        accessible = new Select(acc);
        wifibox = driver.findElement(By.id("wifiCheckbox"));
        tvbox = driver.findElement(By.id("tvCheckbox"));
        Price = driver.findElement(By.id("roomPrice"));
        submit = driver.findElement(By.id("createRoom"));
    }

    @Test(priority = 1, dataProvider = "Room")
    public void addroom(String num, String price){
        test_output = "create A Room";
        report = getReportInstance();
        test = report.createTest("create A Room");
        name.sendKeys(num);
        option.selectByValue("Single");
        accessible.selectByValue("false");
        wifibox.click();
        tvbox.click();
        Price.sendKeys(price);
        submit.click();
        try {
            driver.findElement(By.id("roomName"+num));
            test.pass("Room added");
        }catch (NoSuchElementException e){
            test.fail("missing fields");
        }

    }

    @Test(priority = 2)
    public void deleteroom(){
        test_output = "Delete A Room";
        report = getReportInstance();
        test = report.createTest("Delete A Room");
        WebElement div = driver.findElement(By.xpath("//div[@data-testid='roomlisting'][last()]"));
        delete = div.findElement(By.xpath(".//span[@class='fa fa-remove roomDelete']"));
        delete.click();
        text = driver.findElement(By.id("roomName"+roomId));
        if(text.isDisplayed()){test.fail("delete failed");}
        else {test.pass("Room deleted");}
    }

    @Test(priority = 3)
    public void updateroom(){
        test_output = "Update A Room";
        report = getReportInstance();
        test = report.createTest("Update A Room");
        driver.get(url+"admin/room/20");
        submit = driver.findElement(By.xpath("//button[@class='btn btn-outline-primary float-sm-end']"));
        submit.click();
        name = driver.findElement(By.id("roomName"));
        op = driver.findElement(By.id("type"));
        option = new Select(op);
        Price = driver.findElement(By.id("roomPrice"));
        name.sendKeys("200");
        option.selectByValue("Suite");
        Price.sendKeys("900");
        WebElement update = driver.findElement(By.id("update"));
        update.click();
        text = driver.findElement(By.tagName("h2"));
        if(text.getText().contains("200")){test.pass("Room Updated");}
        else { test.fail("Update failed");}

    }
}
