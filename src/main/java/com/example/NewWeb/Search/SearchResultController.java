package com.example.NewWeb.Search;

import com.example.NewWeb.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchResultController {

    @Autowired
    private SearchResultService searchResultService;

    @GetMapping(value = "/search_result")
    public String getBookmarks(Model model, @PageableDefault(size = 10, sort = "id", direction =
            Sort.Direction.DESC) Pageable pageable,@RequestParam("keyword")String keyword) {
        Page<SearchResult> searchResultsPage = searchResultService.getCocktails(pageable,keyword);

        model.addAttribute("cocktails", searchResultsPage);
        model.addAttribute("back", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("checknext", searchResultsPage.hasNext());
        model.addAttribute("checkprev", searchResultsPage.hasPrevious());
        model.addAttribute("keyword",keyword);

        return "search_result";
    }
}