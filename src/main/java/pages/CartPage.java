package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    WebDriver driver;

    // ─── Web Elements ────────────────────────────────────────────────────────
    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    List<WebElement> cartItemNames;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    // ─── Constructor ─────────────────────────────────────────────────────────
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Actions ─────────────────────────────────────────────────────────────
    public int getCartItemCount() {
        return cartItems.size();
    }

    public String getCartItemNameByIndex(int index) {
        return cartItemNames.get(index).getText();
    }

    public void removeItemByIndex(int index) {
        cartItems.get(index)
                .findElement(By.tagName("button"))
                .click();
    }

    public void removeItemByName(String name) {
        for (WebElement item : cartItems) {
            String itemName = item.findElement(
                    By.className("inventory_item_name")).getText();
            if (itemName.equalsIgnoreCase(name)) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public boolean isItemInCart(String name) {
        return cartItemNames.stream()
                .anyMatch(e -> e.getText().equalsIgnoreCase(name));
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}
