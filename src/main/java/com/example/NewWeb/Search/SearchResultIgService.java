package com.example.NewWeb.Search;

import com.example.NewWeb.SearchResultIg;
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
public class SearchResultIgService {



    @Autowired
    private SearchResultIgRepository searchResultIgRepository;
    @Transactional
    public Page<Map<String, Object>> getIngredients(Pageable pageable,String keyword) {
        Page<SearchResultIg> searchigPage = searchResultIgRepository.findByigkor(pageable,keyword);
        List<Map<String, Object>> searchigDataList = new ArrayList<>();
        for (SearchResultIg searchresultig : searchigPage.getContent()) {
            searchresultig = searchResultIgRepository.findFirstByid(searchresultig.getid());
            System.out.println(searchresultig);
            if (searchresultig != null) {
                Map<String, Object> igsearchData = new HashMap<>();
                igsearchData.put("igkor", searchresultig.getIgKor());
                igsearchData.put("igen", searchresultig.getigEn());
                igsearchData.put("igyes", (searchresultig.getigYes() == 1) ? true : false);
                igsearchData.put("igid",searchresultig.getid());
                searchigDataList.add(igsearchData);
            }
        }
        System.out.println(searchigDataList);
        return new PageImpl<>(searchigDataList, pageable, searchigPage.getTotalElements());
    }



}