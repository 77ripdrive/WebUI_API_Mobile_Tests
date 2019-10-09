package webDriver;


import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import java.net.URL;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class WebDriverProvader implements IWebDriver {

    private final static ThreadLocal<WebDriver> CACHE = ThreadLocal.withInitial(() -> null);

    @Autowired
    private Docker docker;

    @Value("${browser}")
    private String browserName;

    @Value("${browser.version}")
    private String browserVersion;

    @Value("${driver.remote}")
    private boolean browserRemote;

    @Value("${saucelab.user}")
    private String sauceLabUser;

    @Value("${saucelab.key}")
    private String sauceLabKey;

    @Value("${saucelab.host}")
    private String sauceLabHost;

    @Value("${docker.enable}")
    private boolean dockerEnable;

    @Value("${docker.host}")
    private String dockerHost;

    @Value("${docker.port}")
    private int dockerPort;

    private static Optional<WebDriver> getFromCache()
    {
        return Optional.ofNullable(CACHE.get());
    }

    @Override
    public WebDriver get()
    {
        return getFromCache().orElseGet(() -> {
            if (dockerEnable)
            {
                docker.startSeleniumGrid();
            }
            WebDriverFactory factory = WebDriverFactory.getInstance(browserName);
            WebDriver driver = isRemoteDriver() ? factory.createRemote(getRemoteUrl(), getDesiredCapabilities())
                    : factory.create(getDesiredCapabilities());
            CACHE.set(driver);
            return driver;
        });
    }

    public void closeDriver()
    {
        getFromCache().ifPresent(driver -> {
            if (dockerEnable)
            {
                docker.stopSeleniumGrid();
            }
            driver.quit();
            CACHE.remove();
        });
    }

    private DesiredCapabilities getDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName.toLowerCase());
        if (!dockerEnable)
        {
            capabilities.setVersion(browserVersion);
            capabilities.setPlatform(Platform.getCurrent());
        }
        return capabilities;
    }

    private boolean isRemoteDriver()
    {
        return browserRemote || dockerEnable;
    }

    private URL getRemoteUrl()
    {
        return dockerEnable ? getDockerHub() : getDockerHub();
    }


    private URL getDockerHub()
    {
        return build("http", dockerHost, b -> b.port(dockerPort));
    }

    private URL build(String schema, String host, UnaryOperator<Builder> customize){
        Builder builder = new HttpUrl.Builder().scheme(schema).host(host).addPathSegments("wd/hub");
        return customize.apply(builder).build().url();
    }
}


