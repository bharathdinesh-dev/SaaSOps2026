package saasOpsPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import saasOpsUtilities.WaitUtils;

public class BasePage {
	WebDriver driver;
	WaitUtils wait;
	BasePage(WebDriver driver){
		this.driver=driver;
		this.wait=new WaitUtils(driver);
		PageFactory.initElements(driver, this);
	}
}
