package categories;

import base.BaseTest;
import framework.Helper;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AdminPage;
import pages.CategoriesPage;
import pages.DashboardPage;
import pages.SignaturesPage;

public class CategoriesTests extends BaseTest {
    private By brzeVestiLogo = By.className("navbar-brand");
    private CategoriesPage categoriesPage;
    private By addCategoryBtn = By.partialLinkText("Add category");
    private By categorySaveBtn = By.id("save-category-button");
    private By categoryTitleField = By.id("title");
    private By editCategoryBtn = By.xpath("//*[@id=\"categoriesTable\"]/tbody/tr[1]/td[5]/div/a");
    private By deleteCategoryBtn = By.xpath("//*[@id=\"categoriesTable\"]/tbody/tr[1]/td[5]/div/button[2]");
    private By confirmDeleteBtn = By.xpath("//*[@id=\"categoryDeleteDialog\"]/div/div/div[3]/button[2]");
    private By statusChangeBtn = By.xpath("//*[@id=\"categoriesTable\"]/tbody/tr[1]/td[5]/div/button[1]");
    private By confirmDisableBtn = By.xpath("//*[@id=\"categoryDisableDialog\"]/div/div/div[3]/button[2]");
    private By confirmEnableBtn = By.xpath("//*[@id=\"categoryEnableDialog\"]/div/div/div[3]/button[2]");
//    private By categoryStatus = By.xpath("//*[@id=\"categoriesTable\"]/tbody/tr[1]/td[4]/span");
   
    
    
    
    @Before 
    public void setUp() {
        this.categoriesPage = dashboardPage.clickOnCategoriesLink();
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void brzeVestiLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(brzeVestiLogo)).click(); 

        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/", driver.getCurrentUrl());
    }
    
    @Test
    public void testDashboardLink() {
        categoriesPage.clickOnDashboardLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin", driver.getCurrentUrl());
    }
    
    @Test
    public void testSignaturesLink() {
        categoriesPage.clickOnSignaturesLink();
        
         assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/signatures", driver.getCurrentUrl());
    }
    
    @Test
    public void testCategoriesLink() {
        categoriesPage.clickOnCategoriesLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/categories", driver.getCurrentUrl());
    }
    
    @Test
    public void testRegionsLink() {
        categoriesPage.clickOnRegionsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/regions", driver.getCurrentUrl());
    }
    
    @Test
    public void testPortalsLink() {
        categoriesPage.clickOnPortalsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/portals", driver.getCurrentUrl());
    }
    
    @Test
    public void testSourcesLink() {
        categoriesPage.clickOnSourcesLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/sources", driver.getCurrentUrl());
    }
    
    @Test
    public void testLogout() {
        categoriesPage.logout();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/", driver.getCurrentUrl());
    }
    
    @Test
    public void testAddCategory() {
        String title = "Category-" + Helper.getRandomText();
        driver.findElement(addCategoryBtn).click();
        driver.findElement(categoryTitleField).sendKeys(title);
        driver.findElement(categorySaveBtn).click();
             
        assertTrue("failure - category can't be saved", categoriesPage.getAlertMessage().contains("has been successfully saved!"));
    }
    
    @Test
    public void testEditCategory() {
        String newTitle = "Category-" + Helper.getRandomText();
          
        driver.findElement(editCategoryBtn).click();
        driver.findElement(categoryTitleField).clear();
        driver.findElement(categoryTitleField).sendKeys(newTitle);
        driver.findElement(categorySaveBtn).click();
       
        
        assertTrue("failure - category can't be saved", categoriesPage.getAlertMessage().contains("has been successfully saved!"));    
    }
    
    
    @Test
    public void testCategoryDisable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDisableBtn)).click();
        assertTrue("category is already disabled", categoriesPage.getAlertMessage().contains("has been disabled"));    
    }
    
    @Test
    public void testCategoryEnable() {
        driver.findElement(statusChangeBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmEnableBtn)).click(); 
        assertTrue("category is already enabled", categoriesPage.getAlertMessage().contains("has been enabled"));     
    }
       
    
    @Test
    public void testCategoryDelete() {
        
        driver.findElement(deleteCategoryBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
        
         assertTrue("failure - category can't be deleted", categoriesPage.getAlertMessage().contains("has been successfully deleted!"));             
    }
    
}
