package demo.wrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wrappers {

    ChromeDriver driver;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
    }

    public void openFlipkart(String url) {
        driver.get(url);
    }

    public void closeLoginPopup() {

        try {

            driver.findElement(By.xpath("//span[contains(text(),'✕')]")).click();

        } catch (Exception e) {

            System.out.println("Login popup not displayed");
        }
    }

    public void searchProduct(String product) {

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys(product);
        searchBox.submit();
    }

    public void sortByPopularity() {

        driver.findElement(By.xpath("//div[text()='Popularity']")).click();
    }

    public void countItemsWithRatingLessThan4() {

        List<WebElement> ratings = driver.findElements(By.xpath("//div[contains(@class,'MKiFS6')]"));

        int count = 0;

        for (WebElement rating : ratings) {

            try {

                double value = Double.parseDouble(rating.getText());

                if (value <= 4) {
                    count++;
                }

            } catch (Exception e) {

                continue;
            }
        }

        System.out.println("Items with rating <=4 : " + count);
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

            } catch (Exception e) {

                continue;
            }
        }
    }

    public void selectFourStarFilter() {

        driver.findElement(By.xpath("//div[text()='4★ & above']")).click();
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

            } catch (Exception e) {

                continue;
            }
        }

        List<Map.Entry<Integer, Integer>> sorted = new ArrayList<>(reviewMap.entrySet());

        sorted.sort((a, b) -> b.getValue() - a.getValue());

        int limit = Math.min(5, sorted.size());

        for (int i = 0; i < limit; i++) {

            int index = sorted.get(i).getKey();

            System.out.println("Title: " + titles.get(index).getText());

            System.out.println("Image URL: "
                    + images.get(index).getAttribute("src"));
        }
    }
}