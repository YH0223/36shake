package com.example.NewWeb.Search;

import com.example.NewWeb.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SearchResultService {

    @Autowired
    private SearchResultRepository searchResultRepository;
    @Transactional(readOnly = true)
    public Page<SearchResult> getCocktails(Pageable pageable, String keyword) {
        return searchResultRepository.findBydrinknameContaining(pageable,keyword);
    }



}