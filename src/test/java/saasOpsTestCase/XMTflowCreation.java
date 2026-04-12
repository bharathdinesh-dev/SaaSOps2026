package saasOpsTestCase;

import org.testng.annotations.Test;
import saasOpsPageObjects.SaasOperationsSetupPOC;
import saasOpsTestBase.SaasOpsBaseClass;

public class XMTflowCreation extends SaasOpsBaseClass {

    @Test
    public void XMTFlowCreator() {

        SaasOperationsSetupPOC saasOperationsObj =
                new SaasOperationsSetupPOC(driver);
        switch (p.getProperty("zone").toLowerCase()) {
		case "new":
//	      Create zone i need to call zone creation method 
			createZoneAccount();
			break;
		case "existing":
//			Login to zone
	        LoginToZone(
	                p.getProperty("email"),
	                p.getProperty("password")
	        );
			break;

		default:
			System.out.println("Invalid zone type");
			break;
		}

        String randomNumber=randomNumber(3);
        saasOperationsObj.createApplicationProfile(
                p.getProperty("XMT_flow"),
                p.getProperty("XMT_flowOption"),
                p.getProperty("XMT_group_name")+randomNumber,
                p.getProperty("XMT_app_name")+randomNumber,
//                Profile and tag removed from UI 
//                p.getProperty("XMT_profile_name")+randomNumber,
//                p.getProperty("XMT_qa_tag"),
                p.getProperty("zone")
        );
        
    }
}
