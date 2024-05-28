package com.example.NewWeb.bookmark;

import com.example.NewWeb.BookmarkIg;
import com.example.NewWeb.Ingredient;
import com.example.NewWeb.modules.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class BookmarkIgService {
    @Autowired
    private BookmarkIgRepository bookmarkIgRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Transactional
    public Page<Map<String, Object>> getIgBookmarks(String userId, Pageable pageable) {
        userId="admin";
        Page<BookmarkIg> bookmarkigPage = bookmarkIgRepository.findByUseridOrderByBmignoAsc(userId, pageable);
        List<Map<String, Object>> bookmarkigDataList = new ArrayList<>();
        for (BookmarkIg bookmarkig : bookmarkigPage.getContent()) {
            int setid = bookmarkig.getbmigid();
            Ingredient ingredient = ingredientRepository.findFirstByid(setid);
            System.out.println(ingredient);
            if (ingredient != null) {
                Map<String, Object> bookmarkigData = new HashMap<>();
                bookmarkigData.put("igkor", ingredient.getIgKor());
                bookmarkigData.put("igen", ingredient.getigEn());
                bookmarkigData.put("igyes", (ingredient.getigYes() == 1) ? true : false);
                bookmarkigData.put("igid",ingredient.getid());
                bookmarkigDataList.add(bookmarkigData);
            }
        }
        System.out.println(bookmarkigDataList);
        return new PageImpl<>(bookmarkigDataList, pageable, bookmarkigPage.getTotalElements());
    }

}
