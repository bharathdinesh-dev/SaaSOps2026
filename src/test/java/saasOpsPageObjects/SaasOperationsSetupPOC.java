package saasOpsPageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

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
    
//    1. Register Cloud Account and Select Application Resources
    public By new_Deployment_Togle = By.id("acquiremode");
    public By add_New_CloudProvider_Link = By.id("scanserverInstanceForm:addCloudAccount");
    public By provider_accountName = By.id("providerAccForm:accountName");
    public By directory_id = By.id("providerAccForm:tenantid");
    public By app_id = By.id("providerAccForm:clientid");
    public By secket_key = By.id("providerAccForm:secretkey1");
    public By subscription_id = By.id("providerAccForm:SubscriptionID");
    public By submit_btn = By.id("footerForm:providerSaveOrUpdate:spinbutton");
    public By reset_btn = By.id("footerForm:cloudproviderReset");
    public By vm_select_checkbox = By.xpath("//span[text()='${vm_ip}']/parent::td/parent::tr//div[contains(@id,'_checkbox')]");
    public By proceed_afterAcquire = By.id("scanserverInstanceForm:proceedtoappscan");
    public By confirm_YES_btn = By.id("vminfoform:confrmvmsyesSO");
    
//    2. Setup SaaSOps™ Service
    public By activate_saasOpsService_btn = By.id("instalagentdeploybtn");
    public By percentage = By.xpath("//span[text()='100%']");
    public By proceed_afterAgent_btn = By.xpath("appblueprintproceed");
    
//    3.Blueprint
    public By blueprint_graph = By.xpath("SaaSmynetwork");
    public By proceed_afterBluePrint = By.xpath("viewSaasBlueprintGraphForm:proceedToThrdStp");
    
//    4.Snapshot
    public By snap_name_input = By.id("viewSaasBlueprintGraphForm:proceedToThrdStp");
    public By create_snap_btn = By.id("enableSaasTakeSnapFormId:takesaassnap");
    public By snap_success_msg = By.id("//img[@id='enableSaasTakeSnapFormId:progresssnapgreen1']/following-sibling::span[text()='Success']");
    
//    6.Setup details for tenant access 
    public By setup_details_tenant = By.id("appSetupTenantAccesstab");
    public By application_port= By.id("preparesetupform:paramport");
    public By application_name= By.id("preparesetupform:domainContext");
//    need to add proceed button locatot
    
//    7.Tenant Lifecycle plugin
    public By tenant_Lifecycle_plugin_section= By.id("apptenantplugintab");
    public By script_upload_btn= By.id("serviceNodeAccordian:createversionmenuitembtn_button");
//    public By script_upload_btn= By.id("serviceNodeAccordian:createversionmenuitembtn_button");
    
    

    // ******************************************************************
    // BUSINESS METHOD (Complete Flow)
    // ******************************************************************

    public void createApplicationProfile(String flowName,
            String flowOption,
            String groupName,
            String appName,
            String zone) {

    		openApplicationProfileSetup(zone);
    		selectFlow(flowName);
    		selectFlowOption(flowOption);

    		boolean isGroupEntered = enterGroupName1(groupName);

    		if (!isGroupEntered) {
    			System.out.println("Group Name not entered");
    		
    		}

    		// ✅ Only executes if above is successful
    		boolean isAppEntered =enterAppName1(appName);
    		
    		if (!isAppEntered) {
    			System.out.println("App Name not entered correctly");
    		}

    		clickCreateApplicationProfile();
    }
    
    public boolean enterAutoCompleteField(By locator, String value) {

        int attempts = 0;

        while (attempts < 3) {

            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(locator));

                element.click();

                // ✅ Proper clear (IMPORTANT FIX)
                element.sendKeys(Keys.CONTROL + "a");
                element.sendKeys(Keys.DELETE);

                // 🔥 re-locate (stale fix)
                element = driver.findElement(locator);

                // ✅ Enter value
                element.sendKeys(value);

                // ✅ Wait for suggestion panel
                By panel = By.cssSelector(".ui-autocomplete-items");

                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.or(
                                ExpectedConditions.visibilityOfElementLocated(panel),
                                ExpectedConditions.invisibilityOfElementLocated(panel)
                        ));

                // ✅ Select if option appears
                By option = By.xpath("//li[contains(text(),'" + value + "')]");

                if (driver.findElements(option).size() > 0) {
                    driver.findElement(option).click();
                } else {
                    // ✅ Otherwise commit manually
                    element.sendKeys(Keys.TAB);
                }

                // ✅ Final validation (only non-empty)
                String entered = driver.findElement(locator).getAttribute("value");

                if (entered != null && !entered.trim().isEmpty()) {
                    return true;
                }

            } catch (Exception e) {
                attempts++;
            }
        }

        return false;
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
	    	wait.waitForElementToBeClickable(setup_application_profile_btn,15).click();
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
        wait.waitForElementToBeClickable(setup_application_profile_dropdown).click();
        try {
        wait.waitForVisibility(xmt_dropdown_option,2);}
        catch (Exception e) {
        	wait.waitForElementToBeClickable(setup_application_profile_dropdown).click();
		}
        }
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
/*	Profile and tag removed from UI
    public void enterProfileName(String profileName) {
        wait.waitForElementToBeClickable(profile_name_txt);
        driver.findElement(profile_name_txt).clear();
        wait.waitForElementToBeClickable(profile_name_txt);
        driver.findElement(profile_name_txt).sendKeys(profileName);
    }

    public void enterTag(String tag) {
        enterTextWithRetry(tag_name_dropdown, tag);
    }*/

    
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
    private void clickWithRetry(By locator) {

        int attempts = 0;

        while (attempts < 3) {

            try {
                wait.waitForElementToBeClickable(locator, 20).click();
                return; // success
            } 
            catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
            }
        }

        throw new RuntimeException("Unable to click element after retries: " + locator);
    }

    public void clickCreateApplicationProfile() {
        wait.waitForElementToBeClickable(create_Application_profile_btn).click();
        try {
        	wait.waitForElementToBeClickable(create_Application_profile_btn,3).click();}
        catch(Exception e) {
        	System.out.println("Create profile button already clicked");
        }
    }
}
