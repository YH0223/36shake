package com.example.NewWeb.CrawlPrice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceCrawlService {

    public Map<String, Map<String, Map<String, String>>> crawlWebsite(String name,int pricemin, int pricemax) {
        Map<String, Map<String, Map<String, String>>> results = new HashMap<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("no-sandbox");
        options.addArguments("disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("lang=ko");
        WebDriver driver = new ChromeDriver(options);
        // 각 웹사이트별로 크롤링 메서드 호출

        try {
            results.put("와인25플러스", crawlWine25(driver, name, pricemin, pricemax));
            results.put("겟주", crawlGetju(driver, name, pricemin, pricemax));
            results.put("달리", crawlDali(driver, name, pricemin, pricemax));
        } finally {
            driver.quit();
        }
        return results;
    }



    private Map<String, Map<String, String>> crawlWine25(WebDriver driver, String name,int pricemin, int pricemax) throws TimeoutException{
        String url = "https://www.gsshop.com/shop/wine/search.gs?tq=" + name;
        driver.get(url);

        List<WebElement> priceElements = driver.findElements(By.cssSelector("span.set-price > strong"));
        List<WebElement> linkElements = driver.findElements(By.cssSelector("a.prd-item.reduce"));
        List<WebElement> nameElements = driver.findElements(By.cssSelector("dt.prd-name"));

        Map<String, Map<String, String>> results = new LinkedHashMap<>();

        // 가격 정보 수집
        for (int i = 0; i < Math.min(priceElements.size(), linkElements.size()); i++) {
            String price = priceElements.get(i).getText();
            String link = linkElements.get(i).getAttribute("href");
            String nameinfo = nameElements.get(i).getText();
            int toint= Integer.parseInt(price.replaceAll(",", ""));
            System.out.println(toint);
            if(toint>=pricemin && toint<=pricemax){
                Map<String, String> priceLinkMap = new LinkedHashMap<>();
                priceLinkMap.put("Price", price+"원");
                priceLinkMap.put("Link", link);

                results.put(nameinfo, priceLinkMap);
            }
        }
        System.out.println(results);
        return results;
    }

    private Map<String, Map<String, String>> crawlGetju(WebDriver driver, String name,int pricemin, int pricemax) {
        String url = "https://m.getju.co.kr/shop/search_result.php?search_str=" + name;
        driver.get(url);

        List<WebElement> priceElements = driver.findElements(By.cssSelector("p.sell.sell > strong"));
        List<WebElement> linkElements = driver.findElements(By.cssSelector("p.name > a"));
        List<WebElement> nameElements = driver.findElements(By.cssSelector("p.name > a"));
        Map<String, Map<String, String>> results = new LinkedHashMap<>();

        // 가격 정보 수집
        for (int i = 0; i < Math.min(priceElements.size(), linkElements.size()); i++) {
            String price = priceElements.get(i).getText();
            String link = linkElements.get(i).getAttribute("href");
            String nameinfo = nameElements.get(i).getText();
            int toint= Integer.parseInt(price.substring(0, price.indexOf("원")).replaceAll(",", ""));
            if(toint>=pricemin && toint<=pricemax) {
                Map<String, String> priceLinkMap = new LinkedHashMap<>();
                priceLinkMap.put("Price", price);
                priceLinkMap.put("Link", link);

                results.put(nameinfo, priceLinkMap);
            }
        }
        System.out.println(results);
        return results;
    }

    private Map<String, Map<String, String>> crawlDali(WebDriver driver, String name,int pricemin, int pricemax)throws TimeoutException {
        String url = "https://www.daligo.co.kr/shop/search/item?type=smartOrder&searchText=" + name;
        driver.get(url);


        List<WebElement> priceElements = driver.findElements(By.cssSelector("span.item__price--current > em"));
        List<WebElement> linkElements = driver.findElements(By.cssSelector("a.item__link"));
        List<WebElement> nameElements = driver.findElements(By.cssSelector("strong.item__name"));
        Map<String, Map<String, String>> results = new LinkedHashMap<>();

        // 가격 정보 수집
        for (int i = 0; i < Math.min(priceElements.size(), linkElements.size()); i++) {
            String price = priceElements.get(i).getText();
            String link = linkElements.get(i).getAttribute("href");
            String nameinfo = nameElements.get(i).getText();
            int toint= Integer.parseInt(price.replaceAll(",", ""));
            if(toint>=pricemin && toint<=pricemax) {
                Map<String, String> priceLinkMap = new LinkedHashMap<>();
                priceLinkMap.put("Price", price+"원");
                priceLinkMap.put("Link", link);

                results.put(nameinfo, priceLinkMap);
            }
        }
        System.out.println(results);

        return results;
    }


}