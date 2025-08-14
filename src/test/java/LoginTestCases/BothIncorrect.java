package LoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class BothIncorrect {

    WebDriver driver;

    @BeforeMethod
    public void openPage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    @Parameters({"username","password"})
    public void loginWithBothIncorrect(String uName, String pass){
        WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
        username.sendKeys(uName);

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(pass);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
