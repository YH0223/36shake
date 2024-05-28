package com.example.NewWeb.Search;

import com.example.NewWeb.SearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SearchResultRepository extends CrudRepository<SearchResult, Integer> {

    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "id as id, drinkid as drinkId , drinkname as drinkname, IBA as IBA, Alcoholic as Alcoholic " +
            "FROM cocktails WHERE IBA<>'undefined'or Alcoholic='Non Alcoholic' order by drinkId", nativeQuery = true)

    SearchResult findFirstByDrinkId(int drinkid);
    SearchResult findBydrinkname(String drinkname);

    Page<SearchResult> findAll(Pageable pageable);
    Page<SearchResult> findBydrinknameContaining(Pageable pageable,String keyword);



}
