package page;

import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.ItemCreator;
import utils.Waits;

import java.util.List;

public class CartPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();

    private final String CART_PAGE_URL = "https://shop.huawei.by/simplecheckout/";

    @FindBy(xpath = "//td/div/div/a[@class='acc-item-name']")//have similar non touch elements
    private List<WebElement> listOfItemInCart;

    @FindBy(xpath = "//*[@id=\"inform\"]/button")
    private WebElement buttonCloseSaleWindow;

    @FindBy(xpath = "//div[@class='content']")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//div[@class='quantity-block']/div/input[@class='form-control']")
    private WebElement quantityOfItemInCart;

    @FindBy(xpath = "//button[@class='del-good']")
    private WebElement buttonToDelete;

    private final By pathToItemNamesInCart = By.xpath("//td/div/div/a[@class='acc-item-name']"); //have similar non touch elements

    private By pathToCloseSaleWindow = By.xpath("//*[@id='inform']/button");

    private final By pathToInfoOfQuantity = By.xpath("//div[@class='quantity-block']/div/input[@class='form-control']");

    private final By pathToDeleteButton = By.xpath("//button[@class='del-good']");

    private final By pathToEmptyMessage = By.xpath("//div[@class='content']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartInfo() {
        Waits.getWebElementUntilWait(driver, pathToItemNamesInCart);
        String listOfItemNamesInCart = "";
        for(WebElement i : listOfItemInCart) {
            listOfItemNamesInCart += i.getText() + "\n";
        }
        logger.info("items in cart: " + listOfItemNamesInCart);
        return listOfItemNamesInCart;
    }

    public String getEmptyCartMessage() {
        Waits.getWebElementUntilWait(driver, pathToEmptyMessage);
        if(isCartEmpty()){
            logger.info("");
            return emptyCartMessage.getText();
        }
        else return null;
    }

    public int getQuantityOfItem() {
        Waits.getWebElementUntilWait(driver, pathToInfoOfQuantity);
        logger.info("Get quantity of item: " + Integer.parseInt(quantityOfItemInCart.getAttribute("value")));
        return Integer.parseInt(quantityOfItemInCart.getAttribute("value"));
    }

    public CartPage deleteItems() {
        Waits.getWebElementUntilWait(driver, pathToDeleteButton);
        logger.info("Start to delete items:");
        buttonToDelete.click();
        logger.info("Delete item");
        return this;
    }

    private boolean isCartEmpty() {
        return listOfItemInCart.isEmpty();
    }

    @Override
    public CartPage openPage() {
        driver.get(CART_PAGE_URL);
        Waits.getWebElementUntilClickableWait(driver, pathToCloseSaleWindow);
        buttonCloseSaleWindow.click();
        logger.info("Cookie window in item page closed");
        return this;
    }
}
