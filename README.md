# 🛒 Magento E-commerce Automation Testing

## 📌 Project Overview

This project automates functional testing of key e-commerce features on the Magento demo site:  
🔗 [Magento Site](https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html)

The goal is to ensure reliable user experience and site stability across critical paths such as account handling, product search, and order placement.

---

## ✅ Features Covered (Test Cases)

1. 🔐 **Sign In** – Valid login scenario with registered user.
2. 🚪 **Sign Out** – User log-out process.
3. 📝 **Sign Up** – Registration of a new user.
4. 🔎 **Product Search** – Valid/invalid product search scenarios.
5. 🛒 **Complete Order** – Add product to cart and complete a full checkout cycle.

---

## 🧪 Tech Stack

| Category              | Technology                        |
|-----------------------|------------------------------------|
| Language              | Java                               |
| Automation Tool       | Selenium WebDriver                 |
| Test Framework        | TestNG                             |
| Design Pattern        | Page Object Model (POM)            |
| Build Tool            | Maven                              |
| Reporting             | Extent Reports (or Allure Reports) |
| Data Source           | Excel (.xlsx)                      |
| Version Control       | Git + GitHub                       |
| CI/CD (Bonus)         | GitHub Actions                     |
| Execution Mode        | Parallel execution via TestNG      |

---

## 📁 Project Structure

magento-testing/ ├── src/ │ ├── main/ │ │ └── java/ │ │ └── pages/ # Page Object Classes │ └── test/ │ └── java/ │ └── tests/ # Test Cases (TestNG) ├── testdata/ │ └── inputData.xlsx # Excel input for data-driven testing ├── reports/ │ └── ExtentReport.html # HTML report after test run ├── testng.xml # Parallel test configuration ├── pom.xml # Maven dependencies ├── .github/ │ └── workflows/ │ └── ci.yml # GitHub Actions workflow ├── README.md # You're here!

---

Security Testing Report  to report basic security validation and report any found issue, focusing on:
■ Input validation


## 🧰 Setup Instructions

### 1️⃣ Prerequisites

- Java JDK 11 or higher
- Maven
- Chrome browser
- ChromeDriver installed or use WebDriverManager

### 2️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/magento-testing.git
cd magento-testing

3️⃣ Run Test Suite
mvn clean test

🔁 Parallel Execution
Parallel execution is configured in testng.xml using <suite parallel="tests" thread-count="3">.
This improves execution speed by running test classes concurrently.

📊 Test Reports
📘 Extent Report
Generated automatically in /reports/ExtentReport.html.

To view:
open reports/ExtentReport.html

📖 Data-Driven Testing
Data (user credentials, search terms, etc.) are extracted from:

🧪 CI/CD with GitHub Actions (BONUS)
The pipeline is set up in:

Triggered on:
Code pushes

Pull requests to main

Actions Performed:
Builds project with Maven

Executes TestNG tests

Generates reports

🐞 Bug Reporting
Any identified issues during testing were documented with:

Description

Screenshots

💡 Improvements & Next Steps
Improve CI pipeline with Slack notifications or email alerts.

👨‍💻 Author
Mahmoud Senosy
Senior Software Test Automaation Engineer
📧 Email: mahmoudsenosy227@gmail.com
🌍 Location: Giza, Egypt
   Mobile : 01065824069