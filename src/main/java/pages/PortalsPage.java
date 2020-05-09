package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PortalsPage extends AdminPage {
    private WebDriver driver;
    private By panelHeading = By.className("panel-heading");

    public PortalsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
