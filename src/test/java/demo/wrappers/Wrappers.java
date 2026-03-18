package demo.wrappers;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrappers {

    ChromeDriver driver;
    WebDriverWait wait;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openFlipkart(String url) {
        driver.get(url);
    }

    public void closeLoginPopup() {

        try {
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'✕')]")));

            closeBtn.click();

        } catch (Exception e) {

            System.out.println("Login popup not displayed");
        }
    }

    public void searchProduct(String product) {

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        searchBox.clear();
        searchBox.sendKeys(product);
        searchBox.submit();
    }

    public void sortByPopularity() {

        WebElement sortBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Popularity']")));

        sortBtn.click();
    }

    public void countItemsWithRatingLessThan4() {

        List<WebElement> ratings = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'MKiFS6')]")));

        int count = 0;

        for (WebElement rating : ratings) {

            try {

                double value = Double.parseDouble(rating.getText());

                if (value <= 4) {
                    count++;
                }

            } catch (Exception ignored) {
            }
        }

        System.out.println("Items with rating <= 4 : " + count);
    }

    public void printTitleAndDiscount() {

        List<WebElement> titles = driver.findElements(By.xpath("//div[contains(@class,'RG5Slk')]"));

        List<WebElement> discounts = driver.findElements(By.xpath("//div[contains(@class,'HQe8jr')]/span"));

        int size = Math.min(titles.size(), discounts.size());

        for (int i = 0; i < size; i++) {

            try {

                String discountText = discounts.get(i).getText();

                String numericDiscount = discountText.replaceAll("[^0-9]", "");

                int discountValue = Integer.parseInt(numericDiscount);

                if (discountValue > 17) {

                    System.out.println("Title : " + titles.get(i).getText());
                    System.out.println("Discount : " + discountText);
                }

            } catch (Exception ignored) {
            }
        }
    }

    public void selectFourStarFilter() {

        WebElement filter = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='4★ & above']")));

        filter.click();
    }

    
    public void printTopReviewedMugs() {

        List<WebElement> titles = driver.findElements(By.xpath("//a[contains(@class,'pIpigb')]"));
        List<WebElement> reviews = driver.findElements(By.xpath("//span[contains(@class,'PvbNMB')]"));
        List<WebElement> images = driver.findElements(By.xpath("//img[contains(@class,'UCc1lI')]"));

        if (titles.isEmpty() || reviews.isEmpty()) {
            System.out.println("No products found");
            return;
        }

        Map<Integer, Integer> reviewMap = new HashMap<>();

        for (int i = 0; i < reviews.size(); i++) {

            try {

                String reviewText = reviews.get(i).getText().split(" ")[0];

                int reviewCount = Integer.parseInt(reviewText.replace(",", ""));

                reviewMap.put(i, reviewCount);

            } catch (Exception ignored) {
            }
        }

        List<Map.Entry<Integer, Integer>> sorted = new ArrayList<>(reviewMap.entrySet());

        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int limit = Math.min(5, sorted.size());

        for (int i = 0; i < limit; i++) {

            int index = sorted.get(i).getKey();

            System.out.println("Title : " + titles.get(index).getText());
            System.out.println("Image URL : " + images.get(index).getAttribute("src"));
        }
    }
}