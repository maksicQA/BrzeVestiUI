
package regions;

import base.BaseTest;
import framework.Helper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RegionsPage;

public class RegionsTests extends BaseTest {
    private By brzeVestiLogo = By.className("navbar-brand");
    private RegionsPage regionsPage;
    private By addRegionBtn = By.partialLinkText("Add region");
    private By regionSaveBtn = By.id("save-region-button");
    private By regionTitleField = By.id("title");
    private By editRegionBtn = By.xpath("//*[@id=\"regionsTable\"]/tbody/tr[1]/td[5]/div/a");
    private By deleteRegionBtn = By.xpath("//*[@id=\"regionsTable\"]/tbody/tr[1]/td[5]/div/button[2]");
    private By confirmDeleteBtn = By.xpath("//*[@id=\"regionDeleteDialog\"]/div/div/div[3]/button[2]");
    private By statusChangeBtn = By.xpath("//*[@id=\"regionsTable\"]/tbody/tr[1]/td[5]/div/button[1]");
    private By confirmDisableBtn = By.xpath("//*[@id=\"regionDisableDialog\"]/div/div/div[3]/button[2]");
    private By confirmEnableBtn = By.xpath("//*[@id=\"regionEnableDialog\"]/div/div/div[3]/button[2]");
    
    @Before 
    public void setUp() {
        this.regionsPage = dashboardPage.clickOnRegionsLink();
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
        regionsPage.clickOnDashboardLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin", driver.getCurrentUrl());
    }
    
    @Test
    public void testSignaturesLink() {
        regionsPage.clickOnSignaturesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/signatures", driver.getCurrentUrl());
    }
    
    @Test
    public void testCategoriesLink() {
        regionsPage.clickOnCategoriesLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/categories", driver.getCurrentUrl());
    }
    
    @Test
    public void testRegionsLink() {
        regionsPage.clickOnRegionsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/regions", driver.getCurrentUrl());
    }
    
    @Test
    public void testPortalsLink() {
        regionsPage.clickOnPortalsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/portals", driver.getCurrentUrl());
    }
    
    @Test 
    public void testSourcesLink() {
        regionsPage.clickOnSourcesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/sources", driver.getCurrentUrl());
    }
    
    @Test
    public void testLogout() {
        regionsPage.logout();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/", driver.getCurrentUrl());
    }
    
    @Test
    public void testAddRegion() {
        String title = "Region-" + Helper.getRandomText();
        driver.findElement(addRegionBtn).click();
        driver.findElement(regionTitleField).sendKeys(title);
        driver.findElement(regionSaveBtn).click();
        
        
        assertTrue("failure - region can't be saved", regionsPage.getAlertMessage().contains("has been successfully saved!"));
    }
    
     @Test
    public void testEditRegion() {
        String newTitle = "Region-" + Helper.getRandomText();
        
        
        driver.findElement(editRegionBtn).click();
        driver.findElement(regionTitleField).clear();
        driver.findElement(regionTitleField).sendKeys(newTitle);
        driver.findElement(regionSaveBtn).click();
        
        assertTrue("failure - region can't be saved", regionsPage.getAlertMessage().contains("has been successfully saved!"));  
    }
    
    @Test
    public void testRegionDisable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDisableBtn)).click();
        assertTrue("region is already disabled", regionsPage.getAlertMessage().contains("has been disabled"));    
    }
    
    @Test
    public void testRegionEnable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmEnableBtn)).click();
        assertTrue("region is already disabled", regionsPage.getAlertMessage().contains("has been enabled"));    
    }
    
    
    
    @Test
    public void testRegionDelete() {
        
        driver.findElement(deleteRegionBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
        
         assertTrue("failure - region can't be deleted", regionsPage.getAlertMessage().contains("has been successfully deleted!"));    
         
    }
}
