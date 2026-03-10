package saasOpsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ZoneSelfProvisioningPOC extends BasePage {

    // constructor
    public ZoneSelfProvisioningPOC(WebDriver driver) {
        super(driver);
    }

    // ******************************************************************LOCATORS***************************************************************************************************************************************************************************

    public By email_txt = By.id("tenantProvisionForm:email");
    public By acc_name_txt = By.id("tenantProvisionForm:customerName");
    public By subscribe_btn = By.id("tenantProvisionForm:rootcreatebtnid");
    public By reset_btn = By.id("tenantProvisionForm:reset");
    public By acc_name_alrt = By.xpath("//p[text()='Account name already exists']");
    public By acc_creation_alrt = By.xpath("//span[text()='Creating Your Account']");
    public By mfa_page = By.xpath("//span[text()='Verify your Account']");
    public By mfa_txt = By.id("confirmPassworddialogForm:verificationCode");
    public By invalid_mfa = By.xpath("//span[text()='Entered verification code is invalid']");
    public By password_txt = By.id("confirmPassworddialogForm:newpassword");
    public By save_btn = By.id("confirmPassworddialogForm:loginForm465");
    public By success_msg = By.xpath("//label[text()='Success']");
    public By success_ok_btn = By.xpath("//span[text()='OK']");


    // ******************************************************************ACTIONS***************************************************************************************************************************************************************************

    public void enterEmail(String email) {
        WebElement element = wait.waitForElementToBeClickable(email_txt);
        element.clear();
        element.sendKeys(email);
    }

    public void enterAccountName(String acc_name) {
        WebElement element = wait.waitForElementToBeClickable(acc_name_txt);
        element.clear();
        element.sendKeys(acc_name);
    }

    public void clickSubscribe() {
        wait.waitForElementToBeClickable(subscribe_btn).click();
    }

    public void clickReset() {
        wait.waitForElementToBeClickable(reset_btn).click();
    }

    public void enterMFA(String mfa_code) {
        WebElement element = wait.waitForElementToBeClickable(mfa_txt,15);
        element.clear();
        element.sendKeys(mfa_code);
    }

    public void enterPassword(String pwd) {
        WebElement element = wait.waitForElementToBeClickable(password_txt);
        element.clear();
        element.sendKeys(pwd);
    }

    public void clickSave() {
        wait.waitForElementToBeClickable(save_btn).click();
    }

    public void clickSuccessOk() {
        wait.waitForElementToBeClickable(success_ok_btn).click();
    }
    public void waitForAccountCreation() {
        wait.waitForVisibility(acc_creation_alrt, 15);
    }

    public boolean isDuplicateAccountDisplayed() {
        return wait.isElementDisplayed(acc_name_alrt, 3);
    }

    public void clearAccountName() {
        driver.findElement(acc_name_txt).clear();
    }

    public void validateMfaIfInvalid() {
        if (wait.isElementDisplayed(invalid_mfa, 2)) {
            throw new RuntimeException("Invalid MFA entered");
        }
    }

    public void waitForSuccessPopup() {
        wait.waitForVisibility(success_msg, 10);
    }

}
