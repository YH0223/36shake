package com.example.NewWeb.recipes;

import com.example.NewWeb.modules.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TotalIgController {



    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private TotalIgService totalIgService;

    @GetMapping(value = "/recipesig")
    public String getBookmarks(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        // 모델에 북마크 데이터 추가
        model.addAttribute("ig", totalIgService.getIngredients(pageable));
        model.addAttribute("back", pageable.previousOrFirst().getPageNumber()); // 페이지 정보도 함께 전달
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("checknext", totalIgService.getIngredients(pageable).hasNext());
        model.addAttribute("checkprev", totalIgService.getIngredients(pageable).hasPrevious());
        System.out.println(model);
        // 뷰의 이름을 반환하여 해당 뷰로 이동
        return "recipesig"; // bookmarksig.mustache로 이동
    }



}