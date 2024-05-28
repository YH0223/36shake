package com.example.NewWeb.recipes;

import com.example.NewWeb.TotalCkt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalCktRepository extends CrudRepository<TotalCkt, Integer> {

    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "id as id, drinkid as drinkId , drinkname as drinkname, IBA as IBA, Alcoholic as Alcoholic " +
            "FROM cocktails WHERE IBA<>'undefined'or Alcoholic='Non Alcoholic' order by drinkId", nativeQuery = true)

    TotalCkt findFirstByDrinkId(int drinkid);
    TotalCkt findBydrinkname(String drinkname);

    List<TotalCkt> findBydrinknameContaining(String keyword);
    Page<TotalCkt> findAll(Pageable pageable);
    Page<TotalCkt> findByAlcoholicEquals(Pageable pageable,String alcoholic);


}