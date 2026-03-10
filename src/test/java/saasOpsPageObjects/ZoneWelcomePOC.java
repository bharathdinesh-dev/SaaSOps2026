package saasOpsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZoneWelcomePOC extends BasePage{

    public ZoneWelcomePOC (WebDriver driver) {
        super(driver);
    }

    // ******************************************************************LOCATORS***************************************************************************************************************************************************************************

    public By Dashboard_lnk = By.id("menuCLinkForm:dashBoardRootLink_TAB_MENU");
    public By Dashboard_title = By.xpath("//span[text()='Dashboard'and@class='gui-inline-header']");
    public By SurpaasAI_lnk = By.id("menuCLinkForm:openchatCloudddCmdLink");
    public By CloudOps_lnk = By.xpath("//span[contains(text(),'Cloud Ops')]");
    public By Applications_lnk = By.id("menuCLinkForm:appcCmdLink");
    public By SaasSuccess_lnk = By.id("menuCLinkForm:saasSuccessDashBoard_TAB_MENU");
    public By SaasOpserations_lnk = By.id("menuCLinkForm:appsaascCmdLink");
    public By Marketizer_lnk = By.id("menuCLinkForm:appMarketizercCmdLink");
    public By Administration_lnk = By.id("adminli");


    // ******************************************************************ACTIONS***************************************************************************************************************************************************************************

    public void clickDashboard_lnk() {
        wait.waitForElementToBeClickable(Dashboard_lnk).click();
    }
    
    public void waitTillDashbordTitleVisibe() {
        wait.waitForVisibility(Dashboard_title);
    }

    public void clickSurpaasAI_lnk() {
        wait.waitForElementToBeClickable(SurpaasAI_lnk).click();
    }

    public void clickCloudOps_lnk() {
        wait.waitForElementToBeClickable(CloudOps_lnk).click();
    }

    public void clickApplications_lnk() {
        wait.waitForElementToBeClickable(Applications_lnk).click();
    }

    public void clickSaasSuccess() {
        wait.waitForElementToBeClickable(SaasSuccess_lnk).click();
    }

    public void clickSaasOpserations_lnk() {
        wait.waitForElementToBeClickable(SaasOpserations_lnk).click();
    }

    public void clickMarketizer_lnk() {
        wait.waitForElementToBeClickable(Marketizer_lnk).click();
    }

    public void clickAdministration_lnk() {
        wait.waitForElementToBeClickable(Administration_lnk).click();
    }
}
