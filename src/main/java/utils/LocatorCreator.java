package utils;

import org.openqa.selenium.By;

public class LocatorCreator
{
    public static By createXpath(String format, Object... args)
    {
        return By.xpath(String.format(format, args));
    }
}
