import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testing {
    private WebDriver driver;
    private NgWebDriver ngDriver;

    @BeforeClass
    public void initializeDriver(){
        driver = DriverSingleton.getDriverInstance();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
    }

    @Test(priority = 1)
    public void Exercise1_Implicit(){
        driver.findElement(By.id("btn")).click();
        driver.findElement(By.id("message")).isDisplayed();
    }

    @Test(priority = 2)
    public void Exercise1_Sleep() throws InterruptedException {
        driver.findElement(By.id("hidden")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish1")).isDisplayed();

    }

    @Test(priority = 3)
    public void Exercise1_Explicit(){
        driver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
        driver.findElement(By.id("finish2")).isDisplayed();
    }

    @Test
    public void Exercise2(){
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        driver.get("https://dgotlieb.github.io/AngularJS/main.html");
        ngDriver.waitForAngularRequestsToFinish();
        WebElement firstName = driver.findElement(ByAngular.model("firstName"));
        firstName.clear();
        firstName.sendKeys("Zion");
        Assert.assertEquals("Zion",firstName.getAttribute("value"));
    }

    // Exercise3
    // The pageLoadTimeout limits the time that the script allots for a web page to be displayed.

    @Test
    public void Exercise4(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driver.findElement(By.id(Constants.btnSevenId)).getSize());
        driver.findElement(By.id("six")).isDisplayed();
        String number = "25";
        CalculaturPage.pressOne();
        CalculaturPage.pressZero();
        CalculaturPage.pressZero();
        CalculaturPage.pressDivide();
        CalculaturPage.pressFour();
        CalculaturPage.pressEqual();
        Assert.assertEquals(number, CalculaturPage.getAnswer());
    }

    @Test
    public void Exercise5(){
        driver.get("https://www.themarker.com");
        int counter = 0;
        String wordToFind = "news";
        String pageSource = driver.getPageSource();
        Pattern newsPattern = Pattern.compile(wordToFind, Pattern.CASE_INSENSITIVE);
        Matcher newsMatcher = newsPattern.matcher(pageSource);
        while (newsMatcher.find()){
            counter++;
        }
        System.out.println("The word " + wordToFind + " is found " + counter + " times in the source code.");
    }

    @Test
    public void Exercise6(){
        ((JavascriptExecutor)driver).executeScript("window.print()");
    }

    @Test
    public void Exercise7(){
        driver.get("https://dgotlieb.github.io/AngularJS/main.html");
        WebElement firstName = driver.findElement(By.cssSelector("input[ng-model='firstName']"));
        firstName.clear();
        firstName.sendKeys("Zion");
        Assert.assertEquals("Zion",firstName.getAttribute("value"));
    }

    @AfterClass
    public void closeDriver(){
        driver.quit();
    }
}
