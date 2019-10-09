package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class QuotePage extends BaseWebPage {

    public QuotePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String receiveDateOfStart(){
        String s="";
        return s;
    }
}
