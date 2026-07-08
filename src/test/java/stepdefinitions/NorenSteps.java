package stepdefinitions;

import io.cucumber.java.en.*;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    @And("add Scrip to MW")
    public void add_scrip_to_mw() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(ExpectedConditions.elementToBeClickable(
            By.className("close-btn"))).click();
}
    @And("Add Scrip")
    public void add_scrip() throws InterruptedException {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Click search icon
    wait.until(ExpectedConditions.elementToBeClickable(By.id("srch"))).click();
    Thread.sleep(5000); // Wait for the search input to appear

    // Type ACC
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")))
        .sendKeys("CAMS-EQ");
        Thread.sleep(5000);

    // Click ACC-EQ result
    wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[text()='CAMS-EQ']")))
        .click();
        Thread.sleep(5000);
    }
    @And("Close Search")
    public void close_search() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Close search
    wait.until(ExpectedConditions.elementToBeClickable(
        By.className("close-btn")
    )).click();

    // Hover on ACC-EQ after closing
    WebElement accScript = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[@class='rowhvr' and text()='CAMS-EQ']")
        )
    );

    Actions actions = new Actions(driver);
    actions.moveToElement(accScript).perform();

    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.elementToBeClickable(
    By.id("buyClick")
)).click();

wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//div[contains(text(),'Buy Order Entry')]")
));

// Find Qty and Price fields
List<WebElement> orderInputs = wait.until(
    ExpectedConditions.visibilityOfAllElementsLocatedBy(
        By.cssSelector("input.inputWithIcon")
    )
);

// Qty
orderInputs.get(0).sendKeys("10");

// Price
orderInputs.get(1).sendKeys("740");

// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.elementToBeClickable(
    By.cssSelector("button.placeBtnBClass")
)).click();
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