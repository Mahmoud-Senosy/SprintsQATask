# Project Setup Instructions

## Prerequisites
Ensure you have the following installed:
- **Java JDK 8 or later**
- **Maven**
- **IntelliJ IDEA / Eclipse (or any Java IDE)**
- **Google Chrome & ChromeDriver**

## Clone the Repository
```sh
 git clone <repository-url>
 cd selenium-project
```

## Install Dependencies
Run the following command to install dependencies:
```sh
mvn clean install
```

## Project Structure
- `src/main/java/helpers`: Contains API helper method 
- `src/test/pages`: Contains web pages with element locators and methods.
- `testng.xml`: Controls test execution.
- `pom.xml`: Manages dependencies.
- `test/ApiGeneratCustomerToken`: API Method to Generate Token 
- `test/ApiUseCustomer`: API Method to use the data from customer by API
- `test/CreateCustomerTest`: API Method to create a customer by API
- `test/MagentoTest`: Contains UI Testing Suits 
## Running Tests
To execute tests, use:
```sh
mvn tes
```

### Running Tests in Parallel
Parallel execution is controlled via `testng.xml`:
```xml

```


## Viewing Test Reports
Reports are automatically generated in the `test-output` directory. Open `test-output/index.html` in a browser to view the detailed test results.

## Troubleshooting
- **Ensure Java and Maven are correctly installed** using:
  ```sh
  java -version
  mvn -version
  ```
- **Check WebDriver compatibility**: Ensure `ChromeDriver` matches your Chrome browser version.
- **Clean project and recompile if issues persist**:
  ```sh
  mvn clean test
  ```


## **Test Coverage Strategy for Magento E-Commerce Website**
### **1️⃣ Critical Functional Test Scenarios**
✅ User Authentication, Product Search, Shopping Cart, Checkout, Payment, and Order Management

### **2️⃣ UI & Usability Testing**
✅ Mobile responsiveness, Font consistency, Navigation ease

### **3️⃣ API Testing**
✅ Validating key endpoints (`/V1/customers/me`, `/V1/orders`, etc.)

### **4️⃣ Performance & Load Testing**
✅ Response time, order processing under load

### **6️⃣ Compatibility Testing**
✅ Cross-browser and device testing

---

## **Challenges in the Task & Solutions**
1. **No Unique Element ID** → Used **Relative XPath** for locating elements
2. **Dependent Elements (Cart Update)** → Used **Explicit Waits**
3. **Shipping Cost Variability** → Tested checkout for countries with and without free shipping
4. **API Token Expiry** → Validated token expiration & renewal
5.**Reporting** if I have more time I can add Extended repote
---

### **📌 Final Thoughts**
This setup ensures a structured and **efficient test automation framework** for Magento, covering **UI, API, testing**.

