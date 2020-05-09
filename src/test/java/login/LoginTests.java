package login;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import base.BaseTest;
import org.openqa.selenium.By;
import pages.DashboardPage;
import pages.LoginPage;


public class LoginTests extends BaseTest {
    
    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        DashboardPage dashboardPage = loginPage.login();
        
        assertEquals("failure - Url's doesn't match!", "http://bvtest.school.cubes.rs/admin", driver.getCurrentUrl());
        assertEquals("failure - heading's doesn't match!", "Dashboard", dashboardPage.getPanelHeading());
    }
}
