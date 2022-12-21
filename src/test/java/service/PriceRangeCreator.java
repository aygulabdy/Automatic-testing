package service;

public class PriceRangeCreator {
    public static final String TESTDATA_TOPRANGE_PRICE = "testdata.toprange.price";

    public static final String TESTDATA_LESSRANGE_PRICE = "testdata.lessrange.price";

    public static Double withCredentialsFromPropertyTopPrice() {
        return Double.parseDouble(TestDataReader.getTestData(TESTDATA_TOPRANGE_PRICE));
    }

    public static Double withCredentialsFromPropertyLessPrice() {
        return Double.parseDouble(TestDataReader.getTestData(TESTDATA_LESSRANGE_PRICE));
    }
}
