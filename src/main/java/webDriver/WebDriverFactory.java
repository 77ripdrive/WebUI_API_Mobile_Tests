package webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.function.Supplier;

public enum WebDriverFactory {
    CHROME
            {
                public WebDriver create(DesiredCapabilities desiredCapabilities)
                {
                    return new ChromeDriver(setUpDriver(desiredCapabilities));
                }

                @Override
                public WebDriver createRemote(URL url, DesiredCapabilities desiredCapabilities)
                {
                    return new RemoteWebDriver(url, desiredCapabilities);
                }

                private DesiredCapabilities setUpDriver(DesiredCapabilities desiredCapabilities)
                {
                    WebDriverManager.chromedriver().setup();
                    return WebDriverFactory.createOptions(desiredCapabilities, () -> {
                        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
                        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY,
                                new ChromeOptions().addArguments("--start-maximized"));
                        return chromeCapabilities;
                    });
                }

            },
    FIREFOX
            {
                @Override
                public WebDriver create(DesiredCapabilities desiredCapabilities)
                {
                    return new FirefoxDriver(setUpDriver(desiredCapabilities));
                }

                @Override
                public WebDriver createRemote(URL url, DesiredCapabilities desiredCapabilities)
                {
                    return new RemoteWebDriver(url, setUpDriver(desiredCapabilities));
                }

                private DesiredCapabilities setUpDriver(DesiredCapabilities desiredCapabilities)
                {
                    WebDriverManager.firefoxdriver().setup();
                    return WebDriverFactory.createOptions(desiredCapabilities, () -> DesiredCapabilities.firefox());
                }
            },
    EDGE
            {
                @Override
                public WebDriver create(DesiredCapabilities desiredCapabilities)
                {
                    return new EdgeDriver(setUpDriver(desiredCapabilities));
                }

                @Override
                public WebDriver createRemote(URL url, DesiredCapabilities desiredCapabilities)
                {
                    return new RemoteWebDriver(url, setUpDriver(desiredCapabilities));
                }

                private DesiredCapabilities setUpDriver(DesiredCapabilities desiredCapabilities)
                {
                    WebDriverManager.edgedriver().setup();
                    return WebDriverFactory.createOptions(desiredCapabilities, () -> DesiredCapabilities.edge());
                }
            };

    public abstract WebDriver create(DesiredCapabilities desiredCapabilities);

    public abstract WebDriver createRemote(URL url, DesiredCapabilities desiredCapabilities);

    @SuppressWarnings("unchecked")
    private static <T extends MutableCapabilities> T createOptions(DesiredCapabilities toMerge,
                                                                   Supplier <T> optionsFactory)
    {
        return (T) optionsFactory.get().merge(toMerge);
    }

    public static WebDriverFactory getInstance(String browser)
    {
        return WebDriverFactory.valueOf(browser.toUpperCase());
    }

}
