package com.example.NewWeb.recommendations;

import com.example.NewWeb.BookmarkIg;
import com.example.NewWeb.CktRecommendation;
import com.example.NewWeb.Ingredient;
import com.example.NewWeb.bookmark.BookmarkIgRepository;
import com.example.NewWeb.modules.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CktRecommendationService {

    @Autowired
    private BookmarkIgRepository bookmarkIgRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CktRecommendationRepository cktRecommendationRepository;

    public Map<Integer, Map<String, Integer>> recommendCocktails(String userId) {
        // 특정 사용자의 북마크된 재료 정보 가져오기
        List<BookmarkIg> bookmarkIgList = bookmarkIgRepository.findByUseridOrderByBmignoAsc(userId);

        // 재료 수별 추천 결과를 담을 맵 초기화
        Map<Integer, Map<String, Integer>> finalRecommendations = new TreeMap<>();

        // 이미 처리한 칵테일을 기록할 Set 초기화
        Set<String> processedCocktails = new HashSet<>();

        // 칵테일 목록 불러오기
        List<CktRecommendation> cocktails = cktRecommendationRepository.findByAlcoholic("Alcoholic");

        // 각 북마크된 재료에 대해 처리
        for (BookmarkIg bookmarkIg : bookmarkIgList) {
            // 재료 가져오기
            Ingredient ingredient = ingredientRepository.findFirstByid(bookmarkIg.getbmigid());
            String tempIgname = ingredient.getigEn();

            // 각 칵테일에 대해 부족한 재료 수 계산하고 결과를 독립적으로 관리
            for (CktRecommendation cktRCD : cocktails) {
                String drinkName = cktRCD.getDrinkname();
                // 이미 처리한 칵테일인지 확인
                if (!processedCocktails.contains(drinkName)) {
                    int missingIngredientsCount = calculateMissingIngredients(tempIgname, cktRCD);
                    // 재료 수별 맵을 가져오고 없으면 새로 생성
                    Map<String, Integer> recommendations = finalRecommendations.computeIfAbsent(missingIngredientsCount, k -> new HashMap<>());
                    // 칵테일 ID 추가
                    recommendations.put(drinkName, cktRCD.getdrinkid());
                    // 이미 처리한 칵테일로 기록
                    processedCocktails.add(drinkName);
                }
            }
        }

        // 결과 출력
        System.out.println(finalRecommendations);

        // 상위 20개만 선택하여 반환하거나 필요에 따라 다른 처리를 수행할 수 있습니다.
        return getTop20Recommendations(finalRecommendations);
    }
        private int calculateMissingIngredients(String tempIgname, CktRecommendation cktRecommendation) {

        //칵테일에 포함되는 재료 찾기
        List<String> cocktailIngredients = cktRecommendation.getEngIngredients();

        tempIgname = tempIgname.toLowerCase();
        // 칵테일의 재료 중 사용자가 보유하지 않은 재료 개수 계산
        int missingIngredientsCount = 0;
            for (String ig : cocktailIngredients) {
                if (ig != null ) {
                    ig = ig.toLowerCase();
                    if (!ig.equals(tempIgname) && !ig.contains(tempIgname)) {
                        missingIngredientsCount++;
                    }
                }
            }
        return missingIngredientsCount-1;
    }

    // finalRecommendations 맵에서 상위 20개 항목을 선택하는 메서드
    private Map<Integer, Map<String, Integer>> getTop20Recommendations(Map<Integer, Map<String, Integer>> finalRecommendations) {
        Map<Integer, Map<String, Integer>> top20Recommendations = new TreeMap<>();

        int count = 0;
        for (Map.Entry<Integer, Map<String, Integer>> entry : finalRecommendations.entrySet()) {
            int missingIngredientsCount = entry.getKey();
            Map<String, Integer> recommendations = entry.getValue();

            for (Map.Entry<String, Integer> drinkEntry : recommendations.entrySet()) {
                if (count >= 20) {
                    break;
                }

                String drinkName = drinkEntry.getKey();
                Integer drinkId = drinkEntry.getValue();

                // 상위 20개 추천에 추가
                top20Recommendations.computeIfAbsent(missingIngredientsCount, k -> new HashMap<>())
                        .put(drinkName, drinkId);

                count++;
            }
        }
        System.out.println(top20Recommendations);
        return top20Recommendations;
    }
}
