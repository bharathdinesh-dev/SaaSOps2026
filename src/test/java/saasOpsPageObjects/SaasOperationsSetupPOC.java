package saasOpsPageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SaasOperationsSetupPOC extends BasePage {

    public SaasOperationsSetupPOC(WebDriver driver) {
        super(driver);
    }

    // ******************************************************************
    // LOCATORS
    // ******************************************************************

    public By saas_operations_menu = By.id("menuCLinkForm:appsaascCmdLink");
    public By setup_application_profile_btn = By.id("Open_create_application_profile_btn");
    public By setup_application_profile_dropdown =
            By.xpath("//button[contains(@id,'selectsaasprofileFlowForm:create_application_profile_btn_slid')]");

    // Flow radios
    public By xmt_radio_btn = By.xpath("//span[text()='My Application is Already Multi-tenant']/ancestor::div[contains(@class,'radioCard')]//div[contains(@class,'radio')]/div");
    public By vt_radio_btn = By.xpath("//span[text()='My Application is Single Tenant']/ancestor::div[contains(@class,'radioCard')]//div[contains(@class,'radio')]/div");

    // Flow options
    public By xmt_dropdown_option = By.id("selectsaasprofileFlowForm:servicenodeover2");
    public By vt_non_container_dropdown_option = By.id("selectsaasprofileFlowForm:saasnoncontainerized1");

    // Profile fields
    public By group_name_dropdown = By.id("profilecreationForm:existingClustername_input");
    public By app_name_dropdown = By.id("profilecreationForm:existingAppusername_input");
    public By profile_name_txt = By.id("profilecreationForm:profilename");
    public By tag_name_dropdown = By.id("profilecreationForm:tagprofilecreationflow");

    public By qa_tag_name_dropdown = By.xpath("//li[text()='QA']");
    public By staging_tag_name_dropdown = By.xpath("//li[text()='Staging']");
    public By production_tag_name_dropdown = By.xpath("//li[text()='Production']");

    public By create_Application_profile_btn =
            By.id("profilecreationForm:create_application_profile_sidebar");

    // ******************************************************************
    // BUSINESS METHOD (Complete Flow)
    // ******************************************************************

    public void createApplicationProfile(String flowName,
                                         String flowOption,
                                         String groupName,
                                         String appName,
                                         String profileName,
                                         String tagName,
                                         String zone) {

        openApplicationProfileSetup(zone);
        selectFlow(flowName);
        selectFlowOption(flowOption);

<<<<<<< HEAD
    		boolean isGroupEntered = enterGroupName1(groupName);
=======
        enterGroupName(groupName);
        enterAppName(appName);
        enterProfileName(profileName);
>>>>>>> c5648adbfe2e8757b67a308a0a7adb6270ba03e6

        selectTag(tagName);

<<<<<<< HEAD
    		// ✅ Only executes if above is successful
    		boolean isAppEntered =enterAppName1(appName);
    		
    		if (!isAppEntered) {
    			System.out.println("App Name not entered correctly");
    		}

    		clickCreateApplicationProfile();
=======
        clickCreateApplicationProfile();
>>>>>>> c5648adbfe2e8757b67a308a0a7adb6270ba03e6
    }

    // ******************************************************************
    // ACTION METHODS
    // ******************************************************************

    public void openApplicationProfileSetup(String zone) {

        // Click SaaS Operations menu
        wait.waitForElementToBeClickable(saas_operations_menu,20).click();
        
     // Check if setup button is present
	    if(zone.equalsIgnoreCase("new")) {
	    	System.out.println("Setup button not available. Radios already displayed.");
	    } else {
	    	wait.waitForElementToBeClickable(setup_application_profile_btn).click();
	    }
        
    }


    public void selectFlow(String flowName) {

        switch (flowName.toLowerCase()) {

            case "xmtflow":
                wait.waitForElementToBeClickable(xmt_radio_btn).click();
                break;

            case "vtflow":
                wait.waitForElementToBeClickable(vt_radio_btn).click();
                break;

            default:
                throw new IllegalArgumentException("Invalid flow name: " + flowName);
        }
        try {
        wait.waitForElementToBeClickable(setup_application_profile_dropdown).click();}
        catch(StaleElementReferenceException st) {
        	System.out.println(st.getStackTrace());
        	wait.waitForElementToBeClickable(setup_application_profile_dropdown).click();
        }
    }

    public void selectFlowOption(String flowOption) {

        switch (flowOption.toLowerCase()) {

            case "cloudmanagement":
                wait.waitForElementToBeClickable(xmt_dropdown_option).click();
                break;

            case "noncontainer":
                wait.waitForElementToBeClickable(vt_non_container_dropdown_option).click();
                break;

            default:
                throw new IllegalArgumentException("Invalid flow option: " + flowOption);
        }
    }

<<<<<<< HEAD
//    public void enterGroupName(String groupName) {
//        WebElement group = wait.waitForElementToBeClickable(group_name_dropdown);
//        group.clear();
//        group.sendKeys(groupName);
////        need to validate value is added 
//        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(group_name_dropdown, "value", groupName));
//    }
//
//    public void enterAppName(String appName) {
//        wait.waitForElementToBeClickable(app_name_dropdown);
//        driver.findElement(app_name_dropdown).clear();
//        wait.waitForElementToBeClickable(app_name_dropdown);
//        driver.findElement(app_name_dropdown).sendKeys(appName);
//    }
//
//    public void enterProfileName(String profileName) {
//        wait.waitForElementToBeClickable(profile_name_txt);
//        driver.findElement(profile_name_txt).clear();
//        wait.waitForElementToBeClickable(profile_name_txt);
//        driver.findElement(profile_name_txt).sendKeys(profileName);
//    }
    public boolean enterGroupName1(String groupName) {
        return enterAutoCompleteField(group_name_dropdown, groupName);
    }

    public boolean enterAppName1(String appName) {
        return enterAutoCompleteField(app_name_dropdown, appName);}
=======
>>>>>>> c5648adbfe2e8757b67a308a0a7adb6270ba03e6
    public void enterGroupName(String groupName) {
        WebElement group = wait.waitForElementToBeClickable(group_name_dropdown);
        group.clear();
        group.sendKeys(groupName);
//        need to validate value is added 
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(group_name_dropdown, "value", groupName));
    }

    public void enterAppName(String appName) {
        wait.waitForElementToBeClickable(app_name_dropdown);
        driver.findElement(app_name_dropdown).clear();
        wait.waitForElementToBeClickable(app_name_dropdown);
        driver.findElement(app_name_dropdown).sendKeys(appName);
    }

    public void enterProfileName(String profileName) {
        wait.waitForElementToBeClickable(profile_name_txt);
        driver.findElement(profile_name_txt).clear();
        wait.waitForElementToBeClickable(profile_name_txt);
        driver.findElement(profile_name_txt).sendKeys(profileName);
    }

    public void selectTag(String tagName) {

        wait.waitForElementToBeClickable(tag_name_dropdown).click();

        switch (tagName.toLowerCase()) {

            case "qa":
                wait.waitForElementToBeClickable(qa_tag_name_dropdown).click();
                break;

            case "staging":
                wait.waitForElementToBeClickable(staging_tag_name_dropdown).click();
                break;

            case "production":
                wait.waitForElementToBeClickable(production_tag_name_dropdown).click();
                break;

            default:
                throw new IllegalArgumentException("Invalid tag name: " + tagName);
        }
    }

    public void clickCreateApplicationProfile() {
        wait.waitForElementToBeClickable(create_Application_profile_btn).click();
    }
}
