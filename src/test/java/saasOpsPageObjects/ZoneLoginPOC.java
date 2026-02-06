package saasOpsPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ZoneLoginPOC extends BasePage{

	public ZoneLoginPOC(WebDriver driver){
		super(driver);
	}

	//Locators
	@FindBy(id = "loginForm:username") WebElement username_txt;
	@FindBy(id = "loginForm:password") WebElement password_txt;
	@FindBy(id = "loginForm:loginButton") WebElement signIn_btn;
	@FindBy(id = "loginForm:forgot") WebElement forgot_lnk;
	@FindBy(id = "loginForm:provisionURLLink") WebElement create_new_acc_lnk;
	@FindBy(id = "loginFormBottomButton:one") WebElement home_btn;
	@FindBy(id = "loginFormBottomButton:two") WebElement support_btn;
	@FindBy(id = "loginFormBottomButton:marketLink") WebElement marketplace_btn;
	@FindBy(id = "loginForm:azureAD") WebElement azure_SSO_btn;
	@FindBy(id = "loginForm:googlessologinForm:username") WebElement google_SSO_btn;
	@FindBy(xpath = "//label[text()='Customer:']")public WebElement customer_lbl;
	
//	Actions
	public void enterUsername(String username) {
		username_txt.sendKeys(username);
	}
	public void enterpassword(String pwd) {
		password_txt.sendKeys(pwd);
	}
	public void clickSignIn() {
		signIn_btn.click();
	}
	public void clickForgot() {
		forgot_lnk.click();
	}
	public void clickCreateNewAccLink() {
		create_new_acc_lnk.click();
	}
	public void clickHome() {
		home_btn.click();
	}
	public void clickFSupport() {
		support_btn.click();
	}
	public void clickMarketplace() {
		marketplace_btn.click();
	}
	public void clickAzureSSO() {
		azure_SSO_btn.click();
	}
	public void clickGoogleSSO() {
		google_SSO_btn.click();
	}
	
	
	
}
