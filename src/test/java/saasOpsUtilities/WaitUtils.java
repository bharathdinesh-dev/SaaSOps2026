package saasOpsUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private static final int DEFAULT_WAIT = 15;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for visibility (By)
    public WebElement waitForVisibility(By locator) {
        return waitForVisibility(locator, DEFAULT_WAIT);
    }

    public WebElement waitForVisibility(By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for clickable (By)
    public WebElement waitForElementToBeClickable(By locator) {
        return waitForElementToBeClickable(locator, DEFAULT_WAIT);
    }

    public WebElement waitForElementToBeClickable(By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    public boolean isElementDisplayed(By locator, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}


