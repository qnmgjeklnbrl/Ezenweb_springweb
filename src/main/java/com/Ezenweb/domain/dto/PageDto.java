package com.Ezenweb.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class PageDto {
    private int bcno;
    private int page;
    private String key;
    private String keyword;

    @Builder.Default
    private List<BoardDto> list = new ArrayList<BoardDto>();
    private int startbtn;
    private int endbtn;
    private long totalBoards;


    //결과[게시물] 리스트 
    //페이징 버튼 시작번호 , 끝 번호
    //게시물 전체 개수 등등
}
