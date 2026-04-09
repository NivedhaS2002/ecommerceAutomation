package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    WebDriver driver;

    // ─── Web Elements — Step 1 (Your Info) ───────────────────────────────────
    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(css = "[data-test='error']")
    WebElement errorMessage;

    // ─── Web Elements — Step 2 (Overview) ────────────────────────────────────
    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    // ─── Web Elements — Step 3 (Confirmation) ────────────────────────────────
    @FindBy(className = "complete-header")
    WebElement confirmationHeader;

    @FindBy(className = "complete-text")
    WebElement confirmationText;

    @FindBy(id = "back-to-products")
    WebElement backToProductsButton;

    // ─── Constructor ─────────────────────────────────────────────────────────
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ─── Actions ─────────────────────────────────────────────────────────────
    public void enterFirstName(String name) {
        firstNameField.clear();
        firstNameField.sendKeys(name);
    }

    public void enterLastName(String name) {
        lastNameField.clear();
        lastNameField.sendKeys(name);
    }

    public void enterPostalCode(String code) {
        postalCodeField.clear();
        postalCodeField.sendKeys(code);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void fillCheckoutInfo(String firstName, String lastName, String zip) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(zip);
        clickContinue();
    }

    public void clickFinish() {
        finishButton.click();
    }

    public void clickBackToProducts() {
        backToProductsButton.click();
    }

    // ─── Getters ─────────────────────────────────────────────────────────────
    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }

    public String getConfirmationText() {
        return confirmationText.getText();
    }

    public String getTotalLabel() {
        return totalLabel.getText();
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isOrderConfirmed() {
        try {
            return confirmationHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
