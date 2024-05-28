package com.example.NewWeb.Search;

import com.example.NewWeb.SearchResultIg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SearchResultIgRepository extends CrudRepository<SearchResultIg, Integer> {

    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "id AS id, igen AS igen, igkor AS igkor, igyes AS igyes " + // 각 컬럼에 별칭을 추가합니다.
            "FROM ingredients", nativeQuery = true)

    SearchResultIg findByid(int igid);
    SearchResultIg findByigen(String igen); // findByigen 대신 findByIgen으로 수정
    SearchResultIg findByigkor(String igkor);
    SearchResultIg findByigyes(int igyes);

    SearchResultIg findFirstByid(int igid);
    Page<SearchResultIg> findAll(Pageable pageable);
    Page<SearchResultIg> findByigkor(Pageable pageable,String keyword);

}