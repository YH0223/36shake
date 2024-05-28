package com.example.NewWeb.Search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchResultIgController {



    @Autowired
    private SearchResultRepository searchResultRepository;
    @Autowired
    private SearchResultIgService searchResultIgService;

    @GetMapping(value = "/search_result_ig")
    public String getBookmarks(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam("keyword") String keyword) {

        // 모델에 북마크 데이터 추가
        model.addAttribute("ig", searchResultIgService.getIngredients(pageable,keyword));
        model.addAttribute("back", pageable.previousOrFirst().getPageNumber()); // 페이지 정보도 함께 전달
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("checknext", searchResultIgService.getIngredients(pageable,keyword).hasNext());
        model.addAttribute("checkprev", searchResultIgService.getIngredients(pageable,keyword).hasPrevious());
        model.addAttribute("keyword",keyword);
        System.out.println(model);
        // 뷰의 이름을 반환하여 해당 뷰로 이동
        return "search_result_ig"; // search_result_ig.mustache로 이동
    }



}