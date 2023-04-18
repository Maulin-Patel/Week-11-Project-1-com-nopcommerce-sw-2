package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before // JUnit Annotation which will run before every method

    public void setUp() { // calling openBrowser method using baseUrl value
        openBrowser(baseUrl);
    }

    @Test // JUnit Annotation which will allow executing this method
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        driver.findElement(By.linkText("Log in")).click();//Find the login link and click on login link
        String expectedMessage = "Welcome, Please Sign In!"; //This is from requirement
        String actualMessage = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")).getText();//Find the welcome text element and get the text
        Assert.assertEquals("User was not navigated.",expectedMessage, actualMessage);//Validate actual and expected message
    }

    @Test // JUnit Annotation which will allow executing this method
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        driver.findElement(By.linkText("Log in")).click(); //Click on the 'Login' link
        driver.findElement(By.id("Email")).sendKeys("sareejunction@gmail.com"); // Find Email field and provide valid email
        driver.findElement(By.name("Password")).sendKeys("12345678");// Find password field and enter valid Password
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();//Click on login button
        String expectedMessage = "Log out";// storing expected text
        String actualMessage = driver.findElement(By.xpath("//a[@class='ico-logout']")).getText();// Find the welcome text element and get the text
        Assert.assertEquals("User was unable to login successfully.",expectedMessage, actualMessage);// Validating actual and expected message
    }

    @Test // JUnit Annotation which will allow executing this method
    public void verifyTheErrorMessage() {
        driver.findElement(By.linkText("Log in")).click();//Click on the 'Login' link
        driver.findElement(By.id("Email")).sendKeys("maulin@gmail.com");// Find email field and Enter invalid username
        driver.findElement(By.name("Password")).sendKeys("123456");//Find password field and enter invalid Password
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();// Find and click on login button
        String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        String actualMessage = driver.findElement(By.xpath("//div[@class ='message-error validation-summary-errors']")).getText();// Find the welcome text element and get the text
        Assert.assertEquals("Error message was not displayed.",actualMessage, expectedMessage);// Validating actual and expected message
    }
    @After // JUnit Annotation which will run after every method
    public void tearDown(){
        closeBrowser();
    }
}