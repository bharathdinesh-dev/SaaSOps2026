package saasOpsTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import saasOpsPageObjects.ZoneSelfProvisioningPOC;
import saasOpsPageObjects.ZoneSubscriptionPage;
import saasOpsTestBase.SaasOpsBaseClass;

public class CreateZone extends SaasOpsBaseClass {

    @Test
    public void callZoneCreator() {
    	createZoneAccount();
    }
   
    @Override
    public String getUrl() {
        return p.getProperty("provisioning_url");
    }
}
