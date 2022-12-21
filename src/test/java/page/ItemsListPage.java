package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.StringCreator;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;

public class ItemsListPage extends AbstractPage{

    private final String BASE_URL = "https://shop.huawei.by/smart/";

    @FindBy(xpath = "//div[@class='product-item__price']")
    private List<WebElement> priceInfoText;

    private final By pathToPriceInfoText = By.xpath("//div[@class='product-item__price']");

    public ItemsListPage(WebDriver driver) {
        super(driver);
    }

    public List<Double> getPricesFromList() {
        List<Double> prices = new ArrayList<>();
        Waits.getWebElementUntilWait(driver, pathToPriceInfoText);
        for (WebElement i : priceInfoText){
            prices.add(Double.parseDouble(new StringCreator().getPriceText(i.getText())));
        }
        return prices;
    }

    @Override
    public ItemsListPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

}
