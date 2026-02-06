package saasOpsTestCase;

import org.testng.annotations.Test;

import saasOpsPageObjects.SaasOperationsSetupPOC;
import saasOpsTestBase.SaasOpsBaseClass;
import saasOpsUtilities.WaitUtils;

public class XMTflowCreation extends SaasOpsBaseClass{
	SaasOperationsSetupPOC saasOperationsObj;
	@Test
	public void XMTFlowCreator() {
		saasOperationsObj = new SaasOperationsSetupPOC(driver);
		
		LoginToZone(p.getProperty("email"),p.getProperty("password"));
		ApplicationProfileCreator(p.getProperty("XMT_flow"),p.getProperty("XMT_flowOption"),p.getProperty("XMT_group_name"),p.getProperty("XMT_app_name"),p.getProperty("XMT_profile_name"),p.getProperty("XMT_qa_tag"));
	}
	public void ApplicationProfileCreator(String flowName,String flowOption,String groupName,String appName,String profileName ,String TagName) {
		wait.waitForVisibility(saasOperationsObj.saas_operations_menu);
		saasOperationsObj.clickSaasOperationsMenu();
		wait.waitForVisibility(saasOperationsObj.setup_application_profile_btn);
		saasOperationsObj.clickSetupApplicationBtn();
		
//		Choose different radio button for different flow
		switch (flowName.toLowerCase()) {
		case "vtflow":{
			wait.waitForVisibility(saasOperationsObj.vt_radio_btn);
			saasOperationsObj.clickVtRadioBtn();
		}break;
		case "xmtflow":{
			wait.waitForVisibility(saasOperationsObj.xmt_radio_btn);
			saasOperationsObj.clickXmtRadioBtn();
		}break;

		default:
			break;
		}
		wait.waitForVisibility(saasOperationsObj.setup_application_profile_dropdown);
		saasOperationsObj.clickSetupApplicationDropdown();
//		Need to write code for choose flow option
		switch (flowOption.toLowerCase()) {
		case "noncontainer":{
			wait.waitForVisibility(saasOperationsObj.vt_non_container_dropdown_option);
			saasOperationsObj.clickVtNonContainerDropdown();
		}break;
		case "cloudmanagement":{
			wait.waitForVisibility(saasOperationsObj.xmt_dropdown_option);
			saasOperationsObj.clickXmtDropdownOption();;
		}break;
		default:
			break;
		}
		
		wait.waitForVisibility(saasOperationsObj.group_name_dropdown);
		saasOperationsObj.enterGroupName(groupName);
		wait.waitForVisibility(saasOperationsObj.app_name_dropdown);
		saasOperationsObj.enterAppName(appName);
		wait.waitForVisibility(saasOperationsObj.profile_name_txt);
		saasOperationsObj.enterProfileName(profileName);
		wait.waitForVisibility(saasOperationsObj.tag_name_dropdown);
		saasOperationsObj.clickTagDropdown();
//		Need switch case 
		switch (TagName.toLowerCase()) {
		case "qa":{
			wait.waitForVisibility(saasOperationsObj.qa_tag_name_dropdown);
			saasOperationsObj.clickQaTagDropdown();
		}break;
		case "stagging":{
			wait.waitForVisibility(saasOperationsObj.staging_tag_name_dropdown);
			saasOperationsObj.clickStaggingTagDropdown();
		}break;
		case "production":{
			wait.waitForVisibility(saasOperationsObj.production_tag_name_dropdown);
			saasOperationsObj.clickProductionTagDropdown();
		}break;

		default:
			break;
		}
		wait.waitForVisibility(saasOperationsObj.create_Application_profile_btn);
		saasOperationsObj.clickCreateApplicationProfile();
	}
}
