package com.example.NewWeb.recommendations;

import com.example.NewWeb.modules.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CktRecommendationController {
    @Autowired
    private CktRecommendationService cktRS;
    private IngredientRepository ingredientRepository;


    @GetMapping(value = "/recommendation")
    public String getRecommendations(String userId, Model model) {
        userId = "admin";
        Map<Integer, Map<String, Integer>> recommendations = cktRS.recommendCocktails(userId);

        // DTO 객체를 만들어서 recommendations 맵을 그룹화하여 변환
        List<CocktailRecommendationDTO> recommendationDTOs = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Integer>> entry : recommendations.entrySet()) {
            int missingIngredientsCount = entry.getKey();
            Map<String, Integer> innerMap = entry.getValue();

            // 그룹화된 추천 결과를 DTO로 변환하여 추가
            List<RecommendationDetail> recommendationDetails = innerMap.entrySet().stream()
                    .map(innerEntry -> new RecommendationDetail(innerEntry.getKey(), innerEntry.getValue()))
                    .collect(Collectors.toList());

            CocktailRecommendationDTO dto = new CocktailRecommendationDTO(missingIngredientsCount, recommendationDetails);
            recommendationDTOs.add(dto);
        }

        model.addAttribute("finalrecommendations", recommendationDTOs);
        return "recommendation";
    }

    private class CocktailRecommendationDTO {
        private int missingIngredientsCount;
        private List<RecommendationDetail> recommendations;

        public CocktailRecommendationDTO(int missingIngredientsCount, List<RecommendationDetail> recommendations) {
            this.missingIngredientsCount = missingIngredientsCount;
            this.recommendations = recommendations;
        }

        public int getMissingIngredientsCount() {
            return missingIngredientsCount;
        }

        public List<RecommendationDetail> getRecommendations() {
            return recommendations;
        }
    }

    private class RecommendationDetail {
        private String drinkName;
        private int drinkId;

        public RecommendationDetail(String drinkName, int drinkId) {
            this.drinkName = drinkName;
            this.drinkId = drinkId;
        }

        public String getDrinkName() {
            return drinkName;
        }

        public int getDrinkId() {
            return drinkId;
        }
    }
}
