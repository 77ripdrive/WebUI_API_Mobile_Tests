package webUi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMainSearchValidation extends BaseWebUI {


    @Value("${data-for-search}")
    private String dataForSearch;

    @Value("${choose-organisation-from-drop-down-for-search}")
    private String chooseOrganisationFromDropDownForSearch;

    @Value("${expected-result-start-trading}")
    private String expectedResultStartTrading;


    @Test
    public void testFeatureHistoricalDataWithMaxTimePeriodData() {
        mainPage.open().chooseForQuoteWithOutSignIn(dataForSearch,
                chooseOrganisationFromDropDownForSearch);
        String actualResult = quotePage.receiveDateOfStart();
        assertEquals(expectedResultStartTrading, actualResult);
    }
}
