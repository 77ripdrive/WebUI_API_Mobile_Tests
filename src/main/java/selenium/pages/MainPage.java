package selenium.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import selenide.Actions;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

@Component
public class MainPage extends BaseWebPage {

    private static final String BASE_URL = "https://finance.yahoo.com/";
    private static final String PRE_LOCATOR_FOR_QUOTE = "//div[contains(text(),'%s')]";
    private static final String PRE_LOCATOR_FOR_LEFT_MENU_QUOTE = "//*[@id='Col2-0-SymbolLookup-Proxy']//span[text()='%s']";

    @FindBy(id = "uh-signedin")
    private SelenideElement linkSignIn;

    @FindBy(id = "uh-profile")
    private SelenideElement logoUser;

    @FindBy(xpath = "//a[@title='My Portfolio']")
    private SelenideElement buttonMyPortfolio;

    @FindBy(xpath = "//li/a[@title='Markets']")
    private SelenideElement marketsLink;

    @FindBy(css = "a[title='Markets']~div.nr-applet-subnav a[title='Cryptocurrencies']")
    private SelenideElement cryptocerrenciesLink;

    @FindBy(css = ".btn.primary")
    private static List <SelenideElement> buttonOkAlert;

    @FindBy(xpath = "//*[@id='yfin-usr-qry']")
    private SelenideElement searchForNews;

    @FindBy(xpath = "//a[@title='Industries']")
    private SelenideElement industriesLink;

    @FindBy(xpath = "//*[@id='data-util-col']//a[@title='Recently Viewed']")
    private SelenideElement recentlyViewed;

    @FindBy(xpath = "//*[@id='Col2-0-SymbolLookup-Proxy']//input[@placeholder='Quote Lookup']")
    private SelenideElement quoteLookup;

    public static MainPage open()
    {
        Selenide.open(BASE_URL, MainPage.class);
        return Selenide.page(MainPage.class);
    }

    public PageWithQuote selectionDesiredResult(String organisationForSearch, String chooseFromOrganisationForSearch)
    {
        searchForNews.setValue(organisationForSearch);
        Actions.clickWithChoice(PRE_LOCATOR_FOR_QUOTE, chooseFromOrganisationForSearch);
        return page(PageWithQuote.class);
    }

    public PageWithQuote selectionDesiredResultFromQuoteLookup(String organisationForSearch, String chooseFromDropDown)
    {
        quoteLookup.setValue(organisationForSearch);
        Actions.clickWithChoice(PRE_LOCATOR_FOR_LEFT_MENU_QUOTE, chooseFromDropDown);
        return page(PageWithQuote.class);
    }

    public RecentQuotes recentViewed()
    {
        recentlyViewed.click();
        return Selenide.page(RecentQuotes.class);
    }
}
