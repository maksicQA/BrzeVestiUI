package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SourcesPage extends AdminPage {
    
    private By resultsTable = By.xpath("//*[@id=\"sourcesTable\"]/tbody");
    private By portalsFilter = By.id("sourcePortalSelect");
    private By statusFilter = By.id("sourceStatusSelect");
    private By categoryFilter = By.id("sourceCategorySelect");
    private By tableRow = By.tagName("tr");

    public SourcesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    
    public void selectFromPortalsDropdown(String portalName) {
        Select portalsDropdown = new Select(driver.findElement(portalsFilter));
        portalsDropdown.selectByVisibleText(portalName);
    }
    
    public void selectFromStatusesDropdown(String statusName) {
        Select statusDropdown = new Select(driver.findElement(statusFilter));
        statusDropdown.selectByVisibleText(statusName);
    }
    
    public void selectFromCategoriesDropdown(String categoryName) {
        Select categoryDropdown = new Select(driver.findElement(categoryFilter));
        categoryDropdown.selectByVisibleText(categoryName);
    }
    
    public List<WebElement> getAllRows() {
        if (driver.findElements(tableRow).size() != 0) {
            WebElement table = driver.findElement(resultsTable);
            return table.findElements(tableRow);
        }
        return new ArrayList<>();
    }
     
    public List<String> getPortalValuesFromResults() {
        List<String> portalValues = new ArrayList<>();
        List<WebElement> rows = this.getAllRows();
        for (WebElement row : rows) {
            portalValues.add(row.findElement(By.xpath(".//td[1]")).getText());
        }
        return portalValues;
    }
    
    public List<String> getStatusValuesFromResults() {
        List<String> statusValues = new ArrayList<>();
        for (WebElement row : this.getAllRows()) {
            WebElement statusField = row.findElement(By.xpath(".//td[7]/span"));
            String statusText = statusField.getText();
            statusValues.add(statusText);
        }
        return statusValues;
    }
    
    public List<String> getCategoryValuesFromResults() {
        List<String> categoryValues = new ArrayList<>();
        for (WebElement row : this.getAllRows()) {
            WebElement categoryField = row.findElement(By.xpath(".//td[6]"));
            String categoryText = categoryField.getText();
            categoryValues.add(categoryText);
        }
        return categoryValues;
    }

}
