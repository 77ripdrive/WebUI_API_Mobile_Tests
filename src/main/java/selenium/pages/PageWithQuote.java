package selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import selenide.Actions;
import utils.LocatorCreator;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


@Component
public class PageWithQuote extends BaseWebPage
{
    private static final String PRE_LOCATOR_FOR_TABLE = "//*[@id='Col1-1-Financials-Proxy']//tr[%s]/td[2]/span";
    private static final String PRE_LOCATOR_FOR_DATE_SEARCH = "//span[text()='%s']";
    private static final String PRE_LOCATOR_FOR_MAX_VALUE = "//span[text()='%s']/following::td[2]/span";
    private static final String PRE_LOCATOR_FOR_MIN_VALUE = "//span[text()='%s']/following::td[3]/span";
    private static final String PRE_LOCATOR_FOR_START_VALUE = "//span[text()='%s']/following::td[1]/span";

    @FindBy(xpath = "//*[@id='quote-nav']//span[text()='Historical Data']")
    private SelenideElement choiceHistoricalData;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//input[@data-test='date-picker-full-range']")
    private SelenideElement choiceTimePeriod;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Max']")
    private SelenideElement choiceMaxTimePeriod;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//input[@name='startDate']")
    private SelenideElement dateOfStartTrading;

    @FindBy(xpath = "//*[@id='quote-nav']//span[text()='Profile']")
    private SelenideElement choiceProfile;

    @FindBy(xpath = "//*[@id='Col1-0-Profile-Proxy']//span[text()='Industry']")
    private SelenideElement fieldIndustry;

    @FindBy(xpath = "//*[@id='Col1-0-Profile-Proxy']//span[text()='Information Technology Services']")
    private SelenideElement valueFieldIndustry;

    @FindBy(xpath = "//*[@id='quote-header-info']//*[text()='Buy']")
    private SelenideElement buttonBuy;

    @FindBy(xpath = "//*[@id='fin-tradeit']//button[text()='Continue']")
    private SelenideElement buttonContinueInPopUpWindow;

    @FindBy(xpath = "//*[@id='fin-tradeit']//*[text()='Select a Broker']")
    private SelenideElement buttonHeaderInPopUpWindow;

    @FindBy(xpath = "//span[text()='Sign In']")
    private SelenideElement buttonSignInIs;

    @FindBy(xpath = "//*[@id='quote-nav']//span[text()='Financials']")
    private SelenideElement choiceFinancials;

    @FindBy(xpath = "//*[@id='Col1-1-Financials-Proxy']//span[text()='Cash Flow']")
    private SelenideElement fieldCashFlow;

    @FindBy(xpath = "//*[@id='Col1-1-Financials-Proxy']//tr[10]/td[2]/span")
    private SelenideElement fieldTotalCash;

    @FindBy(xpath = "//*[@id='Col1-1-Financials-Proxy']//span[text()='Operating Activities, Cash Flows Provided By or Used In']")
    private SelenideElement fieldOperatingActivities;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//input[@name='startDate']")
    private SelenideElement dataOfSearch;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Done']")
    private SelenideElement pushDoneButton;

    @FindBy(xpath = "//span[contains(text(), 'Holders')]")
    private SelenideElement holdersLink;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Daily']")
    private SelenideElement frequentlyDaily;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Weekly']")
    private SelenideElement frequentlyWeekly;

    @FindBy(xpath = "//*[@id='Col1-1-HistoricalDataTable-Proxy']//span[text()='Apply']")
    private SelenideElement buttonApply;

    public String receiveDateOfStartTrading()
    {
        choiceHistoricalData.click();
        choiceTimePeriod.click();
        choiceMaxTimePeriod.click();
        return dateOfStartTrading.getAttribute("value");
    }

    public void receiveDateOfMaxMinPrices(String dataOfTrade)
    {
        setDateOfTimePeriod(dataOfTrade);
        Actions.clickWithChoice(PRE_LOCATOR_FOR_DATE_SEARCH, Actions.dateFormat(dataOfTrade));
    }

    public String receiveValueOfStartTrading(String dateOldTime, String dateForSearch)
    {
        setDateOfTimePeriod(dateOldTime);
        frequentlyDaily.click();
        frequentlyWeekly.click();
        buttonApply.click();
        return $(LocatorCreator.createXpath(PRE_LOCATOR_FOR_START_VALUE, Actions.dateFormat(dateForSearch))).getText();
    }

    public void setDateOfTimePeriod(String dataOfTrade)
    {
        choiceHistoricalData.click();
        choiceTimePeriod.click();
        dataOfSearch.setValue(dataOfTrade);
        pushDoneButton.click();
    }

    public String valueMaxFromTable(String dataOfTrade)
    {
        return $(LocatorCreator.createXpath(PRE_LOCATOR_FOR_MAX_VALUE, Actions.dateFormat(dataOfTrade))).getText();
    }

    public String valueMinFromTable(String dataOfTrade)
    {
        return $(LocatorCreator.createXpath(PRE_LOCATOR_FOR_MIN_VALUE, Actions.dateFormat(dataOfTrade))).getText();
    }

    public String receiveValueFieldIndustry()
    {
        choiceProfile.click();
        return valueFieldIndustry.getText();
    }

    public Boolean receiveValueFromButton()
    {
        buttonBuy.click();
        return Actions.isDisplayed(buttonSignInIs);
    }

    public double receiveProfit()
    {
        choiceFinancials.click();
        fieldCashFlow.click();
        fieldOperatingActivities.shouldBe(Condition.visible);
        return Actions.sumTotalCashFlowFromOperatingActivities(PRE_LOCATOR_FOR_TABLE);
    }

    public double receiveTotalCashFlowFromOperatingActivities()
    {
        String actual = fieldTotalCash.getText().replace(",", "");
        return Double.parseDouble(actual);
    }

    public PageWithQuote clickHoldersLink()
    {
        holdersLink.click();
        return page(PageWithQuote.class);        
    }
}
