package webUi;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pages.MainPage;
import pages.QuotePage;
import spring.AppConfig;
import webDriver.IWebDriver;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class BaseWebUI {

    protected WebDriver driver;

    @Autowired
    private IWebDriver webDriverProvider;

    @Autowired
    protected MainPage mainPage;

    @Autowired
    protected QuotePage quotePage;

    @Value("${browser}")
    private String browser;

    @Value("${docker.enable}")
    private boolean dockerEnable;

    @SuppressFBWarnings
    @BeforeEach
    public void setUp() {

        driver = webDriverProvider.get();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        webDriverProvider.closeDriver();
    }
}
