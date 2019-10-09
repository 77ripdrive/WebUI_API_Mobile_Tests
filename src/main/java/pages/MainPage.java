package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class MainPage extends BaseWebPage {

    private static final String BASE_URL = "https://finance.yahoo.com/";
    private static final String PRE_LOCATOR_FOR_QUOTE = "//div[contains(text(),'%s')]";
    private static final String PRE_LOCATOR_FOR_LEFT_MENU_QUOTE = "//*[@id='Col2-0-SymbolLookup-Proxy']//span[text()='%s']";

    @FindBy(id = "uh-signedin")
    private WebElement linkSignIn;

    @FindBy(xpath = "//*[@id='yfin-usr-qry']")
    private WebElement searchForNews;

    @FindBy(xpath = "//a[@title='Industries']")
    private WebElement industriesLink;

    @FindBy(xpath = "//*[@id='data-util-col']//a[@title='Recently Viewed']")
    private WebElement recentlyViewed;

    @FindBy(xpath = "//*[@id='Col2-0-SymbolLookup-Proxy']//input[@placeholder='Quote Lookup']")
    private WebElement quoteLookup;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public MainPage open() {
        driver.get(BASE_URL);
        return new MainPage(driver);
    }
    public QuotePage chooseForQuoteWithOutSignIn(String dataForSearch,
                                                 String chooseOrganisationFromDropDownForSearch){

        return new QuotePage(driver);
    }

}
