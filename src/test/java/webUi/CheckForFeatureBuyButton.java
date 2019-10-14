package webUi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import selenium.steps.QuoteSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckForFeatureBuyButton extends BaseWebUITest
{
    @Autowired
    private QuoteSteps stepsForFeatureWithQuote;

    @Value("${data-for-search}")
    private String dataForSearch;

    @Value("${choose-organisation-from-drop-down-for-search}")
    private String chooseOrganisationFromDropDownForSearch;

    @Test
    public void testFeatureBuyIsSignInButtonPresent()
    {   stepsForFeatureWithQuote.openMainPage().chooseForQuoteWithOutSignIn(dataForSearch,
                chooseOrganisationFromDropDownForSearch);
        assertTrue(stepsForFeatureWithQuote.receiveValueFromButton());
    }
}
