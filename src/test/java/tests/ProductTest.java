package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest extends BaseTest {

    // Helper: login before product tests
    private ProductPage loginAndGetProductPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        return new ProductPage(driver);
    }

    // ─── TC07: Verify Products Are Displayed ──────────────────────────────────
    @Test(priority = 1, description = "All products should be visible on inventory page")
    public void verifyProductsDisplayedTest() {
        test = extent.createTest("TC07 - Products Displayed");

        ProductPage productPage = loginAndGetProductPage();
        test.info("Logged in and on inventory page");

        int count = productPage.getProductCount();
        Assert.assertEquals(count, 6, "There should be 6 products on the page");

        test.pass("All 6 products displayed correctly");
    }

    // ─── TC08: Sort Products A to Z ───────────────────────────────────────────
    @Test(priority = 2, description = "Products should sort A to Z correctly")
    public void sortProductsAtoZTest() {
        test = extent.createTest("TC08 - Sort A to Z");

        ProductPage productPage = loginAndGetProductPage();
        productPage.sortBy("az");
        test.info("Sorted products A to Z");

        List<String> names = productPage.getAllProductNames();
        String firstName = names.get(0);
        String lastName  = names.get(names.size() - 1);

        Assert.assertTrue(firstName.compareTo(lastName) < 0,
                "First product should come before last alphabetically");

        test.pass("Products sorted A to Z correctly. First: " + firstName + " | Last: " + lastName);
    }

    // ─── TC09: Sort Products Z to A ───────────────────────────────────────────
    @Test(priority = 3, description = "Products should sort Z to A correctly")
    public void sortProductsZtoATest() {
        test = extent.createTest("TC09 - Sort Z to A");

        ProductPage productPage = loginAndGetProductPage();
        productPage.sortBy("za");
        test.info("Sorted products Z to A");

        List<String> names = productPage.getAllProductNames();
        String firstName = names.get(0);
        String lastName  = names.get(names.size() - 1);

        Assert.assertTrue(firstName.compareTo(lastName) > 0,
                "First product should come after last in reverse alphabetical order");

        test.pass("Products sorted Z to A correctly. First: " + firstName + " | Last: " + lastName);
    }

    // ─── TC10: Sort Products Price Low to High ────────────────────────────────
    @Test(priority = 4, description = "Products sorted by price low to high")
    public void sortPriceLowToHighTest() {
        test = extent.createTest("TC10 - Sort Price Low to High");

        ProductPage productPage = loginAndGetProductPage();
        productPage.sortBy("lohi");
        test.info("Sorted products by price low to high");

        List<Double> prices = productPage.getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Price at index " + i + " should be <= price at index " + (i + 1));
        }

        test.pass("Products sorted by price low to high correctly. "
                + "Min: $" + prices.get(0) + " | Max: $" + prices.get(prices.size() - 1));
    }

    // ─── TC11: Sort Products Price High to Low ────────────────────────────────
    @Test(priority = 5, description = "Products sorted by price high to low")
    public void sortPriceHighToLowTest() {
        test = extent.createTest("TC11 - Sort Price High to Low");

        ProductPage productPage = loginAndGetProductPage();
        productPage.sortBy("hilo");
        test.info("Sorted products by price high to low");

        List<Double> prices = productPage.getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
                    "Price at index " + i + " should be >= price at index " + (i + 1));
        }

        test.pass("Products sorted by price high to low correctly. "
                + "Max: $" + prices.get(0) + " | Min: $" + prices.get(prices.size() - 1));
    }

    // ─── TC12: Add Product to Cart Updates Badge ──────────────────────────────
    @Test(priority = 6, description = "Cart badge updates when product is added")
    public void addProductUpdatesCartBadgeTest() {
        test = extent.createTest("TC12 - Cart Badge Updates");

        ProductPage productPage = loginAndGetProductPage();
        productPage.addFirstProductToCart();
        test.info("Added first product to cart");

        String badge = productPage.getCartBadgeCount();
        Assert.assertEquals(badge, "1", "Cart badge should show 1 after adding one product");

        test.pass("Cart badge updated to 1 after adding product");
    }
}
