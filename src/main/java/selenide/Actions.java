package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LocatorCreator;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class Actions
{
    public static SelenideElement clickViaJs(SelenideElement selenideElement)
    {
        Configuration.clickViaJs = true;
        selenideElement.click();
        Configuration.clickViaJs = false;
        return selenideElement;
    }

    public static boolean isDisplayed(SelenideElement selenideElement)
    {
        return selenideElement.shouldBe(Condition.visible).isDisplayed();
    }

    public static void chooseElementFromList(List<WebElement> webElements, String option)
    {
        for (WebElement webElement : webElements)
        {
            if (webElement.getText().equalsIgnoreCase(option))
            {
                webElement.click();
                break;
            }
        }
    }

    public static void clickWithChoice(String preLocator, String chooseFromOrganisationForSearch)
    {
        Selenide.$(LocatorCreator.createXpath(preLocator, chooseFromOrganisationForSearch)).click();
    }

    public static boolean isLinkDisplayed(String link)
    {
        return isDisplayed($(LocatorCreator.createXpath("//a[@href='%s']", link)));
    }

    public static double sumTotalCashFlowFromOperatingActivities(String string)
    {
        double summ = 0;
        int[] lineNumbers = new int[] { 2, 4, 5, 6, 7, 8, 9 };
        for (int i = 0; i < lineNumbers.length; ++i)
        {
            By locator = By.xpath(String.format(string, lineNumbers[i]));
            String value = $(locator).getText().replace(",", "");
            summ = summ + Double.parseDouble(value);
        }
        return summ;
    }

    public static String dateFormat(String oldDateString)
    {
        LocalDate date = LocalDate.parse(oldDateString, DateTimeFormatter.ofPattern("M/dd/yyyy"));
        return date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy", new Locale("us")));
    }

    public static void chooseElementFromListViaCheckBox(List<? extends WebElement> webElementList, List<? extends WebElement> checkBox,
            String option)
    {
        for (int counter = 0; counter < webElementList.size(); counter++)
        {
            if (webElementList.get(counter).getText().contains(option))
            {
                checkBox.get(counter).click();
                break;
            }
        }
    }
}
