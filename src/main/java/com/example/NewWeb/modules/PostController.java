package com.example.NewWeb.modules;

import com.example.NewWeb.Cocktail;
import com.example.NewWeb.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping(value = "/showpost")
    public String showPost(@RequestParam("drinkId") int drinkId, Model model) {
        try {
            Cocktail cocktail = cocktailRepository.findBydrinkid(drinkId);
            if (cocktail == null) {
                System.out.println("Cocktail with id " + drinkId + " not found.");
                // 에러 처리를 하고 싶다면 여기에 적절한 처리를 추가합니다.
                model.addAttribute("error", "Cocktail not found");
                return "error"; // 에러가 발생한 경우 showpost 템플릿을 반환합니다.
            }

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("name", cocktail.getDrinkname());
            responseData.put("instructions", cocktail.getInstructions());
            responseData.put("drinkId", drinkId);
            Ingredient ingredient;
            List<String> ingredients = new ArrayList<>();
            List<Integer> igids=new ArrayList<>();
            List<Integer> igyes=new ArrayList<>();
            for (int i = 0; i < cocktail.getEngIngredients().size(); i++) {
                ingredient = ingredientRepository.findByigen(cocktail.getEngIngredients().get(i));
                if (ingredient != null && !ingredient.getIgKor().isEmpty()) {
                    String ingredientDetail = ingredient.getIgKor() + ": " + cocktail.getEngMeasures().get(i);
                    ingredients.add(ingredientDetail);
                    igids.add(ingredient.getid());
                    igyes.add(ingredient.getigYes());
                }
            }
            responseData.put("ingredients", ingredients);
            responseData.put("igids",igids);
            responseData.put("igyes",igyes);
            model.addAttribute("cocktailData", responseData); // 뷰로 전달할 데이터를 모델에 추가
            System.out.println(model);
            return "showpost"; // 정상적인 경우 showpost 템플릿을 반환합니다.
        } catch (NumberFormatException e) {
            System.err.println("Invalid idDrink format: " + e.getMessage());
            // 에러 처리를 하고 싶다면 여기에 적절한 처리를 추가합니다.
            model.addAttribute("error", "Invalid idDrink format");
            return "error"; // 에러가 발생한 경우 showpost 템플릿을 반환합니다.
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e.getMessage());
            e.printStackTrace();
            // 에러 처리를 하고 싶다면 여기에 적절한 처리를 추가합니다.
            model.addAttribute("error", "An exception occurred");
            return "error"; // 에러가 발생한 경우 showpost 템플릿을 반환합니다.
        }
    }
}