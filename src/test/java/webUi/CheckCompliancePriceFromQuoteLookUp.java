package webUi;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import selenium.steps.QuoteSteps;

public class CheckCompliancePriceFromQuoteLookUp extends BaseWebUITest
{
    @Autowired
    private QuoteSteps stepsForFeatureWithQuote;

    @Value("${data-for-left-menu-search}")
    private String dataForLeftMenuSearch;

    @Value("${choose-organisation-from-menu}")
    private String chooseOrganisationFromMenu;

    @Value("${date-time-old-period}")
    private String dateTimeOldPeriod;

    @Value("${expected-result-price-start-trading}")
    private String expectedResultPriceStartTrading;

    @Value("${date-for-search-value-start-trading}")
    private String dateForSearchValueStartTrading;


    @Test
    public void checkForCorrectOrderInResultTable()
    {   stepsForFeatureWithQuote
        .openMainPage()
        .quoteLookUp(dataForLeftMenuSearch,chooseOrganisationFromMenu);
        Double actualResult = stepsForFeatureWithQuote.getValueOfPriceStartTrading(dateTimeOldPeriod,
                dateForSearchValueStartTrading);
        assertEquals(java.util.Optional.of(Double.parseDouble(expectedResultPriceStartTrading)), actualResult);
    }
}
