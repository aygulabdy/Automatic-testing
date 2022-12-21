package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.HomePage;
import service.SearchTextCreator;
import utils.StringCreator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends CommonConditions {

    private final Logger logger = LogManager.getRootLogger();

    @Test
    public void validSearchTest() {
        logger.info("Start test-1: validSearchTest");
        String validSearchText = SearchTextCreator.withCredentialsFromPropertyValid();
        new HomePage(driver)
                .openPage()
                .searchByText(validSearchText);
        List<String> actualSearchItemName = new HomePage(driver)
                .getNamesOfSearch();
        logger.info("Get next item names: " + actualSearchItemName.toString());
        assertThat(actualSearchItemName)
                .as("all items contains search text")
                .allMatch(it -> it.contains(validSearchText))
                .as("searching not null")
                .hasSizeGreaterThan(0);
    }

    @Test
    public void invalidSearchTest() {
        logger.info("Start test-2: invalidSearchTest");
        String invalidSearchText = SearchTextCreator.withCredentialsFromPropertyInvalid();
        new HomePage(driver)
                .openPage()
                .searchByText(invalidSearchText);
        String actualSearchItemName = new HomePage(driver)
                .getEmptySearchText();
        logger.info("Get next message: " + actualSearchItemName.toString());
        assertThat(actualSearchItemName)
                .as("empty string search")
                .contains(new StringCreator().createEmptyMessageString(invalidSearchText));
    }
}
