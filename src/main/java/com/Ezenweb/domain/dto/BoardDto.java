package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {

    private int bno;

    private String btitle;

    private String bcontent;

    private int mno;

    private int bcno;

    private int bview;

    private MultipartFile bfile;
    private String memail;
    public BoardEntity toEntity(){
        return BoardEntity.builder()
               .bno(this.bno)
               .btitle(this.btitle)
               .bcontent(this.bcontent)
               .bview(this.bview)
               .build();


    }


}
