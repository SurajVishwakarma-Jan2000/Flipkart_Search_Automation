##Flipkart Product Search Automation

Automation testing project built using Selenium WebDriver, Java, and TestNG to simulate real user actions on the Flipkart website such as searching products, applying filters, sorting results, and extracting product details.

This project demonstrates UI automation testing, dynamic element handling, and clean automation framework design using wrapper methods.

Project Overview

The automation suite performs the following tasks on the Flipkart website:

Search for products

Handle login popup

Sort search results

Apply product filters

Extract product information such as:

Title

Discount

Rating

Review count

Product image URL

The framework uses reusable wrapper methods to keep the code clean and maintainable.


Test Scenarios Automated
Test Case 01 – Washing Machine Search

Open Flipkart

Close login popup

Search for Washing Machine

Sort products by Popularity

Count items with rating ≤ 4


Test Case 02 – iPhone Discount Extraction

Open Flipkart

Search for iPhone

Extract product title and discount

Print products with discount greater than 17%

Test Case 03 – Coffee Mug Review Analysis

Open Flipkart

Search for Coffee Mug

Apply 4★ & above filter

Extract top 5 most reviewed products

Print:

Product title

Product image URL

Tech Stack
Technology	Purpose
Java	Programming Language
Selenium WebDriver	UI Automation
TestNG	Test Framework
Gradle	Build Tool
ChromeDriver	Browser Automation


Project Structure

FlipkartAutomation
│
├── src
│   ├── main
│   │   └── demo
│   │       └── wrappers
│   │           └── Wrappers.java
│   │
│   └── test
│       └── demo
│           └── TestCases.java
│
├── build.gradle
├── README.md
⚙️ Setup & Run Instructions
1️⃣ Clone the repository
git clone https://github.com/yourusername/FlipkartAutomation.git
2️⃣ Navigate to project
cd FlipkartAutomation
3️⃣ Run tests
./gradlew test


Sample Output

Example console output:

TestCase01 : Washing Machine Search
Items with rating <= 4 : 12

TestCase02 : iPhone Discount
Title : Apple iPhone 14
Discount : 18% off

TestCase03 : Coffee Mug Review
Title : Ceramic Coffee Mug
Image URL : https://rukminim2.flixcart.com/...


Framework Highlights

✔ Wrapper-based reusable methods
✔ Explicit waits for stable automation
✔ Clean test structure using TestNG
✔ Dynamic data extraction
✔ Sorting and filtering validation

Learning Objectives

This project demonstrates practical skills in:

Selenium UI automation

Handling dynamic web elements

Data extraction from product listings

Test automation framework design

Writing maintainable automation code

