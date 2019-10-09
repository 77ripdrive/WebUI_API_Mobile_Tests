package mobile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OpenPageFalidation extends BaseMobile {

    @Test
    public void simpleDemoTest()
    {
        List <WebElement> suggestionCorpName = appiumDriver.findElementsByClassName(
                "androidx.recyclerview.widget.RecyclerView");

        actionsMobile.waitFluent(appiumDriver).until(appiumDriver -> suggestionCorpName.size() > ZERO_VALUE);

        Assertions.assertTrue(suggestionCorpName.size() > MIN_VALUE);
    }
}
