package saasOpsPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SaasOperationsSetupPOC extends BasePage{

	public SaasOperationsSetupPOC(WebDriver driver){
		super(driver);
	}
	
//	******************************************************************LOCATORS***************************************************************************************************************************************************************************
	
//	Main menu and setup buttons
	@FindBy(id="menuCLinkForm:appsaascCmdLink") public WebElement saas_operations_menu;
	@FindBy(id="Open_create_application_profile_btn") public WebElement setup_application_profile_btn;
	@FindBy(xpath="//button[contains(@id,'selectsaasprofileFlowForm:create_application_profile_btn_slid')]") public WebElement setup_application_profile_dropdown;
	
//	Flow selecting radios buttons
	@FindBy(xpath = "//span[text()='My Application is Already Multi-tenant']/ancestor::div[contains(@class,'radioCard')]/child::div[contains(@class,'radio')]/div") public WebElement xmt_radio_btn;
	@FindBy(xpath = "//span[text()='My Application is Single Tenant']/ancestor::div[contains(@class,'radioCard')]/child::div[contains(@class,'radio')]/div") public WebElement vt_radio_btn;
	@FindBy(xpath = "//span[text()='On-Premises Installation']/ancestor::div[contains(@class,'radioCard')]/child::div[contains(@class,'radio')]/div") public WebElement onPremises_radio_btn;
	@FindBy(xpath = "//span[text()='Commercials and Usage Based Operations']/ancestor::div[contains(@class,'radioCard')]/child::div[contains(@class,'radio')]/div") public WebElement commercial_radio_btn;
	@FindBy(xpath = "//span[text()='AI Transformation for SaaS Application']/ancestor::div[contains(@class,'radioCard')]/child::div[contains(@class,'radio')]/div") public WebElement aify_radio_btn;
	
//	XMT,Service node & xaas dropdown options
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:servicenodeover2' and @class='ui-commandlink ui-widget overlayHover']") public WebElement xmt_dropdown_option;
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:servicenodecontainderrNo' and @class='ui-commandlink ui-widget overlayHover']") public WebElement sn_non_container_dropdown_option;
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:servicenodecontainderr' and @class='ui-commandlink ui-widget overlayHover']") public WebElement sn_container_dropdown_option;
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:saasmanaged' and @class='ui-commandlink ui-widget overlayHover']") public WebElement xaas_dropdown_option;

//	VT flow container and non container webelements
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:saascontainerized1' and @class='ui-commandlink ui-widget overlayHover']") public WebElement vt_container_dropdown_option;
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:saasnoncontainerized1' and @class='ui-commandlink ui-widget overlayHover']") public WebElement vt_non_container_dropdown_option;

//	OnPremesis dropdown options
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:rapidsaasmodel22' and @class='ui-commandlink ui-widget overlayHover']") public WebElement onPremesis_application_dropdown_option;
	@FindBy(xpath = "//a[@id='selectsaasprofileFlowForm:dedicatedserver1' and @class='ui-commandlink ui-widget overlayHover']") public WebElement onPremesis_server_dropdown_option;
	
	
//	Application profile creation webelements
	@FindBy(id="profilecreationForm:existingClustername_input") public WebElement group_name_dropdown;
	@FindBy(id="profilecreationForm:existingAppusername_input") public WebElement app_name_dropdown;
	@FindBy(id="profilecreationForm:profilename") public WebElement profile_name_txt;
	@FindBy(id="profilecreationForm:tagprofilecreationflow") public WebElement tag_name_dropdown;
	@FindBy(xpath = "//li[text()='QA']") public WebElement qa_tag_name_dropdown;
	@FindBy(xpath = "//li[text()='Staging']") public WebElement staging_tag_name_dropdown;
	@FindBy(xpath = "//li[text()='Production']") public WebElement production_tag_name_dropdown;
	
//	Create application profile button
	@FindBy(id="profilecreationForm:create_application_profile_sidebar") public WebElement create_Application_profile_btn;
	
//	******************************************************************ACTIONS***************************************************************************************************************************************************************************

	//	Main menu and setup buttons
	public void clickSaasOperationsMenu() {
		saas_operations_menu.click();
	}
	
	public void clickSetupApplicationBtn() {
		setup_application_profile_btn.click();
	}
	
	public void clickSetupApplicationDropdown() {
		setup_application_profile_dropdown.click();
	}
	
//	Flow selecting radios buttons 
	public void clickXmtRadioBtn() {
		xmt_radio_btn.click();
	}
	
	public void clickVtRadioBtn() {
		vt_radio_btn.click();
	}
	
	public void clickOnPremisesRadioBtn() {
		onPremises_radio_btn.click();
	}
	
	public void clickCommercialRadioBtn() {
		commercial_radio_btn.click();
	}
	
	public void clickAifyRadioBtn() {
		aify_radio_btn.click();
	}
	
//	XMT,Service node & xaas dropdown options
	public void clickXmtDropdownOption() {
		xmt_dropdown_option.click();
	}
	
	public void clickSnNonContainerDropdownOption() {
		sn_non_container_dropdown_option.click();
	}
	
	public void clickSnContainerDropdownOption() {
		sn_container_dropdown_option.click();
	}
	
	public void clickXaasDropdownOption() {
		xaas_dropdown_option.click();
	}
	
//	VT flow container and non container webelements
	public void clickVtContainerDropdown() {
		vt_container_dropdown_option.click();
	}
	public void clickVtNonContainerDropdown() {
		vt_non_container_dropdown_option.click();
	}

	
//	OnPremesis dropdown options
	public void clickOnPremesisApplicationDropdown() {
		onPremesis_application_dropdown_option.click();
	}
	public void clickOnPremesisServerDropdown() {
		onPremesis_server_dropdown_option.click();
	}

	
//	Application profile creation webelements
	public void enterGroupName(String grpName) {
		group_name_dropdown.click();
		group_name_dropdown.clear();
		group_name_dropdown.sendKeys(grpName);
	}
	public void enterAppName(String appName) {
		app_name_dropdown.click();
		app_name_dropdown.clear();
		app_name_dropdown.sendKeys(appName);
	}
	public void enterProfileName(String profileName) {
		profile_name_txt.click();
		profile_name_txt.clear();
		profile_name_txt.sendKeys(profileName);
	}
	public void clickTagDropdown() {
		tag_name_dropdown.click();
	}
	public void clickQaTagDropdown() {
		qa_tag_name_dropdown.click();
	}
	public void clickStaggingTagDropdown() {
		staging_tag_name_dropdown.click();
	}
	public void clickProductionTagDropdown() {
		production_tag_name_dropdown.click();
	}
	
//	Create application profile button
	public void clickCreateApplicationProfile() {
		create_Application_profile_btn.click();
	}
	
	
}
