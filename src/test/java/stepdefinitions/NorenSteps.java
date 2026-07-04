package stepdefinitions;

import io.cucumber.java.en.*;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;


public class NorenSteps {

    WebDriver driver;

    public void takeScreenshot(String fileName) throws IOException {

    File folder = new File("target/screenshots");
    if (!folder.exists()) {
        folder.mkdirs();
    }

    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File dest = new File(folder, fileName + ".png");

    FileUtils.copyFile(src, dest);
}

    @Given("user opens browser")
    public void user_opens_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @When("user goes to Noren Web")
    public void user_goes_to_noren_web() {
        driver.get("https://rama.kambala.co.in/NorenWeb2.0/investor-entry-level/login");
    }

    @And("user enters username {string}")
    public void user_enters_username(String username) {
        driver.findElement(By.id("lgnusrid")).sendKeys(username);
    }

    @And("user enters password {string}")
    public void user_enters_password(String password) {
        driver.findElement(By.id("lgnpwd")).sendKeys(password);
    }

    @And("user enters otp {string}")
    public void user_enters_otp(String otp) {
        driver.findElement(By.id("lgnotp")).sendKeys(otp);
    }

    @And("user clicks login")
    public void user_clicks_login() {
        driver.findElement(By.className("lgnBtnClss")).click();
    }

    @Then("title should contain {string}")
public void title_should_contain(String expectedTitle) {

    String actualTitle = driver.getTitle();

    try {
        takeScreenshot("LoginResult");
    } catch (IOException e) {
        e.printStackTrace();
    }

    Assert.assertTrue(actualTitle.contains(expectedTitle));

    // driver.quit();
}
}