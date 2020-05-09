package pages;

import framework.Helper;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoriesPage extends AdminPage {
    
    private WebDriver driver;
    private By panelHeading = By.className("panel-heading");
    private By alertBox = By.className("alert");
    protected String statusMessage = "Status can't be changed";
   
    public CategoriesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    
    public String statusMessage() {
        return this.statusMessage();
    }
    
  
    
    
    
  
    
   
    
}
