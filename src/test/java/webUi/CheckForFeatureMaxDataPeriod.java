package webUi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import sand.grasshopper.application.steps.ui.QuoteSteps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class CheckForFeatureMaxDataPeriod extends AbstractTest
{
    @Autowired
    private QuoteSteps stepsForFeatureWithQuote;

    @Value("${data-for-search}")
    private String dataForSearch;

    @Value("${choose-organisation-from-drop-down-for-search}")
    private String chooseOrganisationFromDropDownForSearch;

    @Value("${expected-result-start-trading}")
    private String expectedResultStartTrading;

    @BeforeEach
    void preCondition()
    {
        stepsForFeatureWithQuote.openMainPage().chooseForQuoteWithOutSignIn(dataForSearch,
                chooseOrganisationFromDropDownForSearch);
    }

    @Test
    public void testFeatureHistoricalDataWithMaxTimePeriodData()
    {
        String actualResult = stepsForFeatureWithQuote.receiveDateOfStart();
        assertEquals(expectedResultStartTrading, actualResult);
    }
}
