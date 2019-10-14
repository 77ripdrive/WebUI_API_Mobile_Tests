package webUi;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import selenium.steps.QuoteSteps;

public class CheckComplianceValuePriceStartTrading extends BaseWebUITest {
    @Autowired
    private QuoteSteps stepsForFeatureWithQuote;


    @Value("${data-for-recent-quote-page}")
    private String dataForRecentQuotePage;

    @Value("${date-time-old-period}")
    private String dateTimeOldPeriod;

    @Value("${date-for-search-value-start-trading}")
    private String dateForSearchValueStartTrading;

    @Value("${expected-result-price-start-SP500}")
    private String expectedResultPriceStartSP500;


    @BeforeEach
    void preCondition() {
        stepsForFeatureWithQuote
            .recentQuote()
            .chooseOrganisation(dataForRecentQuotePage);
    }

    @Test
    public void checkForCorrectOrderInResultTable() {
        Double actualResult = stepsForFeatureWithQuote.getValueOfPriceStartTrading(dateTimeOldPeriod,
            dateForSearchValueStartTrading);
        assertEquals(java.util.Optional.of(Double.parseDouble(expectedResultPriceStartSP500)), actualResult);
    }
}
