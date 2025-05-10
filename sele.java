import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class sele {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    WebElement name;
    WebElement email;
    WebElement phone;
    WebElement subject;
    WebElement message;
    WebElement submit;
    WebElement text;
    @BeforeMethod
        public void setup() throws InterruptedException {
            report = extentreportermanager.getReportInstance();
            test =report.createTest("Contact us form");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://automationintesting.online/");
            Thread.sleep(2000);
            name =driver.findElement(By.id("name"));
            email = driver.findElement(By.id("email"));
            phone = driver.findElement(By.id("phone"));
            subject = driver.findElement(By.id("subject"));
            message = driver.findElement(By.id("description"));
            submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        }

        @DataProvider(name = "contact-data")
        public Object[][] contactData() {
            return new Object[][]{
                    {"Mahmoud","xyz@x.z","00000000000","testing","test the contact us form"},
                    {"Ahmed","xyz.x.com","00000000000","testing","test the contact us form"},
                    {" "," "," "," "," "}
            };
        }

        @Test(dataProvider = "contact-data")
        public void Contacttest(String Name, String Email, String Phone, String Sub, String Mess) throws InterruptedException {

            name.sendKeys(Name);
            Thread.sleep(500);

            email.sendKeys(Email);
            Thread.sleep(500);

            phone.sendKeys(Phone);
            Thread.sleep(500);

            subject.sendKeys(Sub);
            Thread.sleep(500);

            message.sendKeys(Mess);
            Thread.sleep(500);

            submit.click();
            Thread.sleep(2000);

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
       @AfterClass
       public void closeReport(){
           report.flush();
       }
        @AfterMethod
        public void finish(){
            driver.quit();
        }
    }
