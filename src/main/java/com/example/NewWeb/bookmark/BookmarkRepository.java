package com.example.NewWeb.bookmark;

import com.example.NewWeb.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> {

    // idDrink 값을 받아 해당하는 칵테일의 재료와 측정치를 가져오는 쿼리
    @Query(value = "SELECT " +
            "bookmarkno AS id, drinkno AS drinkno, userid AS userid" + // 각 컬럼에 별칭을 추가합니다.
            "FROM bookmark", nativeQuery = true)

    Bookmark findBybookmarkno(int bookmarkno);
    Bookmark findBydrinkno(int drinkno); // findByigen 대신 findByIgen으로 수정
    Bookmark findByuserid(String userid);
    List<Bookmark> findAllByOrderByBookmarknoAsc();

    // 특정 사용자(userId)의 북마크를 bookmarkno 오름차순으로 모두 가져오기
    List<Bookmark> findByUseridOrderByBookmarknoAsc(String userid);

    Page<Bookmark> findByUseridOrderByBookmarknoAsc(String userid, Pageable pageable);
}



