package selenium.steps;

import com.codeborne.selenide.Selenide;

import org.springframework.stereotype.Component;
import selenium.pages.MainPage;
import selenium.pages.PageWithQuote;
import selenium.pages.RecentQuotes;

@Component
public class QuoteSteps
{
    private MainPage mainPage;

    private RecentQuotes recentQuotes;

    private PageWithQuote pageWithQuote;

    public QuoteSteps openMainPage()
    {
        MainPage.open();
        return this;
    }

    public void chooseForQuoteWithOutSignIn(String organisationForSearch, String chooseFromOrganisationForSearch)
    {
        mainPage = Selenide.page(MainPage.class);
        mainPage.selectionDesiredResult(organisationForSearch, chooseFromOrganisationForSearch);
    }

    public String receiveDateOfStart()
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return pageWithQuote.receiveDateOfStartTrading();
    }

    public String receiveValueFieldOfIndusry()
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return pageWithQuote.receiveValueFieldIndustry();
    }

    public Boolean receiveValueFromButton()
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return pageWithQuote.receiveValueFromButton();
    }

    public double receiveSummTotalCashFlowFromOperatingActivities()
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return pageWithQuote.receiveProfit();
    }

    public double actualResultSummFromTable()
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return pageWithQuote.receiveTotalCashFlowFromOperatingActivities();
    }

    public void receiveResultTableWithMaxMinValue(String dataOfTrade)
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        pageWithQuote.receiveDateOfMaxMinPrices(dataOfTrade);
    }

    public Double getValueMaxCostLeftColumn(String dataOfTrade)
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return Double.parseDouble(pageWithQuote.valueMaxFromTable(dataOfTrade));
    }

    public Double getValueMinCostRightColumn(String dataOfTrade)
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        return Double.parseDouble(pageWithQuote.valueMinFromTable(dataOfTrade));
    }

    public QuoteSteps quoteLookUp(String organisationForSearch, String chooseFromOrganisationForSearch)
    {
        mainPage = Selenide.page(MainPage.class);
        mainPage.selectionDesiredResultFromQuoteLookup(organisationForSearch, chooseFromOrganisationForSearch);
        return this;
    }

    public QuoteSteps recentQuote()
    {
        mainPage = Selenide.page(MainPage.class);
        mainPage.recentViewed();
        return this;
    }

    public QuoteSteps chooseOrganisation(String organisationForSearch)
    {
        recentQuotes = Selenide.page(RecentQuotes.class);
        recentQuotes.chooseFromSearch(organisationForSearch);
        return this;
    }

    public Double getValueOfPriceStartTrading(String dateOldTime, String dateForSearch)
    {
        pageWithQuote = Selenide.page(PageWithQuote.class);
        String result = pageWithQuote.receiveValueOfStartTrading(dateOldTime, dateForSearch).replace(",", "");
        return Double.parseDouble(result);
    }
}
