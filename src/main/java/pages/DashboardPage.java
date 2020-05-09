package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends AdminPage {

    private By headingTitle = By.className("panel-heading");
    private By logo = By.className("navbar-brand");
    
    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    } 
}
