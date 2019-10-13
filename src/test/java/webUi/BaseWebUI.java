package webUi;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import selenium.pages.MainPage;
import selenium.pages.QuotePage;
import spring.AppConfig;
import webDriver.IWebDriverProvider;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
//@Execution(ExecutionMode.CONCURRENT)
public class BaseWebUI {


    @Autowired
    IWebDriverProvider webDriverProvider;

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

        webDriverProvider = (IWebDriverProvider) webDriverProvider.get();
        webDriverProvider.get().manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        webDriverProvider.closeDriver();
    }
}
