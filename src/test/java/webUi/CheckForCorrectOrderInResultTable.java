package webUi;

import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import selenium.steps.QuoteSteps;

public class CheckForCorrectOrderInResultTable extends BaseWebUITest
{
    @Autowired
    private QuoteSteps stepsForFeatureWithQuote;

    @Value("${data-for-search-trading}")
    private String dataForSearchTrading;

    @Value("${data-for-search}")
    private String dataForSearch;

    @Value("${choose-organisation-from-drop-down-for-search}")
    private String chooseOrganisationFromDropDownForSearch;

    @Test
    public void checkForCorrectOrderInResultTable()
    {stepsForFeatureWithQuote.openMainPage().chooseForQuoteWithOutSignIn(dataForSearch,
        chooseOrganisationFromDropDownForSearch);
        stepsForFeatureWithQuote.receiveResultTableWithMaxMinValue(dataForSearchTrading);
        boolean result = stepsForFeatureWithQuote.getValueMaxCostLeftColumn(dataForSearchTrading) >= stepsForFeatureWithQuote
                .getValueMinCostRightColumn(dataForSearchTrading);
        assertTrue(result);
    }
}
