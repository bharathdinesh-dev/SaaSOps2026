package saasOpsPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ZoneSelfProvisioningPOC extends BasePage{
//constructor 
	public ZoneSelfProvisioningPOC(WebDriver driver){
		super(driver);
	}
	
//	Locators
	@FindBy(id = "tenantProvisionForm:email") public WebElement email_txt;
	@FindBy(id = "tenantProvisionForm:customerName") public WebElement acc_name_txt;
	@FindBy(id = "tenantProvisionForm:rootcreatebtnid") public WebElement subscribe_btn;
	@FindBy(id = "tenantProvisionForm:reset") public WebElement reset_btn;
	@FindBy(xpath = "//p[text()='Account name already exists']") public WebElement acc_name_alrt;
	@FindBy(xpath = "//span[text()='Creating Your Account']") public WebElement acc_creation_alrt;
	@FindBy(xpath = "//span[text()='Verify your Account']") public WebElement mfa_page;
	@FindBy(id="confirmPassworddialogForm:verificationCode") public WebElement mfa_txt;
	@FindBy(xpath = "//span[text()='Entered verification code is invalid']") public WebElement invalid_mfa;
	@FindBy(id="confirmPassworddialogForm:newpassword") public WebElement password_txt;
	@FindBy(id="confirmPassworddialogForm:loginForm465") public WebElement save_btn;
	@FindBy(xpath = "//label[text()='Success']") public WebElement success_msg;
	@FindBy(xpath = "//span[text()='OK']") public WebElement success_ok_btn;
	
//	Actions
	public void enterEmail(String email) {
		email_txt.sendKeys(email);
	}
	
	public void enterAccountName(String acc_name) {
		acc_name_txt.sendKeys(acc_name);
	}
	
	public void clickSubscribe() {
		subscribe_btn.click();
	}
	
	public void clickReset() {
		reset_btn.click();
	}
	
	public void enterMFA(String mfa_code) {
		mfa_txt.sendKeys(mfa_code);
	}
	
	public void enterPassword(String pwd) {
		password_txt.sendKeys(pwd);
	}
	
	public void clickSave() {
		save_btn.click();
	}
	
	public void clickSuccessOk() {
		success_ok_btn.click();
	}
}
