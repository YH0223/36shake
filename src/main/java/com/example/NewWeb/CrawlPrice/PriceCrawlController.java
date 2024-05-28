package com.example.NewWeb.CrawlPrice;

import com.example.NewWeb.Ingredient;
import com.example.NewWeb.modules.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PriceCrawlController {

    @Autowired
    private PriceCrawlService priceCrawlService;
    @Autowired
    private IngredientRepository ingredientRepository;


    @GetMapping(value = "/PriceList")
    public Model crawl(@RequestParam("id") int igid,@RequestParam("pricemin") int pricemin,@RequestParam("pricemax") int pricemax, Model model) {
        Ingredient ingredient = ingredientRepository.findById(igid).orElse(null);
        if (ingredient == null) {
            throw new IllegalArgumentException("Invalid ingredient id: " + igid);
        }

        // 크롤링 결과 받아오기
        Map<String, Map<String, Map<String, String>>> results = priceCrawlService.crawlWebsite(ingredient.getIgKor(),pricemin,pricemax);

        // 데이터를 풀어서 저장
        List<SiteResult> siteResults = new ArrayList<>();
        for (Map.Entry<String, Map<String, Map<String, String>>> siteEntry : results.entrySet()) {
            String siteName = siteEntry.getKey();
            Map<String, Map<String, String>> items = siteEntry.getValue();
            for (Map.Entry<String, Map<String, String>> itemEntry : items.entrySet()) {
                String itemName = itemEntry.getKey();
                Map<String, String> itemDetails = itemEntry.getValue();
                String price = itemDetails.get("Price");
                String link = itemDetails.get("Link");

                siteResults.add(new SiteResult(siteName, itemName, price, link));
            }
        }

        // 모델에 데이터 추가
        model.addAttribute("name", ingredient.getIgKor());
        model.addAttribute("siteResults", siteResults);
        model.addAttribute("igid",ingredient.getid());

        // 결과를 표시할 뷰 이름 반환
        return model;
    }

    // SiteResult 클래스 추가
    public static class SiteResult {
        private String siteName;
        private String itemName;
        private String price;
        private String link;

        public SiteResult(String siteName, String itemName, String price, String link) {
            this.siteName = siteName;
            this.itemName = itemName;
            this.price = price;
            this.link = link;
        }

        // Getters and setters
        public String getSiteName() { return siteName; }
        public void setSiteName(String siteName) { this.siteName = siteName; }

        public String getItemName() { return itemName; }
        public void setItemName(String itemName) { this.itemName = itemName; }

        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }

        public String getLink() { return link; }
        public void setLink(String link) { this.link = link; }
    }
}
