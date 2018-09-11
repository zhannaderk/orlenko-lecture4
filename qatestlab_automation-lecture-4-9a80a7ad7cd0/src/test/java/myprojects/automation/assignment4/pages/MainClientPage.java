package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.DriverWaiters;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class MainClientPage {

    private WebDriver webDriver;

    @FindBy(className = "all-product-link")
    private WebElement allProducts;
    @FindBy(className = "logo")
    private WebElement logoImage;
    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/h1")
    private WebElement mainPageBlock;
    @FindBy(className = "ui-autocomplete-input")
    private WebElement searchField ;
    @FindBy(xpath = "//*[@id=\"main\"]/h2")
    private WebElement searchResults;
    @FindBy(id = "products")
    private WebElement products;
    @FindBy(css = "#wrapper > div > nav > ol > li:nth-child(2) > a > span")
    private WebElement productName;
    @FindBy(css = "#wrapper > div > nav > ol > li:nth-child(2) > a > span")
    private WebElement productPrice;
    @FindBy(css = "#product-details > div.product-quantities > span")
    private WebElement productQte;


    public void open() {
        webDriver.get(Properties.getBaseUrl());
       DriverWaiters.wait10SecondsForVisibilityOf(webDriver, logoImage);
    }


    public MainClientPage (WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickByLinkAllProd() {
        allProducts.click();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, products);
    }

    public void search(ProductData productData) {
        searchField.sendKeys(productData.getName());
        searchField.submit();
        DriverWaiters.wait10SecondsForVisibilityOf(webDriver, searchResults);
    }

    public boolean isPresentProduct(ProductData productData) {
        return products.getText().contains(productData.getName());
    }

    public void clickOnProduct(ProductData productData) {
        webDriver.findElement(By.linkText(productData.getName())).click();
    }

    public String getProductName() {
        return productName.getText();
    }

    public boolean containProductQty(int number) {
        return productQte.getText().contains(String.valueOf(number));
    }

    public boolean containsProductPrice(String price) {
        return productPrice.getText().contains(price);
    }
}

