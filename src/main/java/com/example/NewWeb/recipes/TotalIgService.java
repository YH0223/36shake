package com.example.NewWeb.recipes;

import com.example.NewWeb.Ingredient;
import com.example.NewWeb.modules.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TotalIgService {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Transactional
    public Page<Map<String, Object>> getIngredients(Pageable pageable) {
        Page<Ingredient> igPage = ingredientRepository.findAll(pageable);
        List<Map<String, Object>> igDataList = new ArrayList<>();
        for (Ingredient ingredient : igPage.getContent()) {
            ingredient = ingredientRepository.findFirstByid(ingredient.getid());
            System.out.println(ingredient);
            if (ingredient != null) {
                Map<String, Object> igData = new HashMap<>();
                igData.put("igkor", ingredient.getIgKor());
                igData.put("igen", ingredient.getigEn());
                igData.put("igyes", (ingredient.getigYes() == 1) ? true : false);
                igData.put("igid",ingredient.getid());
                igDataList.add(igData);
            }
        }
        System.out.println(igDataList);
        return new PageImpl<>(igDataList, pageable, igPage.getTotalElements());
    }
    @Transactional
    public boolean getListCheck() {

        Page<Ingredient> igPage = ingredientRepository.findAll(PageRequest.of(0, 10)); // 첫 번째 페이지 요청
        System.out.println(igPage.hasNext());
        return igPage.hasNext(); // 다음 페이지가 있으면 1을 반환, 없으면 0을 반환
    }

}
