package com.Ezenweb.service;


import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.dto.VisitDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import com.Ezenweb.domain.entity.vcategory.VcategoryRepository;
import com.Ezenweb.domain.entity.visit.VisitEntity;
import com.Ezenweb.domain.entity.visit.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class BoardService {
    // ------------1.전역변수---------------//
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BcategoryRepository bcategoryRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private VcategoryRepository vcategoryRepository;
    @Autowired
    private VisitRepository visitRepository;
    // ------------ 2. 서비스 ------------- //
    //1.게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto ){
        MemberEntity memberEntity = memberService.getEntity();
        if(memberEntity == null){return false;}
        Optional<BcategoryEntity> optional = bcategoryRepository.findById(boardDto.getBcno());
        if(!optional.isPresent()){return false;}
        BcategoryEntity bcategoryEntity = optional.get();


       BoardEntity boardEntity =  boardRepository.save( boardDto.toEntity() );
       if(boardEntity.getBno() != 0){
           boardEntity.setMemberEntity(memberEntity);
           memberEntity.getBoardEntityList().add(boardEntity);

           boardEntity.setBcategoryEntity(bcategoryEntity);
           bcategoryEntity.getBoardEntityList().add(boardEntity);
           return  true;
       }else{ return  false;}

    }
    //2.게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(int bcno){
        List<BoardEntity> elist= null;
        if(bcno == 0){
            elist = boardRepository.findAll();
        }
        else{
             BcategoryEntity bcategoryEntity  = bcategoryRepository.findById(bcno).get();
             elist = bcategoryEntity.getBoardEntityList();
        }

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

    @Transactional
    public boolean serbcategory(BcategoryDto bcategoryDto ){
        BcategoryEntity entity = bcategoryRepository.save(bcategoryDto.toEntity());
        if(entity.getBcno() != 0){return true;}
        else {return false;}
    }
    @Transactional
    public List<BcategoryDto> bcategorylist(){
        List<BcategoryEntity> entityList = bcategoryRepository.findAll();
        List<BcategoryDto> dtolist = new ArrayList<>();
        entityList.forEach( e-> dtolist.add( e.toDto() ) );
        return dtolist;
    }
    @Transactional
    public boolean setvcategory(String vcname ){
        VcategoryEntity vcategoryEntity =  VcategoryEntity.builder().vcname(vcname).build();
        vcategoryRepository.save(vcategoryEntity);

        if(vcategoryEntity.getVcno() != 0){return true;}
        else {return false;}
    }
    @Transactional
   public List<VcategoryDto> getvcategory(){
       List<VcategoryEntity> entityList = vcategoryRepository.findAll();
       List<VcategoryDto> dtolist = new ArrayList<>();
       entityList.forEach( e-> dtolist.add( e.toDto() ) );
       return dtolist;


   }

    @Transactional
   public boolean setvisit(VisitDto visitDto){
       Optional<VcategoryEntity> optional = vcategoryRepository.findById(visitDto.getVcno());
       if(!optional.isPresent()){return false;}
       VcategoryEntity vcategoryEntity = optional.get();
       VisitEntity visitEntity =visitRepository.save(  visitDto.toEntity());

       if(visitEntity.getVno() != 0){


           visitEntity.setVcategoryEntity(vcategoryEntity);
           vcategoryEntity.getVisitEntityList().add(visitEntity);
           return  true;
       }else{ return  false;}

   }
    @Transactional
   public List<VisitDto> visitlist(int vcno ){



        VcategoryEntity vcategoryEntity  = vcategoryRepository.findById(vcno).get();
        List<VisitEntity> elist = vcategoryEntity.getVisitEntityList();


        List<VisitDto> dlist = new ArrayList<>();
        for(VisitEntity entity : elist){
            dlist.add(entity.toDto());
        }
        return dlist;
   }

}

/*

    List<BoardEntity> elist= null;
        if(bcno == 0){
                elist = boardRepository.findAll();
                }
                else{
                BcategoryEntity bcategoryEntity  = bcategoryRepository.findById(bcno).get();
                elist = bcategoryEntity.getBoardEntityList();
                }

                List<BoardDto> dlist = new ArrayList<>();
        for(BoardEntity entity : elist){
        dlist.add(entity.toDto());
        }
        return dlist;



*/
