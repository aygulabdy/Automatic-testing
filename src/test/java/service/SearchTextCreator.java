package service;

public class SearchTextCreator {
    public static final String TESTDATA_VALID_NAME = "testdata.valid.search";

    public static final String TESTDATA_INVALID_NAME = "testdata.invalid.search";

    public static String withCredentialsFromPropertyInvalid() {

        return TestDataReader.getTestData(TESTDATA_INVALID_NAME);
    }

    public static String withCredentialsFromPropertyValid() {
        return TestDataReader.getTestData(TESTDATA_VALID_NAME);
    }
}
