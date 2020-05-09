
package sources;

import base.BaseTest;
import framework.Helper;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.SourcesPage;

public class SourcesTests extends BaseTest {
    private By brzeVestiLogo = By.className("navbar-brand");
    private SourcesPage sourcesPage;
    private By addSourceBtn = By.partialLinkText("Add source");
    private By sourceSaveBtn = By.id("save-source-button");
    private By sourceTitleField = By.id("sourceTitleText");
    private By sourceUrlField = By.id("sourceUrlText");
    private By sourcePortalDropdown = By.id("sourcePortalSelect");
    private By sourceCategoryDropdown = By.id("sourceCategorySelect");
    private By sourcePortalSelect = By.xpath("//*[@id=\"sourcePortalSelect\"]/option[2]");
    private By sourceCategorySelect = By.xpath("//*[@id=\"sourceCategorySelect\"]/option[115]");
    private By sourcesPageBody = By.id("app-layout");
    private By editSourceBtn = By.xpath("//*[@id=\"sourcesTable\"]/tbody/tr[1]/td[8]/div/a");
    private By deleteSourceBtn = By.xpath("//*[@id=\"sourcesTable\"]/tbody/tr[1]/td[8]/div/button[2]");
    private By confirmDeleteBtn = By.xpath("//*[@id=\"sourceDeleteDialog\"]/div/div/div[3]/button[2]");
    private By statusChangeBtn = By.xpath("//*[@id=\"sourcesTable\"]/tbody/tr[1]/td[8]/div/button[1]");
    private By confirmDisableBtn = By.xpath("//*[@id=\"sourceDisableDialog\"]/div/div/div[3]/button[2]");
    private By confirmEnableBtn = By.xpath("//*[@id=\"sourceEnableDialog\"]/div/div/div[3]/button[2]");
    
    
    @Before 
    public void setUp() {
        this.sourcesPage = dashboardPage.clickOnSourcesLink();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void brzeVestiLogo() {
        driver.findElement(brzeVestiLogo).click();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/", driver.getCurrentUrl());
    }
    
    @Test
    public void testDashboardLink() {
        sourcesPage.clickOnDashboardLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin", driver.getCurrentUrl());
    }
    
    @Test
    public void testSignaturesLink() {
        sourcesPage.clickOnSignaturesLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/signatures", driver.getCurrentUrl());
    }
    
    @Test
    public void testCategoriesLink() {
        sourcesPage.clickOnCategoriesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/categories", driver.getCurrentUrl());
    }
    
    @Test
    public void testRegionsLink() {
        sourcesPage.clickOnRegionsLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/regions", driver.getCurrentUrl());
    }
    
    @Test
    public void testPortalsLink() {
        sourcesPage.clickOnPortalsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/portals", driver.getCurrentUrl());
    }
    
    @Test
    public void testSourcesLink() {
        sourcesPage.clickOnSourcesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/sources", driver.getCurrentUrl());
    }
    
    @Test
    public void testLogout() {
        sourcesPage.logout();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/", driver.getCurrentUrl());
    }
    
    @Test
     public void testAddSource() {
        String title = "Source-" + Helper.getRandomText();
        String url = "https://www.portal.org/feed.rss" + Helper.getRandomText();
        
        driver.findElement(addSourceBtn).click();
        driver.findElement(sourcePortalDropdown).click();
        driver.findElement(sourcePortalSelect).click();
        driver.findElement(sourcesPageBody).click();
        driver.findElement(sourceTitleField).sendKeys(title);
        driver.findElement(sourceUrlField).sendKeys(url);
        driver.findElement(sourceCategoryDropdown).click();
        driver.findElement(sourceCategorySelect).click();
        driver.findElement(sourcesPageBody).click();
        driver.findElement(sourceSaveBtn).click();
             
        assertTrue("failure - source can't be saved", sourcesPage.getAlertMessage().contains("has been successfully saved!"));
    }
     
    @Test
    public void testEditSource() {
        String newTitle = "Source -" + Helper.getRandomText();
        
        
        driver.findElement(editSourceBtn).click();
        driver.findElement(sourceTitleField).clear();
        driver.findElement(sourceTitleField).sendKeys(newTitle);
        driver.findElement(sourceSaveBtn).click();
        
        assertTrue("failure - source can't be saved", sourcesPage.getAlertMessage().contains("has been successfully saved!"));  
    }
    
    @Test
    public void testSourceDisable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDisableBtn)).click();
        assertTrue("source is already disabled", sourcesPage.getAlertMessage().contains("has been disabled"));    
    }
    
    @Test
    public void testSourceEnable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmEnableBtn)).click();
        assertTrue("source is already enabled", sourcesPage.getAlertMessage().contains("has been enabled"));    
    }
    
    @Test
    public void testSourceDelete() {
        
        driver.findElement(deleteSourceBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
        
         assertTrue("failure - source can't be deleted", sourcesPage.getAlertMessage().contains("has been successfully deleted!"));            
    }
    
    @Test
    public void testPortalsFilterWithB92() {        
        sourcesPage.selectFromPortalsDropdown("B92");
        List<String> portalValues = sourcesPage.getPortalValuesFromResults();
        for(String portal : portalValues) {
            assertEquals("failure - portals doesn't match.", portal, "B92");
        }
    }
    
    @Test
    public void testEnabledStatusesFilter() {
        sourcesPage.selectFromStatusesDropdown("Enabled");
        
        List<String> statusValues = sourcesPage.getStatusValuesFromResults();
        for(String status : statusValues) {
            assertEquals("failure - statuses doesn't match.", status, "E");
        }
    }
    
    @Test
    public void testCategoryFilter() {
        sourcesPage.selectFromCategoriesDropdown("Uncategorized");
        
        List<String> categoryValues = sourcesPage.getCategoryValuesFromResults();
        for(String category : categoryValues) {
            assertEquals("failure - categories doesn't match.", category, "-");
        }
    }
    
    @Test
    public void testCombinationOfAllFilters() {
        sourcesPage.selectFromPortalsDropdown("B92");
        sourcesPage.selectFromStatusesDropdown("Enabled");
        sourcesPage.selectFromCategoriesDropdown("Uncategorized");
        
        List<String> portalValues = sourcesPage.getPortalValuesFromResults();
        for(String portal : portalValues) {
            assertEquals("failure - portals doesn't match.", portal, "B92");
        }
        
        List<String> statusValues = sourcesPage.getStatusValuesFromResults();
        for(String status : statusValues) {
            assertEquals("failure - statuses doesn't match.", status, "E");
        }
        
        List<String> categoryValues = sourcesPage.getCategoryValuesFromResults();
        for (String category : categoryValues) {
            assertEquals("failure - categories doesn't match.", category, "-");
        }
    }

    
    
    
    
    
    
    
    
    
}
