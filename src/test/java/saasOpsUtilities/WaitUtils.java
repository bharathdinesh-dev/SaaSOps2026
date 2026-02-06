package saasOpsUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private static final int DEFAULT_WAIT = 10;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForVisibility(WebElement element) {
        waitForVisibility(element, DEFAULT_WAIT);
    }

    public void waitForVisibility(WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForClikable(WebElement element) {
    	waitForClikable(element, DEFAULT_WAIT);
    }

    public void waitForClikable(WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.elementToBeClickable(element));
    }
    
}

