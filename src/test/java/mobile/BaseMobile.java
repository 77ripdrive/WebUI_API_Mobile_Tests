package mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import mobile.utils.Action;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.AppConfig;
import utils.ResourceUtils;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class BaseMobile {
    protected final static int MIN_VALUE = 1;
    protected final static int ZERO_VALUE = 0;

    private String prefix = "sauce-labs.";

    @Autowired
    private ConfigurableEnvironment myEnv;

    @Autowired
    protected Action actionsMobile;

    @Autowired
    protected ResourceUtils resourceUtils;

    @Value("${android.host.sauce.lab}")
    private String getHub;

    protected AppiumDriver appiumDriver;

    @BeforeAll
    public void beforeTest() throws MalformedURLException {
        URL EU_endpoint = new URL(format("http://%s/wd/hub", getHub));
        appiumDriver = new AndroidDriver(EU_endpoint, resourceUtils.setUpAppiumDriver(myEnv, prefix));
    }

    @AfterAll
    public void afterTest() {
        appiumDriver.quit();
    }
}
