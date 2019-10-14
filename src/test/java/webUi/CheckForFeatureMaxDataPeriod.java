package webUi;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import selenium.steps.QuoteSteps;

public class CheckForFeatureMaxDataPeriod extends BaseWebUITest {
    @Autowired
    private QuoteSteps stepsForFeatureWithQuote;

    @Value("${data-for-search}")
    private String dataForSearch;

    @Value("${choose-organisation-from-drop-down-for-search}")
    private String chooseOrganisationFromDropDownForSearch;

    @Value("${expected-result-start-trading}")
    private String expectedResultStartTrading;


    @Test
    public void testFeatureHistoricalDataWithMaxTimePeriodData() {
        stepsForFeatureWithQuote.openMainPage().chooseForQuoteWithOutSignIn(dataForSearch,
            chooseOrganisationFromDropDownForSearch);
        String actualResult = stepsForFeatureWithQuote.receiveDateOfStart();
        assertEquals(expectedResultStartTrading, actualResult);
    }
}
