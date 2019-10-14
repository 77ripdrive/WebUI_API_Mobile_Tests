package webUi;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
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

import spring.AppConfig;
import webDriver.IWebDriverProvider;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@Execution(ExecutionMode.CONCURRENT)
public class BaseWebUITest {

    @Autowired
    private IWebDriverProvider webDriverProvider;

    @Value("${browser}")
    private String browser;

    @Value("${wait.timeout}")
    private int waitTimeoutSeconds;

    @Value("${docker.enable}")
    private boolean dockerEnable;

    @SuppressFBWarnings
    @BeforeEach
    public void setUp()
    {
        Configuration.timeout = waitTimeoutSeconds * 1000;
        WebDriverRunner.setWebDriver(webDriverProvider.get());
        webDriverProvider.get().manage().window().maximize();
    }

    @AfterEach
    public void tearDown()
    {
        webDriverProvider.closeDriver();
    }
}
