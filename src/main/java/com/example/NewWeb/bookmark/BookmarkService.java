package com.example.NewWeb.bookmark;

import com.example.NewWeb.Bookmark;
import com.example.NewWeb.Cocktail;
import com.example.NewWeb.modules.CocktailRepository;
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
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private CocktailRepository cocktailRepository;
    @Transactional
    public Page<Map<String, Object>> getBookmarks(String userId, Pageable pageable) {
        userId="admin";
        Page<Bookmark> bookmarkPage = bookmarkRepository.findByUseridOrderByBookmarknoAsc(userId, pageable);

        List<Map<String, Object>> bookmarkDataList = new ArrayList<>();
        for (Bookmark bookmark : bookmarkPage.getContent()) {
            Cocktail cocktail = cocktailRepository.findBydrinkid(bookmark.getdrinkno());
            if (cocktail != null) {
                Map<String, Object> bookmarkData = new HashMap<>();
                bookmarkData.put("drinkId", bookmark.getdrinkno());
                bookmarkData.put("name", cocktail.getDrinkname());
                bookmarkDataList.add(bookmarkData);
            }
        }
        System.out.println(bookmarkDataList);
        return new PageImpl<>(bookmarkDataList, pageable, bookmarkPage.getTotalElements());
    }

}
