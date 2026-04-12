# SaaS Selenium Hybrid Framework

A production-grade **Test Automation Framework** built for SaaS-based multi-tenant applications using **Selenium WebDriver 4.x + Java + TestNG**. Implements a Hybrid Framework combining the **Page Object Model (POM)** and **Data-Driven** design patterns for scalable and maintainable test automation.

---

## 🚀 Framework Highlights

- **90+ automated test scripts** covering end-to-end SaaS application workflows
- **~40% reduction** in manual regression effort through automation
- **500+ test cases** across functional, regression, UI, and security test scenarios
- Supports **multi-tenant test execution** — validates tenant isolation, role-based access control, and tenant-specific configurations
- Integrated **ExtentReports** for rich HTML test execution reports
- **Data-Driven** approach using Apache POI for Excel-based test data management
- **Log4j2** logging for full execution traceability

---

## 🛠️ Tech Stack

| Category | Technology | Version |
|---|---|---|
| Automation | Selenium WebDriver | 4.38.0 |
| Language | Java | 11+ |
| Test Framework | TestNG | 7.11.0 |
| Build Tool | Maven | 3.x |
| Reporting | ExtentReports | 5.1.2 |
| Data-Driven | Apache POI (Excel) | 5.5.0 |
| Logging | Log4j2 | 2.25.2 |
| Database | MySQL Connector | 8.0.33 |
| Utilities | Apache Commons IO / Lang3 | Latest |

---

## 🏗️ Framework Architecture

```
SaaS_Selenium_Hybrid_Framework/
│
├── src/test/java/
│   ├── pageObjects/          # Page Object Model classes (one class per page)
│   ├── testCases/            # TestNG test classes
│   ├── utilities/            # Reusable helper classes
│   │   ├── BaseClass.java    # WebDriver setup & teardown
│   │   ├── ExcelUtils.java   # Apache POI — reads test data from Excel
│   │   ├── ExtentReport.java # ExtentReports configuration
│   │   └── Log4jUtils.java   # Logging utility
│   └── testData/             # Excel files with test data
│
├── logs/                     # Log4j2 execution logs
├── test-output/              # TestNG & ExtentReports HTML reports
├── pom.xml                   # Maven dependencies
└── testng.xml                # TestNG suite configuration
```

---

## 🔑 Key Design Patterns

### Page Object Model (POM)
Each page of the application has a dedicated Java class that holds:
- Web element locators (`@FindBy`)
- Page-specific action methods
- No assertions inside page classes (clean separation of concerns)

### Data-Driven Testing
- Test data stored externally in Excel sheets (`.xlsx`)
- Apache POI reads data at runtime — no hardcoded test data in scripts
- Enables running the same test with multiple data sets

### Hybrid Approach
Combines POM (for maintainability) + Data-Driven (for scalability) so tests are:
- Easy to maintain when UI changes
- Easy to scale by just adding rows to Excel

---

## ✅ Test Coverage

| Module | Test Types Covered |
|---|---|
| Login & Authentication | Functional, Negative, Role-based |
| Tenant Configuration | Functional, Multi-tenant isolation |
| User Role Management | Access control, Permission validation |
| Core Business Workflows | End-to-end, Regression |
| UI Validation | Cross-browser, Layout checks |
| Security | SQL Injection, XSS input validation |

---

## ⚙️ Prerequisites

- Java JDK 11 or above
- Maven 3.x
- Chrome / Firefox browser
- Eclipse or IntelliJ IDEA

---

## 🔧 Setup & Run

**1. Clone the repository**
```bash
git clone https://github.com/bharathdinesh-dev/SaaS_Selenium_Hybrid_Framework.git
cd SaaS_Selenium_Hybrid_Framework
```

**2. Install dependencies**
```bash
mvn clean install
```

**3. Run the test suite**
```bash
mvn test
```

**4. Run a specific TestNG suite**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Test Reports

After execution, ExtentReports generates a detailed HTML report at:
```
test-output/ExtentReport.html
```

The report includes:
- Pass / Fail / Skip summary
- Step-by-step execution logs
- Screenshots on failure
- Execution timestamp and duration

---

## 📋 Sample Test Scenario

```java
@Test(dataProvider = "loginData")
public void verifyLoginFunctionality(String username, String password, String expectedResult) {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUsername(username);
    loginPage.enterPassword(password);
    loginPage.clickLogin();
    Assert.assertEquals(loginPage.getResult(), expectedResult);
}
```

---

## 👨‍💻 Author

**Dinesh K** — QA Automation Engineer

- 📧 bharathdinesh.dev@gmail.com
- 🔗 [LinkedIn](https://linkedin.com/in/bharathdinesh-dev)
- 💼 1.8 years experience in Selenium, Java, TestNG, REST Assured

---

## 📄 License

This project is for portfolio and demonstration purposes.
