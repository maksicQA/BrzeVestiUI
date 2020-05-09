
package portals;

import base.BaseTest;
import framework.Helper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PortalsPage;


public class PortalsTests extends BaseTest {
    private By brzeVestiLogo = By.className("navbar-brand");
    private PortalsPage portalsPage;
    private By addPortalBtn = By.partialLinkText("Add portal");
    private By portalSaveBtn = By.id("save-portal-button");
    private By portalTitleField = By.id("title");
    private By portalUrlField = By.id("url");
    private By editPortalBtn = By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[1]/td[5]/div/a");
    private By deletePortalBtn = By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[1]/td[5]/div/button[2]");
    private By confirmDeleteBtn = By.xpath("//*[@id=\"portalDeleteDialog\"]/div/div/div[3]/button[2]");
    private By statusChangeBtn = By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[1]/td[5]/div/button[1]");
    private By confirmDisableBtn = By.xpath("//*[@id=\"portalDisableDialog\"]/div/div/div[3]/button[2]");
    private By confirmEnableBtn = By.xpath("//*[@id=\"portalEnableDialog\"]/div/div/div[3]/button[2]");
    
    @Before 
    public void setUp() {
        this.portalsPage = dashboardPage.clickOnPortalsLink();
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
        portalsPage.clickOnDashboardLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin", driver.getCurrentUrl());             
    }
    
    @Test
    public void testSignaturesLink() {
        portalsPage.clickOnSignaturesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/signatures", driver.getCurrentUrl());
    }
    
    @Test
    public void testCategoriesLink() {
        portalsPage.clickOnCategoriesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/categories", driver.getCurrentUrl());
    }
    
    @Test
    public void testRegionsLink() {
        portalsPage.clickOnRegionsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/regions", driver.getCurrentUrl());
    }
    
    @Test
    public void testPortalsLink() {
        portalsPage.clickOnPortalsLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/portals", driver.getCurrentUrl());
    }
    
    @Test
    public void testSourcesLink() {
        portalsPage.clickOnSourcesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/sources", driver.getCurrentUrl());
    }
    
    @Test
    public void testLogout() {
        portalsPage.logout();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/", driver.getCurrentUrl());
    }
    
    @Test
     public void testAddPortal() {
        String title = "Portal-" + Helper.getRandomText();
        String url = "http://www.nenad.rs" + Helper.getRandomText();
        
        driver.findElement(addPortalBtn).click();
        driver.findElement(portalTitleField).sendKeys(title);
        driver.findElement(portalUrlField).sendKeys(url);
        driver.findElement(portalSaveBtn).click();
             
        assertTrue("failure - portal can't be saved", portalsPage.getAlertMessage().contains("has been successfully saved!"));
    }
     
    @Test
    public void testEditPortal() {
        String newTitle = "Portal-" + Helper.getRandomText();
        
        
        driver.findElement(editPortalBtn).click();
        driver.findElement(portalTitleField).clear();
        driver.findElement(portalTitleField).sendKeys(newTitle);
        driver.findElement(portalSaveBtn).click();
        
        assertTrue("failure - portal can't be saved", portalsPage.getAlertMessage().contains("has been successfully saved!"));  
    }
    
    @Test
    public void testPortalDisable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDisableBtn)).click();
        assertTrue("portal is already disabled", portalsPage.getAlertMessage().contains("has been disabled"));    
    }
    
    @Test
    public void testPortalEnable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmEnableBtn)).click();
        assertTrue("portal is already enabled", portalsPage.getAlertMessage().contains("has been enabled"));    
    }
    
    @Test
    public void testPortalDelete() {
        
        driver.findElement(deletePortalBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
        
         assertTrue("failure - portal can't be deleted", portalsPage.getAlertMessage().contains("has been successfully deleted!"));    
         
    }
    
         
         
         
}
