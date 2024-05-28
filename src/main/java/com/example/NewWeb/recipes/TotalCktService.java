package com.example.NewWeb.recipes;

import com.example.NewWeb.TotalCkt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TotalCktService {

    @Autowired
    private TotalCktRepository totalCktRepository;
    @Transactional(readOnly = true)
    public Page<TotalCkt> getCocktails(Pageable pageable) {
        return totalCktRepository.findAll(pageable);
    }


    @Transactional
    public int getListCheck() {
        Page<TotalCkt> cktPage = totalCktRepository.findAll(PageRequest.of(0, 10)); // 첫 번째 페이지 요청

        return cktPage.hasNext() ? 1 : 0; // 다음 페이지가 있으면 1을 반환, 없으면 0을 반환
    }
}
