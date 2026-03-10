package saasOpsTestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import saasOpsPageObjects.ZoneLoginPOC;
import saasOpsPageObjects.ZoneSelfProvisioningPOC;
import saasOpsPageObjects.ZoneSubscriptionPage;
import saasOpsPageObjects.ZoneWelcomePOC;
import saasOpsUtilities.WaitUtils;

public class SaasOpsBaseClass {
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	public ZoneLoginPOC logObj;
	public WaitUtils wait;
	public ZoneSelfProvisioningPOC zoneObj;
	public ZoneWelcomePOC welcomeObj;
	public ZoneSubscriptionPage subObj;
	public String mfa_time;
	ChromeOptions options;
	
	@BeforeClass
	public void setup() throws IOException {
//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
//		Initiate logs
		logger=LogManager.getLogger(this.getClass());
		
		options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
//		initiate driver 
		driver = new ChromeDriver();
		wait=new WaitUtils(driver);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(getUrl());
	}
//	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber(int count)
	{
		String generatednumber=RandomStringUtils.randomNumeric(count);
		return generatednumber;
	}
	
	public String randomAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	public String getMfaFromDB(String datee, String email, String dburl, String username, String password) {

		String mfaCode = null;
	    String accountId = null;

	    String query = "SELECT VERIFICATIONCODE, ACCOUNTID " +
	                   "FROM c_identitycode " +
	                   "WHERE USERNAME=? AND TYPE='Forget' AND STATUS='Active' AND CREATED_DATE >? " +
	                   "ORDER BY CREATED_DATE DESC LIMIT 1";

	    String updateQuery = "UPDATE c_users SET mfaenabled='false' WHERE AccountID = ?";

	    try {
	        // Load MySQL Driver mfaenabled
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
	            accountId = rs.getString("ACCOUNTID");

	            System.out.println("MFA Code: " + mfaCode);
	            System.out.println("Account ID: " + accountId);

	            /* --------- NEW EDIT FUNCTIONALITY START --------- */
	            PreparedStatement updatePs = con.prepareStatement(updateQuery);
	            updatePs.setString(1, accountId);
	            int updatedRows = updatePs.executeUpdate();

	            System.out.println("MFA Disabled Successfully : " + updatedRows);
	            updatePs.close();
	            /* --------- NEW EDIT FUNCTIONALITY END --------- */
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
	    }
	    return mfaCode;
	}
	public  String getCurrentDbDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return now.format(formatter);
    }
	public String getUrl() {
		String zoneType = p.getProperty("zone");

	    if(zoneType.equalsIgnoreCase("new")) {
	        return p.getProperty("provisioning_url");
	    } else {
	        return p.getProperty("zone_url");
	    }
	}
	public void zoneLoginValidator() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(logObj.customer_lbl));
			System.out.println("Customer Logged in succesfully");
		}catch (Exception e) {
			System.out.println("Customer Logg in failed");
		}
	}
	public void LoginToZone(String email,String pwd) {
		logObj = new ZoneLoginPOC(driver);
		logObj.enterUsername(email);
		logObj.enterpassword(pwd);
		logObj.clickSignIn();
		zoneLoginValidator();
	}
	public void createZoneAccount() {

        zoneObj = new ZoneSelfProvisioningPOC(driver);
        welcomeObj = new ZoneWelcomePOC(driver);

        // Step 1 – Enter Details
        zoneObj.enterEmail(p.getProperty("email"));
        zoneObj.enterAccountName(p.getProperty("acc_name") + randomNumber(4));
        zoneObj.clickSubscribe();

        mfa_time = getCurrentDbDateTime();

        // Step 2 – Handle duplicate name if exists
        handleDuplicateAccountName();

        // Step 3 – Wait for account creation
        zoneObj.waitForAccountCreation();

        // Step 4 – Fetch MFA from DB
        String mfa = getMfaFromDB(
                mfa_time,
                p.getProperty("email"),
                p.getProperty("db_url"),
                p.getProperty("db_username"),
                p.getProperty("db_password")
        );

        // Step 5 – Enter MFA
        zoneObj.enterMFA(mfa);
        

        // Step 6 – Set Password
        zoneObj.enterPassword(p.getProperty("password"));
        zoneObj.clickSave();
        zoneObj.validateMfaIfInvalid();

        zoneObj.waitForSuccessPopup();
        zoneObj.clickSuccessOk();

        // -------------------------------
        // Provisioning Validation
        // -------------------------------

        subObj = new ZoneSubscriptionPage(driver);

        Assert.assertTrue(subObj.isSubscriptionActive(), "Subscription not Active");
        Assert.assertTrue(subObj.isProvisioningSuccessful(), "Provisioning not Successful");

        subObj.clickLandingIfVisible();
        welcomeObj.waitTillDashbordTitleVisibe();
    }

    // ----------------------------------------------------
    // Duplicate Handler (Safe Retry – Max 3 Attempts)
    // ----------------------------------------------------
    public void handleDuplicateAccountName() {

        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {

            if (zoneObj.isDuplicateAccountDisplayed()) {

                zoneObj.clearAccountName();
                zoneObj.enterAccountName(p.getProperty("acc_name") + randomNumber(4));
                zoneObj.clickSubscribe();

                attempts++;

            } else {
                break;
            }
        }

        if (attempts == maxAttempts) {
            throw new RuntimeException("Duplicate account name persists after retries");
        }
    }
	
	
	
}
