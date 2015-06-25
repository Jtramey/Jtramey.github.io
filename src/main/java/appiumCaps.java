/**
 * Created by jonathon.ramey on 6/25/2015.
 */
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TestName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class appiumCaps {

    private AndroidDriver driver;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }
    public @Rule
    TestName name = new TestName();


    @Before
    public void setUp() throws MalformedURLException {
        File appDir = new File("~/Yikyak/");
        File app = new File(appDir, "YikYak-2.7.3.apk");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("app", app.getAbsolutePath());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5");
        desiredCapabilities.setCapability("appiumVersion", "1.3.4");
        desiredCapabilities.setCapability("name", name.getMethodName());

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(url, desiredCapabilities);
        sessionId = driver.getSessionId().toString();
        try {

            // Connect to the appium server on localhost
            this.driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities) {
            };
        } catch (MalformedURLException e
                ) {
            e.printStackTrace();
        }
        //Timeout is set to 80 seconds before the test quits
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        System.out.println("Test that was just run: " + this.getSessionId());
        driver.quit();
    }

    @Test
    public void switchToHotPosts()
    {
        getHotButton().click();

    }
    WebElement getHotButton() {
        return driver.findElementByName("Hot");
    }
}
