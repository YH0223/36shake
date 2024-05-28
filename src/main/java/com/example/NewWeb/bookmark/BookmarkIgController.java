package com.example.NewWeb.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BookmarkIgController {



    @Autowired
    private BookmarkIgService bookmarkIgService;

    @GetMapping(value = "/bookmarksig")
    public String getBookmarks(Model model, @PageableDefault(size = 10, sort = "bmigno", direction = Sort.Direction.DESC) Pageable pageable) {

        String userid= "admin";

        // 모델에 북마크 데이터 추가
        model.addAttribute("bookmarksig", bookmarkIgService.getIgBookmarks(userid,pageable));
        model.addAttribute("back", pageable.previousOrFirst().getPageNumber()); // 페이지 정보도 함께 전달
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("checknext", bookmarkIgService.getIgBookmarks(userid,pageable).hasNext());
        model.addAttribute("checkprev", bookmarkIgService.getIgBookmarks(userid,pageable).hasPrevious());
        System.out.println(model);
        // 뷰의 이름을 반환하여 해당 뷰로 이동
        return "bookmarksig"; // bookmarksig.mustache로 이동
    }



}