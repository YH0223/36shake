package com.example.NewWeb.modules;

import com.example.NewWeb.Cocktail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRepository extends CrudRepository<Cocktail, Integer> {

    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "drinkid , drinkname, Tags, Category, IBA, Alcoholic, " +
            "Glass, Instructions, drinkthb, " +
            "engig1, engig2, engig3, engig4, engig5, engig6, engig7, engig8, engig9, engig10, engig11, engig12, " +
            "engms1, engms2, engms3, engms4, engms5, engms6, engms7, engms8, engms9, engms10, engms11, engms12 " +
            "FROM cocktails WHERE drinkid = ?1 and (IBA<>'undefined'or Alcoholic='Non alcoholic')", nativeQuery = true)
    Cocktail findBydrinkid(int drinkid);
    Cocktail findBydrinkname(String drinkname);
    List<Cocktail> findByDrinknameContaining(String keyword);
    Page<Cocktail> findAll(Pageable pageable);


}