package selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import selenide.Actions;


import static com.codeborne.selenide.Selenide.page;

@Component
public class RecentQuotes extends BaseWebPage
{
    private static final String PRE_LOCATOR_FOR_RECENT_QUOTE = "//*[@id='fin-srch-assist']//div[text()='%s']";

    @FindBy(xpath = "//*[@id='yfin-usr-qry']")
    private SelenideElement searchField;

    @FindBy(xpath = "//*[@id='yfin-list']//span[text()='Name']")
    private SelenideElement nameField;

    public PageWithQuote chooseFromSearch(String organisationForSearch)
    {
        nameField.shouldBe(Condition.visible);
        searchField.setValue(organisationForSearch);
        Actions.clickWithChoice(PRE_LOCATOR_FOR_RECENT_QUOTE, organisationForSearch);
        return page(PageWithQuote.class);
    }
}