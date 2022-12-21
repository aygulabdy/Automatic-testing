package test;

import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.CartPage;
import page.ItemPage;
import service.ItemCreator;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends CommonConditions {

    private final Logger logger = LogManager.getRootLogger();

    private String expectedEmptyCartMessage = "Ваша корзина пуста";

    @Test
    public void addItemIntoCart() {
        logger.info("Start test-1: adding item to cart");
        Item testItem = ItemCreator.withCredentialsFromProperty();
        new ItemPage(driver)
                .openPage()
                .addItemToCart();
        String itemsInCart = new CartPage(driver)
                .openPage()
                .getCartInfo();
        assertThat(itemsInCart)
                .as("find expected name in list of names")
                .contains(testItem.getItemName());
    }

    @Test
    public void checkEmptyCart() {
        logger.info("Start test-2: checking message in empty cart");
        String actualCartMessage = new CartPage(driver)
                .openPage()
                .getEmptyCartMessage();
        assertThat(actualCartMessage)
                .as("check messages in cart and expected message")
                .contains(expectedEmptyCartMessage);
    }

    @Test
    public void addMoreItemToCart() {
        logger.info("Start test-3: adding more item to cart");
        Item testItem = ItemCreator.withCredentialsFromProperty();
        new ItemPage(driver)
                .openPage()
                .changeQuantityOfItemsInCart(testItem.getItemCount())
                .addItemToCart();
        int actualQuantityOfItemInCart = new CartPage(driver)
                .openPage()
                .getQuantityOfItem();
        assertThat(actualQuantityOfItemInCart)
                .as("test count of item and quantity in input form")
                .isEqualTo(testItem.getItemCount());
    }

    @Test
    public void deleteItemFromCart() {
        logger.info("Start test-4: delete item to cart");
        Item testItem = ItemCreator.withCredentialsFromProperty();
        new ItemPage(driver)
                .openPage()
                .addItemToCart();
        String actualCartMessage = new CartPage(driver)
                .openPage()
                .deleteItems()
                .getEmptyCartMessage();
        assertThat(actualCartMessage)
                .as("checking by empty message")
                .contains(expectedEmptyCartMessage);
    }
}
