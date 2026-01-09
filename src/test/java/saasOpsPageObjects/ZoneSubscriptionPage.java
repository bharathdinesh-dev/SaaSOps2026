package saasOpsPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ZoneSubscriptionPage extends BasePage {
	public ZoneSubscriptionPage(WebDriver driver){
		super(driver);
	}
	@FindBy(xpath = "//td[contains(text(),'Subscription Status')]/following-sibling::td/span[text()='Active']") public WebElement subcription_status;
	@FindBy(xpath="//td[contains(text(),'Provisioning Status')]/following-sibling::td/span[text()='Success']") public WebElement provisioning_status;
	@FindBy(id = "productViewForm:gotosubsclandingpageId") public WebElement goto_landing_btn;
	@FindBy(id="menuCLinkForm:productLink_TAB_MENU") public WebElement subscription_tab;
	
	public String getSubscriptionStatus() {
		return subcription_status.getText();
	}
	public String getProvisioningStatus() {
		return provisioning_status.getText();
	}
	public void clickGotoLandingBtn() {
		goto_landing_btn.click();
	}
}
