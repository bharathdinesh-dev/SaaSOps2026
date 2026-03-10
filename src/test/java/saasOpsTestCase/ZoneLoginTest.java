package saasOpsTestCase;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import saasOpsPageObjects.ZoneLoginPOC;
import saasOpsTestBase.SaasOpsBaseClass;

public class ZoneLoginTest extends SaasOpsBaseClass {
	
	@Test
	public void LoginTest() {
		LoginToZone(p.getProperty("email"),p.getProperty("password")); 
	}
	
	
	
}
