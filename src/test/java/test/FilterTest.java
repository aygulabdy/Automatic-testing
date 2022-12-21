package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.ItemsListPage;
import service.PriceRangeCreator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest extends CommonConditions {

    private final Logger logger = LogManager.getRootLogger();

    private final String ASCENDING_PRICE_URL = "https://shop.huawei.by/smart/?sort=p.price&order=ASC";

    private final String DESCENDING_PRICE_URL = "https://shop.huawei.by/smart/?sort=p.price&order=DESC";

    private final String PRICE_RANGE_URL = "https://shop.huawei.by/smart/price/499-999/";

    @Test
    public void testFilterForAscendingPrice() {
        logger.info("Start test-1: testFilterForAscendingPrice");
        driver.get(ASCENDING_PRICE_URL);
        List<Double> pricesFromPage = new ItemsListPage(driver)
                .getPricesFromList();
        logger.info(pricesFromPage.toString());
        assertThat(pricesFromPage)
                .as("sequence of prices similar")
                .containsSequence(pricesFromPage.stream().sorted().collect(Collectors.toList()))
                .as("prices sequence have similar size")
                .hasSize(pricesFromPage.stream().sorted().collect(Collectors.toList()).size());
    }

    @Test
    public void testFilterForDescendingPrice() {
        logger.info("Start test-2: testFilterForDescendingPrice");
        driver.get(DESCENDING_PRICE_URL);
        List<Double> pricesFromPage = new ItemsListPage(driver)
                .getPricesFromList();
        logger.info(pricesFromPage.toString());
        assertThat(pricesFromPage)
                .as("sequence of prices similar")
                .containsSequence(pricesFromPage
                        .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()))
                .as("prices sequence have similar size")
                .hasSize(pricesFromPage
                        .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).size());
    }

    @Test
    public void testFilterForPriceRange() {
        logger.info("Start test-3: testFilterForPriceRange");
        double priceTopRange = PriceRangeCreator.withCredentialsFromPropertyTopPrice();
        double priceLessRange = PriceRangeCreator.withCredentialsFromPropertyLessPrice();
        driver.get(PRICE_RANGE_URL);
        List<Double> pricesFromPage = new ItemsListPage(driver)
                .getPricesFromList();
        logger.info(pricesFromPage.toString() + " Top price: " + priceTopRange + " Less price: " + priceLessRange);
        assertThat(pricesFromPage)
                .as("Price less than")
                .allMatch(it -> it <= priceTopRange)
                .as("price greater than")
                .allMatch(it -> it >= priceLessRange);
    }
}
