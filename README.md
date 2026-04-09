# 🛒 E-Commerce Test Automation Project
### Selenium + Java + TestNG + ExtentReports
**Target Site:** [SauceDemo](https://www.saucedemo.com)

---

## 📁 Project Structure

```
EcommerceAutomation/
├── src/
│   ├── main/java/
│   │   └── pages/
│   │       ├── LoginPage.java        ← Login page elements & actions
│   │       ├── ProductPage.java      ← Product listing elements & actions
│   │       ├── CartPage.java         ← Cart elements & actions
│   │       └── CheckoutPage.java     ← Checkout elements & actions
│   └── test/java/
│       ├── base/
│       │   └── BaseTest.java         ← WebDriver setup, ExtentReports, Screenshots
│       └── tests/
│           ├── LoginTest.java        ← 6 Login test cases
│           ├── ProductTest.java      ← 6 Product test cases
│           ├── CartTest.java         ← 5 Cart test cases
│           └── CheckoutTest.java     ← 5 Checkout test cases
├── reports/                          ← HTML test reports generated here
├── screenshots/                      ← Failure screenshots saved here
├── testng.xml                        ← TestNG suite configuration
├── pom.xml                           ← Maven dependencies
└── README.md
```

---

## ✅ Test Cases Covered (22 Total)

| Module      | Test Cases |
|-------------|-----------|
| Login       | Valid login, Invalid password, Empty username, Empty password, Locked user, Logout |
| Products    | Product count, Sort A-Z, Sort Z-A, Sort price low-high, Sort price high-low, Cart badge |
| Cart        | Add single item, Add multiple items, Remove item, Empty cart, Continue shopping |
| Checkout    | Full purchase flow, Empty first name, Empty last name, Empty postal code, Order total |

---

## 🛠️ Prerequisites

1. **Java JDK 11+** → https://www.oracle.com/java/technologies/downloads/
2. **Maven** → https://maven.apache.org/download.cgi
3. **Chrome Browser** (latest)
4. **IntelliJ IDEA** or **Eclipse**

---

## 🚀 How to Run

### Option 1: Run from IntelliJ / Eclipse
1. Open the project
2. Right-click `testng.xml` → Run

### Option 2: Run from Terminal (Maven)
```bash
mvn clean test
```

### Option 3: Run specific test class
```bash
mvn test -Dtest=LoginTest
```

---

## 📊 View Report
After running, open the generated HTML file:
```
reports/TestReport_YYYY-MM-DD_HH-mm-ss.html
```
Open it in any browser to see pass/fail results with screenshots.

---

## 🧰 Technologies Used

| Technology | Version | Purpose |
|---|---|---|
| Java | 11 | Programming Language |
| Selenium WebDriver | 4.18.1 | Browser Automation |
| TestNG | 7.9.0 | Test Framework |
| WebDriverManager | 5.7.0 | Auto ChromeDriver setup |
| ExtentReports | 5.1.1 | HTML Test Reports |
| Maven | 3.x | Build & Dependency Management |

---

## 💡 Design Patterns Used
- **Page Object Model (POM)** — Separates page elements from test logic
- **Base Test Class** — Common setup/teardown for all tests
- **Data-Driven** — Test data passed as parameters

---

## 🔗 Put This on Your Resume

> **Project:** E-Commerce Test Automation Framework | Selenium + Java + TestNG  
> - Automated **22 test cases** covering Login, Products, Cart, and Checkout modules  
> - Implemented **Page Object Model (POM)** design pattern for code reusability  
> - Integrated **ExtentReports** for detailed HTML reporting with screenshots on failure  
> - Used **WebDriverManager** for automatic browser driver management  
> - Managed source code on **GitHub** using Git version control
