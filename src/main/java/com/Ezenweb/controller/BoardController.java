package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.dto.VisitDto;
import com.Ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/board") // 공통 URL
public class BoardController {

    // 컨트롤 역할 : 요청 / 응답

    // ------------1.전역변수---------------//
    // 1. 서비스 메소드 호출 위한 객체 생성
    // 1. 개발자가 new 연산자 사용해서 JVM 힙 메모리 할당해서 객체 생성
    // private BoardService boardService = new BoardService();
    // 2. @Autowired 어노테이션 이용해서 Spring 컨테이너에 빈[메모리] 생성
    @Autowired
    private BoardService boardService= new BoardService();

    // ------------2.페이지[html] 요청 로드 [view]---------------//
    // 1. 게시물목록 페이지 열기
    @GetMapping("/list") // URL  : localhost:8080/board/list 요청시 해당 html 반환
    public Resource getlist(){ return new ClassPathResource("templates/board/list.html"); }
    // 2. 게시물쓰기 페이지 열기
    @GetMapping("/write")// URL  : localhost:8080/board/write 요청시 해당 html 반환
    public Resource getwrite(){ return new ClassPathResource("templates/board/write.html"); }
    // 3. 게시물조회 페이지 열기
    @GetMapping("/view")// URL  : localhost:8080/board/view 요청시 html 해당 html 반환
    public Resource getview(){ return new ClassPathResource("templates/board/view.html"); }
    // 4. 게시물수정 페이지 열기
    @GetMapping("/update")// URL  : localhost:8080/board/update 요청시 해당 html 반환
    public Resource getupdate(){ return new ClassPathResource("templates/board/update.html"); }
    @GetMapping("/visit")
    public Resource getvisit(){ return new ClassPathResource("templates/board/visit.html");}

    // ----------- 3.요청과응답 처리 [model] --------------//
    // 1. HTTP 요청 메소드 매핑 : @PostMapping @GetMapping @DeleteMapping @PutMapping
    // 2. HTTP 데이터 요청 메소드 매핑 : @RequestBody @RequestParam @PathVariable
    // 1. 게시물 쓰기 [ 첨부파일 ]
   // @PostMapping("/setboard") 첨부파일 없을때
   // public boolean setboard( @RequestBody BoardDto boardDto ){
       // return boardService.setboard( boardDto);
   // }
    @PostMapping("/setboard")
    public boolean setboard(  BoardDto boardDto ){
        
    
        return boardService.setboard( boardDto);
    }
    // 2. 게시물 목록 조회 [ 페이징,검색 ]
    @GetMapping("/boardlist")
    public List<BoardDto> boardlist(@RequestParam("bcno") int bcno ){
        return boardService.boardlist(bcno);
    }
    // 3. 게시물 개별 조회
    @GetMapping("/getboard")
    public BoardDto getboard( @RequestParam("bno") int bno ){
        
        return boardService.getboard( bno );
    }
    // 4. 게시물 삭제
    @DeleteMapping("/delboard")
    public boolean delboard( @RequestParam("bno") int bno ){
        return boardService.delboard( bno );
    }
    // 5. 게시물 수정 [ 첨부파일 ]
    @PutMapping("/upboard")
    public boolean upboard( BoardDto boardDto){
        return boardService.upboard( boardDto );
    }

    //6. 카테고리 등록
    @PostMapping("/setbcategory")
    public boolean setbcategory( @RequestBody BcategoryDto bcategoryDto ){



        return boardService.serbcategory(bcategoryDto);

    }
    //7.모든 카테고리 출력
    @GetMapping("/bcategorylist")
    public List<BcategoryDto>  bcategorylist(){
        return boardService.bcategorylist();
    }


    @GetMapping("/setvcategory")
    public boolean setvcategory( @RequestParam("vcname") String vcname){


      return boardService.setvcategory(vcname);

    }
    @GetMapping("/getvcategory")
    public List<VcategoryDto> getvcategory(){


        return boardService.getvcategory();
    }
    @PostMapping("/setvisit")
    public boolean setvisit(@RequestBody VisitDto visitDto){

        return boardService.setvisit(visitDto);
    }
    @GetMapping("/visitlist")
    public List<VisitDto> visitlist(@RequestParam("vcno") int vcno){
        return boardService.visitlist(vcno);
    }
    @GetMapping("/viewvisit")
    public VisitDto viewvisit(@RequestParam("vno") int vno){



        return boardService.viewvisit(vno);

    }
    @DeleteMapping("/vdelete")
    public boolean vdelete(@RequestParam("vno") int vno){

        return boardService.vdelete(vno);

    }
    @PutMapping("/vput")
    public boolean vput(@RequestBody VisitDto visitDto){

        return boardService.vput(visitDto);


    }
    @GetMapping("/filedownload")
    public void filedownload( @RequestParam("filename") String filename ){
      
        boardService.filedownload( filename );
    }

}







