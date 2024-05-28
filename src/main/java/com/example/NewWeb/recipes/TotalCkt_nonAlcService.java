package com.example.NewWeb.recipes;

import com.example.NewWeb.TotalCkt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TotalCkt_nonAlcService {

    @Autowired
    private TotalCktRepository totalCktRepository;
    @Transactional(readOnly = true)
    public Page<TotalCkt> getCocktails(Pageable pageable,String alcoholic) {
        return totalCktRepository.findByAlcoholicEquals(pageable,alcoholic);
    }



}
