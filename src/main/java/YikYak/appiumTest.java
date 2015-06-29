package YikYak;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class appiumTest {

    private AndroidDriver driver;
    private String sessionId;


    @BeforeTest
    public void setUp() throws MalformedURLException {
        //Set file path to yikyak apk
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/");
        File app = new File(appDir, "YikYak-2.7.3.apk");
        // Caps for what the appium server does
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("app", app.getAbsolutePath());
        desiredCapabilities.setCapability("platform" , "Windows");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5");
        desiredCapabilities.setCapability("appiumVersion", "1.3.4");

        try {

            // Connect to the appium server on localhost, can be changed to different port or url
            this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities) {
            };
        } catch (MalformedURLException e
                ) {
            e.printStackTrace();
        }
        //Timeout is set to 80 seconds before the test quits
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        sessionId = driver.getSessionId().toString();

    }
    // Test methods
    @Test
    public void submitPost() {

        getComposeButton().click();
        getAgreeToRulesButton().click();
        getWhatsOnYourMindTextBox().click();
        getWhatsOnYourMindTextBox().sendKeys("");
        getSendButton().click();
    }

    @Test
    public void switchToHotPosts() {
        driver.resetApp();
        WebDriverWait wait = new WebDriverWait(driver, 80);

        getHotButton().click();
        //Will Wait forever to show POC
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.name("/uF013")));

    }

    //Getters and Setters
    WebElement getHotButton() {
        return driver.findElementByName("Hot");
    }
    WebElement getComposeButton() {return driver.findElementById("com.yik.yak:id/fab");}
    WebElement getAgreeToRulesButton() { return driver.findElementById("com.yik.yak:id/btnDialogOk");}
    WebElement getWhatsOnYourMindTextBox() { return driver.findElementByName("What's on your mind?");}
    WebElement getSendButton() { return driver.findElementByName("Send");}

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
