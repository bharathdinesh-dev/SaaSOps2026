package saasOpsTestCase;


import org.testng.annotations.Test;
import saasOpsPageObjects.SaasOperationsSetupPOC;
import saasOpsTestBase.SaasOpsBaseClass;
import saasOpsUtilities.WaitUtils;

public class VTflowCreation extends SaasOpsBaseClass {
	SaasOperationsSetupPOC saasOperationsObj;
	WaitUtils wait;
	@Test
	public void VTFlowCreator() {
		saasOperationsObj = new SaasOperationsSetupPOC(driver);
		wait=new WaitUtils(driver);
		LoginToZone(p.getProperty("email"),p.getProperty("password"));
		ApplicationProfileCreator();
	}
	public void ApplicationProfileCreator() {
		wait.waitForVisibility(saasOperationsObj.saas_operations_menu);
		wait.waitForVisibility(saasOperationsObj.saas_operations_menu);
		saasOperationsObj.clickSaasOperationsMenu();
		wait.waitForVisibility(saasOperationsObj.setup_application_profile_btn);
		saasOperationsObj.clickSetupApplicationBtn();
		wait.waitForVisibility(saasOperationsObj.vt_radio_btn);
		saasOperationsObj.clickVtRadioBtn();
		wait.waitForVisibility(saasOperationsObj.setup_application_profile_dropdown);
		saasOperationsObj.clickSetupApplicationDropdown();
		wait.waitForVisibility(saasOperationsObj.vt_non_container_dropdown_option);
		saasOperationsObj.clickVtNonContainerDropdown();
		wait.waitForVisibility(saasOperationsObj.group_name_dropdown);
		saasOperationsObj.enterGroupName(p.getProperty("vt_group_name"));
		wait.waitForVisibility(saasOperationsObj.app_name_dropdown);
		saasOperationsObj.enterAppName(p.getProperty("vt_app_name"));
		wait.waitForVisibility(saasOperationsObj.profile_name_txt);
		saasOperationsObj.enterProfileName("vt_profile_name");
		wait.waitForVisibility(saasOperationsObj.tag_name_dropdown);
		saasOperationsObj.clickTagDropdown();
		wait.waitForVisibility(saasOperationsObj.qa_tag_name_dropdown);
		saasOperationsObj.clickQaTagDropdown();
		wait.waitForVisibility(saasOperationsObj.create_Application_profile_btn);
		saasOperationsObj.clickCreateApplicationProfile();
	}
}
