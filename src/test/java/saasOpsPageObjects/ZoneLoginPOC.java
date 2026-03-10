package saasOpsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ZoneLoginPOC extends BasePage {

    public ZoneLoginPOC(WebDriver driver) {
        super(driver);
    }

    // ******************************************************************LOCATORS***************************************************************************************************************************************************************************

    public By username_txt = By.id("loginForm:username");
    public By password_txt = By.id("loginForm:password");
    public By signIn_btn = By.id("loginForm:loginButton");
    public By forgot_lnk = By.id("loginForm:forgot");
    public By create_new_acc_lnk = By.id("loginForm:provisionURLLink");
    public By home_btn = By.id("loginFormBottomButton:one");
    public By support_btn = By.id("loginFormBottomButton:two");
    public By marketplace_btn = By.id("loginFormBottomButton:marketLink");
    public By azure_SSO_btn = By.id("loginForm:azureAD");
    public By google_SSO_btn = By.id("loginForm:googlessologinForm:username");
    public By customer_lbl = By.xpath("//label[text()='Customer:']");


    // ******************************************************************ACTIONS***************************************************************************************************************************************************************************

    public void enterUsername(String username) {
        WebElement element = wait.waitForElementToBeClickable(username_txt);
        element.clear();
        element.sendKeys(username);
    }

    public void enterpassword(String pwd) {
        WebElement element = wait.waitForElementToBeClickable(password_txt);
        element.clear();
        element.sendKeys(pwd);
    }

    public void clickSignIn() {
        wait.waitForElementToBeClickable(signIn_btn).click();
    }

    public void clickForgot() {
        wait.waitForElementToBeClickable(forgot_lnk).click();
    }

    public void clickCreateNewAccLink() {
        wait.waitForElementToBeClickable(create_new_acc_lnk).click();
    }

    public void clickHome() {
        wait.waitForElementToBeClickable(home_btn).click();
    }

    public void clickFSupport() {
        wait.waitForElementToBeClickable(support_btn).click();
    }

    public void clickMarketplace() {
        wait.waitForElementToBeClickable(marketplace_btn).click();
    }

    public void clickAzureSSO() {
        wait.waitForElementToBeClickable(azure_SSO_btn).click();
    }

    public void clickGoogleSSO() {
        wait.waitForElementToBeClickable(google_SSO_btn).click();
    }

}

