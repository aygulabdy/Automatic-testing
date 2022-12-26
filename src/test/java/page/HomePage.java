package page;

import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.ItemCreator;
import utils.Waits;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage  extends AbstractPage {

    private final String HOME_PAGE_URL = "https://shop.huawei.by/";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id=\"inform\"]/button")
    private WebElement buttonCloseSaleWindow;

    @FindBy(xpath = "//button[@class='close js-close-cookie']")
    private WebElement buttonToCloseCookie;

    @FindBy(xpath = "//form[@id='search']/span")
    private WebElement buttonToSearch;

    @FindBy(xpath = "//input[@class='searchbox-input form-control input-lg']")
    private WebElement inputBoxOfSearch;

    @FindBy(xpath = "//a[@class='acc-item-name']")
    private List<WebElement> itemSearchResult;

    @FindBy(xpath = "//div[@class='search-page-results']")
    private WebElement noSearchTextBox;

    private By pathToNoSearchTextBox = By.xpath("//div[@class='search-page-results']");

    private By pathToCloseSaleWindow = By.xpath("//*[@id='inform']/button");

    private By pathToSearchResult = By.xpath("//a[@class='acc-item-name']");

    private By pathToSearchBox = By.xpath("//input[@class='searchbox-input form-control input-lg']");

    private By pathToSearchButton = By.xpath("//form[@id='search']/span");

    private By pathToCloseCookieButton = By.xpath("//button[@class='close js-close-cookie']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage searchByText(String textToSearch) {
        Waits.getWebElementUntilWait(driver, pathToSearchButton);
        buttonToSearch.click();
        Waits.getWebElementUntilClickableWait(driver, pathToSearchBox);
        inputBoxOfSearch.sendKeys(textToSearch);
        inputBoxOfSearch.sendKeys(Keys.ENTER);
        Waits.getWebElementUntilClickableWait(driver, pathToCloseSaleWindow);
        buttonCloseSaleWindow.click();
        return this;
    }

    public List<String> getNamesOfSearch() {
        Waits.getWebElementUntilClickableWait(driver, pathToSearchResult);
        logger.info("search name count start");
        return itemSearchResult
                .stream().map(it -> it.getText().toLowerCase()).collect(Collectors.toList());
    }

    public String getEmptySearchText() {
        Waits.getWebElementUntilClickableWait(driver, pathToNoSearchTextBox);
        logger.info("get empty serach message");
        return noSearchTextBox.getText();
    }

    @Override
    public HomePage openPage() {
        driver.get(HOME_PAGE_URL);
        Waits.getWebElementUntilClickableWait(driver, pathToCloseSaleWindow);
        buttonCloseSaleWindow.click();
        Waits.getWebElementUntilWait(driver, pathToCloseCookieButton);
        buttonToCloseCookie.click();
        logger.info("Cookie window in item page closed");
        return this;
    }
}
