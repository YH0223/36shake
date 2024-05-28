package com.example.NewWeb.recommendations;

import com.example.NewWeb.CktRecommendation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CktRecommendationRepository extends CrudRepository<CktRecommendation, Integer> {
    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "id as id, drinkid , drinkname, Alcoholic as alcoholic engig1, engig2, engig3, engig4, engig5, engig6, engig7, engig8, engig9, engig10, engig11, engig12" +
            "FROM cocktails WHERE IBA<>'undefined' and alcoholic='Alcoholic')", nativeQuery = true)

    CktRecommendation findBydrinkid(int drinkid);
    CktRecommendation findBydrinkname(String drinkname);

    CktRecommendation findByid(int id);
    List<CktRecommendation> findAll();
    Page<CktRecommendation> findAll(Pageable pageable);
    List<CktRecommendation> findByAlcoholic(String Alcoholic);




}
