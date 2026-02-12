package saasOpsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ZoneSubscriptionPage extends BasePage {

    public ZoneSubscriptionPage(WebDriver driver){
        super(driver);
    }

    // ******************************************************************LOCATORS***************************************************************************************************************************************************************************

    public By subscription_status = By.xpath("//td[contains(text(),'Subscription Status')]/following-sibling::td/span");
    
    public By provisioning_status = By.xpath("//td[contains(text(),'Provisioning Status')]/following-sibling::td/span[text()='Success']");
    
    public By goto_landing_btn = By.id("productViewForm:gotosubsclandingpageId");
    
    public By subscription_tab = By.id("menuCLinkForm:productLink_TAB_MENU");


    // ******************************************************************ACTIONS***************************************************************************************************************************************************************************

    public String getSubscriptionStatus() {
        WebElement element = wait.waitForVisibility(subscription_status);
        return element.getText().trim();
    }

    public String getProvisioningStatus() {
        WebElement element = wait.waitForVisibility(provisioning_status);
        return element.getText().trim();
    }

    public void clickGotoLandingBtn() {
        wait.waitForElementToBeClickable(goto_landing_btn).click();
    }

    public void clickSubscriptionTab() {
        wait.waitForElementToBeClickable(subscription_tab).click();
    }
    public boolean isSubscriptionActive() {
        wait.waitForVisibility(subscription_status, 30);
        return getSubscriptionStatus().equalsIgnoreCase("Active");
    }

    public boolean isProvisioningSuccessful() {
        wait.waitForVisibility(provisioning_status, 60);
        return getProvisioningStatus().equalsIgnoreCase("Success");
    }

    public void clickLandingIfVisible() {
        wait.waitForElementToBeClickable(goto_landing_btn, 10);
        clickGotoLandingBtn();
    }

}
