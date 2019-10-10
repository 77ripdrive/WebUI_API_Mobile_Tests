package webDriver;

import org.openqa.selenium.WebDriver;

public interface IWebDriverProvider {
    WebDriver get();

    void closeDriver();
}
