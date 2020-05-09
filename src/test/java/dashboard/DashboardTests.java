
package dashboard;

import base.BaseTest;
import org.junit.Test;
import static org.junit.Assert.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SignaturesPage;
import pages.CategoriesPage;


public class DashboardTests extends BaseTest {
    
    @Test
    public void testSignaturesLink() {
        LoginPage loginPage = new LoginPage(driver, wait);
        DashboardPage dashboardPage = loginPage.login();
        SignaturesPage signaturesPage = dashboardPage.clickOnSignaturesLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/signatures", driver.getCurrentUrl());
        assertEquals("failure - heading's doesn't match!", "Signatures", signaturesPage.getPanelHeading());
    }
    
    @Test 
    public void testCategoriesLink() {
        LoginPage loginPage = new LoginPage(driver, wait);
        DashboardPage dashboardPage = loginPage.login();
        CategoriesPage categoriesPage = dashboardPage.clickOnCategoriesLink();
                
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/categories", driver.getCurrentUrl());
        assertTrue("failure - heading's doesn't match!", categoriesPage.getPanelHeading().contains("Categories"));
    }
    
}