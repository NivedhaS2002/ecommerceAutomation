package tests;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    // Helper: login and go to cart with items
    private CartPage loginAddItemAndGoToCart(int numberOfItems) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        for (int i = 0; i < numberOfItems; i++) {
            productPage.addProductToCartByIndex(i);
        }
        productPage.goToCart();
        return new CartPage(driver);
    }

    // ─── TC13: Add Single Item to Cart ───────────────────────────────────────
    @Test(priority = 1, description = "Add one product to cart and verify it appears")
    public void addSingleItemToCartTest() {
        test = extent.createTest("TC13 - Add Single Item to Cart");

        CartPage cartPage = loginAddItemAndGoToCart(1);
        test.info("Added 1 product and navigated to cart");

        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Cart should contain exactly 1 item");

        test.pass("Single item added to cart successfully");
    }

    // ─── TC14: Add Multiple Items to Cart ────────────────────────────────────
    @Test(priority = 2, description = "Add multiple products to cart and verify count")
    public void addMultipleItemsToCartTest() {
        test = extent.createTest("TC14 - Add Multiple Items to Cart");

        CartPage cartPage = loginAddItemAndGoToCart(3);
        test.info("Added 3 products and navigated to cart");

        Assert.assertEquals(cartPage.getCartItemCount(), 3,
                "Cart should contain exactly 3 items");

        test.pass("3 items added to cart successfully");
    }

    // ─── TC15: Remove Item from Cart ──────────────────────────────────────────
    @Test(priority = 3, description = "Remove one item from cart and verify count decreases")
    public void removeItemFromCartTest() {
        test = extent.createTest("TC15 - Remove Item from Cart");

        CartPage cartPage = loginAddItemAndGoToCart(2);
        test.info("Added 2 products to cart");

        Assert.assertEquals(cartPage.getCartItemCount(), 2,
                "Cart should have 2 items before removal");

        cartPage.removeItemByIndex(0);
        test.info("Removed first item from cart");

        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Cart should have 1 item after removal");

        test.pass("Item removed from cart successfully");
    }

    // ─── TC16: Cart is Empty After Removing All Items ─────────────────────────
    @Test(priority = 4, description = "Cart should be empty after removing all items")
    public void cartEmptyAfterRemovingAllTest() {
        test = extent.createTest("TC16 - Cart Empty After Removing All Items");

        CartPage cartPage = loginAddItemAndGoToCart(1);
        test.info("Added 1 product to cart");

        cartPage.removeItemByIndex(0);
        test.info("Removed the only item from cart");

        Assert.assertTrue(cartPage.isCartEmpty(),
                "Cart should be empty after removing all items");
        

        test.pass("Cart is empty after removing all items");
    }

    // ─── TC17: Continue Shopping from Cart ───────────────────────────────────
    @Test(priority = 5, description = "Continue shopping button takes user back to products")
    public void continueShoppingTest() {
        test = extent.createTest("TC17 - Continue Shopping");

        CartPage cartPage = loginAddItemAndGoToCart(1);
        cartPage.clickContinueShopping();
        test.info("Clicked Continue Shopping button");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User should be back on inventory page");

        test.pass("Continue shopping works - user back on inventory page");
    }
}
