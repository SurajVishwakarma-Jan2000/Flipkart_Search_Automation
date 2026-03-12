package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {

        wrappers = new Wrappers(driver);

        System.out.println("Opening Flipkart : Start Test");

        wrappers.openFlipkart("https://www.flipkart.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'✕')]")));
        wrappers.closeLoginPopup();

        wrappers.searchProduct("Washing Machine");

        Thread.sleep(4000);

        wrappers.sortByPopularity();

        Thread.sleep(4000);

        wrappers.countItemsWithRatingLessThan4();

        System.out.println("End Test");
    }

    @Test
    public void testCase02() throws InterruptedException {

        wrappers = new Wrappers(driver);

        System.out.println("Opening Flipkart : Start Test");

        wrappers.openFlipkart("https://www.flipkart.com");

        Thread.sleep(4000);

        wrappers.searchProduct("iPhone");

        Thread.sleep(4000);

        wrappers.printTitleAndDiscount();

        System.out.println("End Test");
    }

    @Test
    public void testCase03() throws InterruptedException {

        wrappers = new Wrappers(driver);

        System.out.println("Opening Flipkart : Start Test");

        wrappers.openFlipkart("https://www.flipkart.com");

        Thread.sleep(4000);

        wrappers.searchProduct("Coffee Mug");

        Thread.sleep(4000);

        wrappers.selectFourStarFilter();

        Thread.sleep(6000);

        wrappers.printTopReviewedMugs();

        System.out.println("End Test");
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}