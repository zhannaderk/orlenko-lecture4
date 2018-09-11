package myprojects.automation.assignment4.pages;
import lombok.Getter;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * For login page.
 */
public class AdminLoginPage {

    private WebDriver webDriver;

    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "passwd")
    private WebElement passInput;

    @FindBy(xpath = "//*[@id=\"login_form\"]/div[3]/button")
    private WebElement  loginBtn;

    @Getter
    private WebElement logo;

    public AdminLoginPage (WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        webDriver.get(Properties.getBaseAdminUrl());
    }

    public void fillEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public void fillPasswordInput(String password) {
        passInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginBtn.click();
    }
}
