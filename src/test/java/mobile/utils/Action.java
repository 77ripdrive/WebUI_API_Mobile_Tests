package mobile.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;

@Component
public class Action {
    private final static int TIME_OUT = 1;

    public Wait waitFluent(AppiumDriver appiumDriver) {
        return new FluentWait <>(appiumDriver).withMessage("Page was not loaded").pollingEvery(ofSeconds(TIME_OUT))
                .withTimeout(ofMinutes(TIME_OUT));
    }

}
