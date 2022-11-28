package com.Ezenweb.domain.entity.board;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {


     // 1. 제목 검색
    @Query( value = "select * from board where bcno = :bcno and btitle like %:keyword%" , nativeQuery = true )
     Page<BoardEntity> findbybtitle( int bcno , String keyword , Pageable pageable);
     // 2. 내용 검색
     @Query( value = "select * from board where bcno = :bcno and bcontent like %:keyword%" , nativeQuery = true )
     Page<BoardEntity> findbybcontent( int bcno , String keyword , Pageable pageable);
     // 3. 검색이 없을때
     @Query( value = "select * from board where bcno = :bcno " , nativeQuery = true)
     Page<BoardEntity> findBybcno(@Param("bcno") int bcno , Pageable pageable);
}



