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

public class ItemPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//button[@class='close js-close-cookie']")
    private WebElement buttonToCloseCookie;

    @FindBy(xpath = "//*[@id=\"inform\"]/button")
    private WebElement buttonCloseSaleWindow;

    @FindBy(xpath = "//button[@class='empty-btn new-cart']")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//button[@class='inc qty-button not-null']")
    private WebElement buttonIncreaseQuantityOfItem;

    private By pathToCloseCookieButton = By.xpath("//button[@class='close js-close-cookie']");

    private By pathToCloseSaleWindow = By.xpath("//*[@id='inform']/button");

    private By pathToAddToCart = By.xpath("//button[@class='empty-btn new-cart']");

    private By pathToIncreaseQuantity = By.xpath("//button[@class='inc qty-button not-null']");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage addItemToCart() {
        Waits.getWebElementUntilWait(driver, pathToAddToCart);
        buttonAddToCart.click();
        logger.info("addItemToCart. button add to cart clicked");
        return this;
    }

    public ItemPage changeQuantityOfItemsInCart(int count) {
        Waits.getWebElementUntilWait(driver, pathToIncreaseQuantity);
        for(int i = 1; i < count; i++) {
            buttonIncreaseQuantityOfItem.click();
        }
        return this;
    }

    @Override
    public ItemPage openPage() {
        Item testItem = ItemCreator.withCredentialsFromProperty();
        driver.get(testItem.getItemURL());
        Waits.getWebElementUntilClickableWait(driver, pathToCloseSaleWindow);
        buttonCloseSaleWindow.click();
        Waits.getWebElementUntilWait(driver, pathToCloseCookieButton);
        buttonToCloseCookie.click();
        logger.info("Cookie window in item page closed");
        return this;
    }
}
