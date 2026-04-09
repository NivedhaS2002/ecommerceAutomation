package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage {

    WebDriver driver;

    // ─── Web Elements ────────────────────────────────────────────────────────
    @FindBy(className = "inventory_item")
    List<WebElement> productItems;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    @FindBy(css = ".shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(css = ".shopping_cart_link")
    WebElement cartIcon;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    // ─── Constructor ─────────────────────────────────────────────────────────
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Actions ─────────────────────────────────────────────────────────────
    public int getProductCount() {
        return productItems.size();
    }

    public String getFirstProductName() {
        return productNames.get(0).getText();
    }

    public void sortBy(String option) {
        // Options: "az", "za", "lohi", "hilo"
        Select select = new Select(sortDropdown);
        select.selectByValue(option);
    }

    public void addFirstProductToCart() {
        // Click "Add to cart" button of first product
        productItems.get(0)
                .findElement(org.openqa.selenium.By.tagName("button"))
                .click();
    }

    public void addProductToCartByIndex(int index) {
        productItems.get(index)
                .findElement(org.openqa.selenium.By.tagName("button"))
                .click();
    }

    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void goToCart() {
        cartIcon.click();
    }

    public void clickProductByName(String name) {
        for (WebElement el : productNames) {
            if (el.getText().equalsIgnoreCase(name)) {
                el.click();
                break;
            }
        }
    }

    public List<String> getAllProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Double> getAllProductPrices() {
        return productPrices.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(java.util.stream.Collectors.toList());
    }

    public void logout() {
        menuButton.click();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        logoutLink.click();
    }
}
