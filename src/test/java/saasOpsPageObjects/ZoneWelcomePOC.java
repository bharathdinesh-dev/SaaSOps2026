package saasOpsPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ZoneWelcomePOC extends BasePage{

	public ZoneWelcomePOC (WebDriver driver) {
		super(driver);
	}
	
//	Locator
	@FindBy(id = "menuCLinkForm:dashBoardRootLink_TAB_MENU") WebElement Dashboard_lnk;
	@FindBy(id = "menuCLinkForm:openchatCloudddCmdLink") WebElement SurpaasAI_lnk;
	@FindBy(xpath = "//span[contains(text(),'Cloud Ops')]") WebElement CloudOps_lnk;
	@FindBy(id = "menuCLinkForm:appcCmdLink") WebElement Applications_lnk;
	@FindBy(id = "menuCLinkForm:saasSuccessDashBoard_TAB_MENU") WebElement SaasSuccess_lnk;
	@FindBy(id = "menuCLinkForm:appsaascCmdLink") WebElement SaasOpserations_lnk;
	@FindBy(id = "menuCLinkForm:appMarketizercCmdLink") WebElement Marketizer_lnk;
	@FindBy(id = "adminli") WebElement Administration_lnk;
	
//	Action
	public void clickDashboard_lnk() {
		Dashboard_lnk.click();
	}
	public void clickSurpaasAI_lnk() {
		SurpaasAI_lnk.click();
	}
	public void clickCloudOps_lnk() {
		CloudOps_lnk.click();
	}
	public void clickApplications_lnk() {
		Applications_lnk.click();
	}
	public void clickSaasSuccess() {
		SaasSuccess_lnk.click();
	}
	public void clickSaasOpserations_lnk() {
		SaasOpserations_lnk.click();
	}
	public void clickMarketizer_lnk() {
		Marketizer_lnk.click();
	}
	public void clickAdministration_lnk() {
		Administration_lnk.click();
	}
}
