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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class BoardService {
    // ------------1.전역변수---------------//
    String path = "C:\\Users\\504\\Desktop\\Ezenweb\\src\\main\\resources\\static\\bupload\\";
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BcategoryRepository bcategoryRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private VcategoryRepository vcategoryRepository;
    @Autowired
    private VisitRepository visitRepository;
    // ------------ 2. 서비스 ------------- //
    //0. 첨부파일 다운로드
    public void filedownload( String filename ){
        String realfilename ="";  // uuid 제거  //
        String [] split = filename.split("_"); // 1. _ 기준으로 자르기
        for( int i = 1 ; i<split.length ; i++ ) { // 2. uuid 제외한 반복문 돌리기
            realfilename += split[i];               // 3. 뒷자리 문자열 추가
            if (split.length-1 != i ){      // 마지막 인덱스 아니면
                realfilename += "_";        // 문자열[1] _ 문자열[2] _ 문자열[3].확장자명
            }
        }
        String filepath = path+filename; // 1. 경로 찾기
        try {  // 2. 헤더 구성 [ HTTP 해서 지원하는 다운로드형식 메소드 [ response ]
            response.setHeader( // 응답
                    "Content-Disposition", // 다운로드 형식 [ 브라우저 마다 다름 ]
                    "attachment;filename=" + URLEncoder.encode(realfilename, "UTF-8")); // 다운로드에 표시될 파일명
            File file = new File(filepath); // 해당 경로의 파일 객체화
            // 3. 다운로드 스트림 [ ]
            BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file)  ); // 1. 입력 스트림 객체 선언
            byte[] bytes = new byte[ (int)file.length() ];  // 2. 파일의 길이만큼 배열 선언
            fin.read( bytes );      // * 스트림 읽기 [ 대상 : new FileInputStream(file) ] // 3. 파일의 길이만큼 읽어와서 바이트를 배열에 저장
            BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() ); // 4. 출력 스트림 객체 선언
            fout.write( bytes );    // * 스트림 내보내기   [ response.getOutputStream() ]  // 5. 응답하기 [ 배열 내보내기]
            fout.flush(); fout.close(); fin.close();  // 6. 버퍼 초기화 혹은 스트림 닫기

        }catch(Exception e){ System.out.println(e);  }


    }


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
            if(boardDto.getBfile() != null){
                //첨부파일 등록
                String filename =boardDto.getBfile().getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                filename+=uuid;
                boardEntity.setBfile(filename);
                //업로드

                try {
                    boardDto.getBfile().transferTo( new File(path+filename));
                } catch (IOException e) {
                    throw new RuntimeException(e+"첨부파일 업로드 실패");
                }


            }


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
        List<VisitEntity> elist= vcategoryEntity.getVisitEntityList();


        List<VisitDto> dlist = new ArrayList<>();
        for(VisitEntity entity : elist){
            dlist.add(entity.toDto());
        }
        return dlist;
   }

   @Transactional
   public VisitDto viewvisit(int vno){

        VisitEntity visitEntity = visitRepository.findById(vno).get();
        return visitEntity.toDto();

   }
   @Transactional
    public boolean vdelete(int vno){
        Optional <VisitEntity> optional = visitRepository.findById(vno);
        if( optional.isPresent() ){
            visitRepository.delete(optional.get());
            return true;
        }else{return false;}


    }
    @Transactional
    public boolean vput(VisitDto visitDto){
        Optional <VisitEntity> optional = visitRepository.findById(visitDto.getVno());
        if( optional.isPresent() ){
            VisitEntity entity = optional.get();
            entity.setVtitle(visitDto.getVtitle());
            entity.setVcontent(visitDto.getVcontent());

            return true;
        }else{ return false; }




    }

}

