package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // ─── TC01: Valid Login ────────────────────────────────────────────────────
    @Test(priority = 1, description = "Valid login with correct credentials")
    public void validLoginTest() {
        test = extent.createTest("TC01 - Valid Login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        test.info("Entered valid credentials and clicked login");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User should be redirected to inventory page after login");

        test.pass("Valid login successful - redirected to inventory page");
    }

    // ─── TC02: Invalid Password ───────────────────────────────────────────────
    @Test(priority = 2, description = "Login with wrong password shows error")
    public void invalidPasswordTest() {
        test = extent.createTest("TC02 - Invalid Password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrongpassword");

        test.info("Entered invalid password");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for wrong password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Correct error message should appear");

        test.pass("Error message displayed correctly for invalid password");
    }

    // ─── TC03: Empty Username ─────────────────────────────────────────────────
    @Test(priority = 3, description = "Login with empty username shows error")
    public void emptyUsernameTest() {
        test = extent.createTest("TC03 - Empty Username");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");

        test.info("Left username empty and clicked login");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for empty username");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Error should say username is required");

        test.pass("Empty username validation working correctly");
    }

    // ─── TC04: Empty Password ─────────────────────────────────────────────────
    @Test(priority = 4, description = "Login with empty password shows error")
    public void emptyPasswordTest() {
        test = extent.createTest("TC04 - Empty Password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");

        test.info("Left password empty and clicked login");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for empty password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"),
                "Error should say password is required");

        test.pass("Empty password validation working correctly");
    }

    // ─── TC05: Locked Out User ────────────────────────────────────────────────
    @Test(priority = 5, description = "Locked out user cannot login")
    public void lockedOutUserTest() {
        test = extent.createTest("TC05 - Locked Out User");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        test.info("Attempted login with locked out user account");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error should be shown for locked out user");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Error should mention user is locked out");

        test.pass("Locked out user correctly blocked from login");
    }

    // ─── TC06: Logout ─────────────────────────────────────────────────────────
    @Test(priority = 6, description = "User can logout successfully")
    public void logoutTest() {
        test = extent.createTest("TC06 - Logout");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        test.info("Logged in successfully");

        pages.ProductPage productPage = new pages.ProductPage(driver);
        productPage.logout();

        test.info("Clicked logout from menu");

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User should be redirected to login page after logout");
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"),
                "User should NOT be on inventory page after logout");

        test.pass("Logout successful - user redirected to login page");
    }
}
