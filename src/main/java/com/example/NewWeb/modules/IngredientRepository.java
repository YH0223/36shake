package com.example.NewWeb.modules;

import com.example.NewWeb.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "id AS id, igen AS igen, igkor AS igkor, igyes AS igyes " + // 각 컬럼에 별칭을 추가합니다.
            "FROM ingredients", nativeQuery = true)

    Ingredient findByid(int igid);
    Ingredient findByigen(String igen); // findByigen 대신 findByIgen으로 수정
    Ingredient findByigkor(String igkor);
    Ingredient findByigyes(int igyes);

    Ingredient findFirstByid(int igid);
    Page<Ingredient> findAll(Pageable pageable);

}