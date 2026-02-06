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
		zoneLoginValidator();
	}
	
	public void zoneLoginValidator() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(logObj.customer_lbl));
			System.out.println("Customer Logged in succesfully");
		}catch (Exception e) {
			System.out.println("Customer Logg in failed");
		}
	}
	
}
