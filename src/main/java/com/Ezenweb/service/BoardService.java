package com.Ezenweb.service;


import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    // ------------1.전역변수---------------//
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberRepository memberRepository;
    // ------------ 2. 서비스 ------------- //
    //1.게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto ){
        Object object = request.getSession().getAttribute("loginMno");
        if( object == null ){return false;}
        int mno = (Integer)object;
       Optional<MemberEntity> optional = memberRepository.findById(mno);
       if( !optional.isPresent()){return false;}
       MemberEntity memberEntity = optional.get();
       BoardEntity boardEntity =  boardRepository.save( boardDto.toEntity() );
       if(boardEntity.getBno() != 0){
           boardEntity.setMemberEntity(memberEntity);
           memberEntity.getBoardEntityList().add(boardEntity);
            return  true;
       }else{ return  false;}

    }
    //2.게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        List<BoardEntity> elist = boardRepository.findAll();
        List<BoardDto> dlist = new ArrayList<>();
        for(BoardEntity entity : elist){
            dlist.add(entity.toDto());
        }
        return dlist;
    }
    @Transactional
    public BoardDto getboard( int bno ){
         Optional <BoardEntity> optional = boardRepository.findById(bno);
         if( optional.isPresent() ){
             optional.get().setBview(optional.get().getBview()+1);
             return optional.get().toDto();
         }else{return null;}
    }
    @Transactional
    public boolean delboard( int bno ){
        Optional <BoardEntity> optional = boardRepository.findById(bno);
        if( optional.isPresent() ){
            boardRepository.delete(optional.get());
            return true;
        }else{return false;}
    }
    @Transactional
    public boolean upboard( BoardDto boardDto){
        Optional <BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        if( optional.isPresent() ){
            BoardEntity entity = optional.get();
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            entity.setBfile(boardDto.getBfile());
            return true;
        }else{ return false; }
    }

}






