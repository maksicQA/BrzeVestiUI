package pages;

import framework.Helper;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignaturesPage extends AdminPage {

    private By portalsFilter = By.id("newsProcessorSignaturePortalSelect");
    private By statusFilter = By.id("newsProcessorSignatureStatusSelect");
    private By resultsTable = By.xpath("//*[@id=\"newsProcessorSignatureTable\"]/tbody");
    private By tableRow = By.tagName("tr");
    private By confirmDeleteButton = By.xpath("//*[@id=\"newsProcessorSignatureDeleteDialog\"]/div/div/div[3]/button[2]");
    private By alertBox = By.className("alert");
    
    
    SignaturesPage(WebDriver driver, WebDriverWait wait) {
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
            portalValues.add(row.findElement(By.xpath(".//td[2]")).getText());
        }
        return portalValues;
    }
    
    public List<String> getStatusValuesFromResults() {
        List<String> statusValues = new ArrayList<>();
        for (WebElement row : this.getAllRows()) {
            WebElement statusField = row.findElement(By.xpath(".//td[5]/span"));
            String statusText = statusField.getText();
            statusValues.add(statusText);
        }
        return statusValues;
    }
    
    
    public String deleteFirstSignature() {
        List<WebElement> rows = this.getAllRows();
        if (rows.size() == 0) {
            return "";
        }
        WebElement firstRow = rows.get(0);
        String signature = this.getSignatureFromRow(firstRow);
        this.clickOnDeleteButton(firstRow);
        this.confirmDelete();
        return signature;
    }
    
    public String deleteLastSignature() {
        List<WebElement> rows = this.getAllRows();
        if (rows.size() == 0) {
            return "";
        }
        WebElement lastRow = rows.get(rows.size() - 1);
        String signature = this.getSignatureFromRow(lastRow);
        this.clickOnDeleteButton(lastRow);
        this.confirmDelete();
        return signature;
    }
    
    public String deleteRandomSignature() {
        List<WebElement> rows = this.getAllRows();
        if (rows.size() == 0) {
            return "";
        }
        WebElement randomRow = rows.get(Helper.getRandomInteger(rows.size() - 1));
        String signature = this.getSignatureFromRow(randomRow);
        this.clickOnDeleteButton(randomRow);
        this.confirmDelete();
        return signature;
    }
    
    public String getAlertMessage() {
        return driver.findElement(alertBox).getText();
    }
    
    private String getSignatureFromRow(WebElement row) {
        return row.findElement(By.xpath(".//td[3]")).getText();
    }
    
    private void clickOnDeleteButton(WebElement row) {
        row.findElement(By.xpath(".//td[6]/div/button[3]")).click();
    }
    
    private void confirmDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }
    
}
