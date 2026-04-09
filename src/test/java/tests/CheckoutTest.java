package tests;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    // Helper: login, add item, go to cart, click checkout
    private CheckoutPage goToCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        return new CheckoutPage(driver);
    }

    // ─── TC18: Full End-to-End Purchase ───────────────────────────────────────
    @Test(priority = 1, description = "Complete a full purchase from login to order confirmation")
    public void fullPurchaseFlowTest() {
        test = extent.createTest("TC18 - Full End-to-End Purchase");

        CheckoutPage checkoutPage = goToCheckout();
        test.info("Navigated to checkout page");

        checkoutPage.fillCheckoutInfo("Ravi", "Kumar", "600001");
        test.info("Filled checkout info: Ravi Kumar, 600001");

        checkoutPage.clickFinish();
        test.info("Clicked Finish button");

        Assert.assertTrue(checkoutPage.isOrderConfirmed(),
                "Order confirmation should be displayed");
        Assert.assertEquals(checkoutPage.getConfirmationHeader(),
                "Thank you for your order!",
                "Confirmation header should say 'Thank you for your order!'");

        test.pass("Full purchase completed successfully! Order confirmed.");
    }

    // ─── TC19: Checkout with Empty First Name ─────────────────────────────────
    @Test(priority = 2, description = "Checkout with empty first name shows error")
    public void checkoutEmptyFirstNameTest() {
        test = extent.createTest("TC19 - Checkout Empty First Name");

        CheckoutPage checkoutPage = goToCheckout();
        checkoutPage.fillCheckoutInfo("", "Kumar", "600001");
        test.info("Left first name empty and clicked continue");

        Assert.assertFalse(checkoutPage.getErrorMessage().isEmpty(),
                "Error message should appear when first name is empty");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name"),
                "Error should mention First Name");

        test.pass("Validation works - error shown for empty first name");
    }

    // ─── TC20: Checkout with Empty Last Name ──────────────────────────────────
    @Test(priority = 3, description = "Checkout with empty last name shows error")
    public void checkoutEmptyLastNameTest() {
        test = extent.createTest("TC20 - Checkout Empty Last Name");

        CheckoutPage checkoutPage = goToCheckout();
        checkoutPage.fillCheckoutInfo("Ravi", "", "600001");
        test.info("Left last name empty and clicked continue");

        Assert.assertFalse(checkoutPage.getErrorMessage().isEmpty(),
                "Error message should appear when last name is empty");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name"),
                "Error should mention Last Name");

        test.pass("Validation works - error shown for empty last name");
    }

    // ─── TC21: Checkout with Empty Postal Code ────────────────────────────────
    @Test(priority = 4, description = "Checkout with empty postal code shows error")
    public void checkoutEmptyPostalCodeTest() {
        test = extent.createTest("TC21 - Checkout Empty Postal Code");

        CheckoutPage checkoutPage = goToCheckout();
        checkoutPage.fillCheckoutInfo("Ravi", "Kumar", "");
        test.info("Left postal code empty and clicked continue");

        Assert.assertFalse(checkoutPage.getErrorMessage().isEmpty(),
                "Error message should appear when postal code is empty");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code"),
                "Error should mention Postal Code");

        test.pass("Validation works - error shown for empty postal code");
    }

    // ─── TC22: Verify Order Total is Displayed ────────────────────────────────
    @Test(priority = 5, description = "Order total label should be visible on overview page")
    public void verifyOrderTotalDisplayedTest() {
        test = extent.createTest("TC22 - Order Total Displayed");

        CheckoutPage checkoutPage = goToCheckout();
        checkoutPage.fillCheckoutInfo("Ravi", "Kumar", "600001");
        test.info("Filled checkout info and proceeded to overview");

        String total = checkoutPage.getTotalLabel();
        Assert.assertFalse(total.isEmpty(), "Total label should not be empty");
        Assert.assertTrue(total.contains("Total"), "Label should contain 'Total'");

        test.pass("Order total displayed correctly: " + total);
    }
}
