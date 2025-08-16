package DataDrivenTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginUsingDataProvider {
    WebDriver driver;

    @BeforeMethod
    public void openPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @DataProvider(name = "loginData")  //we use object because there can be any data types, like string, int, float etc. To all of those kinds, we can use the common data type "Object"
    public Object[][] loginDataProvider(){
        String[][] data = {
                {"Admin", "admin123", "valid"},
                {"dummyAdmin", "dummyPass", "invalid"},
                {"dummyAdmin", "admin123", "invalid"},
                {"Admin", "dummyPass", "invalid"}
        };

        return data;  //can give these data to method with @Test annotation
    }

    @Test(dataProvider = "loginData")
    public void loginTestScenario(String uName, String pass, String expValidation) throws InterruptedException {

        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        username.sendKeys(uName);

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(pass);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(3000);

        boolean urlVerification = driver.getCurrentUrl().contains("/dashboard/index");


        if (expValidation.equals("valid")){
            Assert.assertTrue(true, "Expecting login success but not navigated to dashboard");
        }else {
            Assert.assertFalse(urlVerification, "Expecting login failed but navigated to dashboard");
        }

    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
