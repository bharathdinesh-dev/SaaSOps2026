package saasOpsTestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import saasOpsPageObjects.ZoneSelfProvisioningPOC;
import saasOpsPageObjects.ZoneSubscriptionPage;
import saasOpsTestBase.SaasOpsBaseClass;
import saasOpsUtilities.MfaGetter;
import saasOpsUtilities.MySQLDBRead;

public class CreateZone extends SaasOpsBaseClass{
	ZoneSelfProvisioningPOC zoneObj;
	ZoneSubscriptionPage subObj; 
	String mfa_time;
	@Test
	public void createZoneAccount() throws InterruptedException {
	    zoneObj = new ZoneSelfProvisioningPOC(driver);
	    zoneObj.enterEmail(p.getProperty("email"));
	    zoneObj.enterAccountName(p.getProperty("acc_name") + randomeNumber(4));
	    Thread.sleep(1000);
	    zoneObj.clickSubscribe();
	    mfa_time=getCurrentDbDateTime();
	    System.out.println(mfa_time);
//	    Validation
	    handleDuplicateAccountName();
	    System.out.println("handleDuplicateAccountName");
	    ensureAccountCreated();
	    System.out.println("ensureAccountCreated");
	    MySQLDBRead mySQLDBRead = new MySQLDBRead();
	    MfaGetter obj1 = new MfaGetter();
	    System.out.println(mfa_time+" : "+p.getProperty("email")+" : "+p.getProperty("qaops_db_url")+" : "+p.getProperty("db_username")+" : "+p.getProperty("db_password"));
	    String mfa = getMfaFromDB(mfa_time,p.getProperty("email"),p.getProperty("db_url"),p.getProperty("db_username"),p.getProperty("db_password"));
	    System.out.println(mfa);
	    zoneObj.enterMFA(mfa);
	    ensureMfa();
	    System.out.println("enterMFA");
	    zoneObj.enterPassword(p.getProperty("password"));
	    System.out.println("enterPassword");
	    zoneObj.clickSave();
	    System.out.println("clickSave");
	    ensureSuccess();
	    zoneObj.clickSuccessOk();
	    System.out.println("Click ok in success popup");
//	    *******************provisining end***************************************************************************************************************************************************
//	    Validation
	    subObj = new ZoneSubscriptionPage(driver);
	    
	    ensureSubscrptionStatus(subObj.subcription_status);
	    ensureProvisioningStatus(subObj.provisioning_status);
	    ensureLandiPageBtnVisibility(subObj.goto_landing_btn);
	    
	}
	public void ensureMfa() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(zoneObj.invalid_mfa));
//			Generate new MFA 
		}catch(Exception e) {
			
		}
	}
	
	public void ensureSubscrptionStatus(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			if(subObj.getSubscriptionStatus().equalsIgnoreCase("Active")) {
				System.out.println("Subscription success");}
		}catch(Exception e) {
			System.out.println("Subscption inprogress for morethan 10 seconds");
		}
	}
	
	public void ensureProvisioningStatus(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			if(subObj.getProvisioningStatus().equalsIgnoreCase("Success")) {
				System.out.println("Provisioning success");}
		}catch(Exception e) {
			System.out.println("Provisioning inprogress for morethan 30 seconds");
		}
	}
	
	public void ensureLandiPageBtnVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("Goto landing page button visible");
		subObj.clickGotoLandingBtn();}
		catch(Exception e) {
			System.out.println("Goto landing page button not visible");
		}
	}
	
	public void ensureSuccess() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.visibilityOf(zoneObj.success_msg));
			System.out.println("Sucess popup displayed");
		}catch(Exception e) {
			try {
				zoneObj.clickSave();
			}catch(Exception e1) {
				System.out.println("Save not displayed ");
			}
		}
	}

	public void ensureAccountCreated() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    while (true) {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(zoneObj.acc_creation_alrt));
//	            account creation successfuly 
	            break;
	        } catch (Exception e) {
	            try {
	            	zoneObj.clickSubscribe();
	            }catch(Exception ee) {
	            	System.out.println("subscribe button not displayed ");
	            	break;
	            }}}
	    }
	public void handleDuplicateAccountName() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	    while(true) {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(zoneObj.acc_name_alrt));
	        	Thread.sleep(1000);
	        	zoneObj.acc_name_txt.clear();
	        	Thread.sleep(1000);
	        	zoneObj.enterAccountName(p.getProperty("acc_name") + randomeNumber(4));
	        	Thread.sleep(1000);
	            zoneObj.clickSubscribe();       
	        
	    } catch (Exception ignored) {
	    	System.out.println("catch");
	        break;
	    }}
	}

	public String getMfaFromDB(String datee,String email,String dburl,String username,String password) {

		String mfaCode=null;
        String query = "SELECT VERIFICATIONCODE " +
                       "FROM c_identitycode " +
                       "WHERE USERNAME=? AND TYPE='Forget' AND STATUS='Active' AND CREATED_DATE >? " +
                       "ORDER BY CREATED_DATE DESC LIMIT 1";

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            Connection con = DriverManager.getConnection(dburl, username, password);

            // Prepare Statement
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, datee);

            // Execute Query
            ResultSet rs = ps.executeQuery();

            // Read Result
            if (rs.next()) {
                mfaCode = rs.getString("VERIFICATIONCODE");
                System.out.println("MFA Code: " + mfaCode);
            } 
            else {
                System.out.println("No record found");
            }

            // Close connections
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }return mfaCode;
	}

}
