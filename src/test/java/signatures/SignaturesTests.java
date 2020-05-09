package signatures;

import base.BaseTest;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SignaturesPage;
import pages.PortalsPage;

public class SignaturesTests extends BaseTest  {
    private SignaturesPage signaturesPage;
    
    @Before
    public void setUp() {
        this.signaturesPage = dashboardPage.clickOnSignaturesLink();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSignaturesLink() {
        signaturesPage.clickOnSignaturesLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/signatures", driver.getCurrentUrl());
        assertEquals("failure - heading's doesn't match!", "Signatures", signaturesPage.getPanelHeading());
    }
    
    @Test
    public void testDashboardLink() {
        DashboardPage dashboardPage2 = signaturesPage.clickOnDashboardLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin", driver.getCurrentUrl());
        assertEquals("failure - heading's doesn't match!", "Dashboard", dashboardPage2.getPanelHeading());
    }
    
    @Test
    public void testPortalsLink() {
        PortalsPage portalsPage = signaturesPage.clickOnPortalsLink();
        
        assertEquals("failure - Url's doesn't match", "http://bvtest.school.cubes.rs/admin/portals", driver.getCurrentUrl());
        assertTrue("failure - heading's doesn't match!", portalsPage.getPanelHeading().contains("Portals"));
    }
    
    @Test
    public void testPortalsFilterWithB92() {        
        signaturesPage.selectFromPortalsDropdown("B92");
        List<String> portalValues = signaturesPage.getPortalValuesFromResults();
        for(String portal : portalValues) {
            assertEquals("failure - portals doesn't match.", portal, "B92");
        }
    }
    
    @Test 
    public void testPortalsFilterWithNovosti() {
        signaturesPage.selectFromPortalsDropdown("Novosti");
        List<String> portalValues = signaturesPage.getPortalValuesFromResults();
        for(String portal : portalValues) {
            assertEquals("failure - portals doesn't match.", portal, "Novosti");
        }
    }
    
    @Test
    public void testStatusesFilter() {
        signaturesPage.selectFromStatusesDropdown("New");
        
        List<String> statusValues = signaturesPage.getStatusValuesFromResults();
        for(String status : statusValues) {
            assertEquals("failure - statuses doesn't match.", status, "N");
        }
    }
    
    @Test
    public void testStatusesFilter2() {
        signaturesPage.selectFromStatusesDropdown("Ignore");
        
        List<String> statusValues = signaturesPage.getStatusValuesFromResults();
        for(String status : statusValues) {
            assertEquals("failure - statuses doesn't match.", status, "I");
        }
    }
    
    @Test
    public void testPortalAndStatusFilters() {
        signaturesPage.selectFromPortalsDropdown("Novosti");
        signaturesPage.selectFromStatusesDropdown("Approved");
        
        List<String> portalValues = signaturesPage.getPortalValuesFromResults();
        for(String portal : portalValues) {
            assertEquals("failure - portals doesn't match.", portal, "Novosti");
        }
        
        List<String> statusValues = signaturesPage.getStatusValuesFromResults();
        for(String status : statusValues) {
            assertEquals("failure - statuses doesn't match.", status, "A");
        }
    }
    
    @Test
    public void testDeleteFirstSignature() {
        String signatureToBeDeleted = signaturesPage.deleteFirstSignature();
        Assume.assumeTrue(signatureToBeDeleted != "");
        
        assertTrue("failure - after delete wrong message", signaturesPage.getAlertMessage().contains("has been successfully deleted"));
        assertTrue("failure - wrong signature deleted", signaturesPage.getAlertMessage().contains(signatureToBeDeleted));
    }
    
    @Test
    public void testDeleteLastSignature() {
        String signatureToBeDeleted = signaturesPage.deleteLastSignature();
        Assume.assumeTrue(signatureToBeDeleted != "");
        
        assertTrue("failure - after delete wrong message", signaturesPage.getAlertMessage().contains("has been successfully deleted"));
        assertTrue("failure - wrong signature deleted", signaturesPage.getAlertMessage().contains(signatureToBeDeleted));
    }
    
    @Test
    public void testDeleteRandomSignature() {
        String signatureToBeDeleted = signaturesPage.deleteRandomSignature();
        Assume.assumeTrue(signatureToBeDeleted != "");
        
        assertTrue("failure - after delete wrong message", signaturesPage.getAlertMessage().contains("has been successfully deleted"));
        assertTrue("failure - wrong signature deleted", signaturesPage.getAlertMessage().contains(signatureToBeDeleted));
    }
    
    @Test 
    public void testDeleteFirstSignatureFromPortalNovosti() {
        signaturesPage.selectFromPortalsDropdown("Novosti");
        String signatureToBeDeleted = signaturesPage.deleteFirstSignature();
        
        Assume.assumeTrue(signatureToBeDeleted != "");
        
        assertTrue("failure - after delete wrong message", signaturesPage.getAlertMessage().contains("has been successfully deleted"));
        assertTrue("failure - wrong signature deleted", signaturesPage.getAlertMessage().contains(signatureToBeDeleted));
    }
}
