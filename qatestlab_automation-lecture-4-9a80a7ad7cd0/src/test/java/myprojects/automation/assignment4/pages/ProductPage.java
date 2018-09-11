package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.utils.DriverWaiters;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductPage {

    private WebDriver webDriver;
    @FindBy (id = "subtab-AdminCatalog")

    private WebElement catalogMenu;
    @FindBy (xpath = "/html/body/nav/ul/li[4]/a/span")

    private WebElement catalogItem;
    @FindBy (id = "subtab-AdminProducts")

    private WebElement productItem;
    @FindBy (xpath = "//*[@id=\"main-div\"]/div[1]/h2")

    private WebElement headerProducts;
    @FindBy (id = "page-header-desc-configuration-add")

    private WebElement newProductBtn;
    @FindBy (id = "form_step1_name_1")

    private WebElement nameProduct;
    @FindBy (id = "form_step1_qty_0_shortcut")

    private WebElement qnyProduct;
    @FindBy (id = "form_step1_price_shortcut")

    private WebElement priceProduct;
    @FindBy (className = "switch-input")

    private WebElement toggleSwitch;
    @FindBy (className = "js-btn-save")

    private WebElement createAndSaveBtn;
    @FindBy (xpath = "//*[@id=\"form\"]/div[4]/div[2]/div/button[1]/span")

    private WebElement alertMessage;
    @FindBy (css = "#growls > .growl-notice")

    private WebElement alertSuccess;
    @FindBy (css = "#growls > .growl > .growl-close")

    private WebElement alertSuccessClose;


    public ProductPage (WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        webDriver.get(Properties.getBaseAdminUrl());
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, catalogMenu);
    }

    public void clickProductSubmenu() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(catalogMenu).build().perform();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, productItem);
        productItem.click();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, newProductBtn);
    }

    public void openNewProductPageByClick() {
       newProductBtn.click();
       DriverWaiters.wait10SecondsForVisibilityOf(webDriver, nameProduct);
    }

    public void fillNameOfProduct(String name) {
        nameProduct.sendKeys(name);
    }

    public void fillNumberOfProduct(int number) {
        qnyProduct.sendKeys(String.valueOf(number));
    }

    public void fillPriceOfProduct(String price) {

        priceProduct.clear();
        priceProduct.sendKeys(price);
    }

    public void toggleSwitcher() {
        toggleSwitch.click();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, alertMessage);
    }

    public void saveButton() {
        createAndSaveBtn.click();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, alertMessage);
    }

    public void closeAlert() {
        boolean staleElement = true;
        int tryCount = 0;
        while (staleElement && tryCount < 3) {
            try {
                alertSuccessClose.click();
                staleElement = false;
            } catch (StaleElementReferenceException e) {
                tryCount++;
                staleElement = true;
            }
        }
    }

    public boolean isDisplayedHeaderProducts() {
       return headerProducts.isDisplayed();
    }

    public boolean isSuccsessAlertDisplayed() {
        return alertSuccess.isDisplayed();
    }

    public void allProdPageIsDisplayed() {
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver,  catalogItem);
        Actions actions = new Actions(webDriver);
        actions.moveToElement((WebElement) catalogItem).build().perform();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver,  productItem);
        productItem.click();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver,  headerProducts);
        boolean isDisplayed = headerProducts.isDisplayed();
        Assert.assertTrue(isDisplayed, "Products header is not displayed");
    }

    public void newProdPageIsDisplayed() {
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, newProductBtn);
        newProductBtn.click();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, nameProduct);
        boolean isDisplayed = nameProduct.isDisplayed();
        Assert.assertTrue(isDisplayed, "Input for product title is not displayed");
    }

}

