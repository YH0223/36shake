package com.example.NewWeb.recipes;

import com.example.NewWeb.TotalCkt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TotalCktController {

    @Autowired
    private TotalCktService totalCktService;

    @GetMapping(value = "/recipes")
    public String getBookmarks(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TotalCkt> cktPage = totalCktService.getCocktails(pageable);

        model.addAttribute("cocktails", cktPage);
        model.addAttribute("back", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("checknext", cktPage.hasNext());
        model.addAttribute("checkprev", cktPage.hasPrevious());

        return "recipes";
    }
}

