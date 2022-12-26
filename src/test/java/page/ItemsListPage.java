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

    @FindBy(xpath = "//*[@id=\"inform\"]/button")
    private WebElement buttonCloseSaleWindow;

    @FindBy(xpath = "//div[@class='product-item__price']")
    private List<WebElement> priceInfoText;

    private final By pathToPriceInfoText = By.xpath("//div[@class='product-item__price']");

    private By pathToCloseSaleWindow = By.xpath("//*[@id='inform']/button");

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
        Waits.getWebElementUntilClickableWait(driver, pathToCloseSaleWindow);
        buttonCloseSaleWindow.click();
        return this;
    }

}
