package com.Ezenweb.domain.entity.board;

import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="board")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int bno;
    @Column( nullable = false )
    private String btitle;
    @Column( nullable = false ,columnDefinition = "text")
    private String bcontent;

    @Column
    @ColumnDefault("0")
    private int bview;
    @Column
    private String bfile;

    @ManyToOne
    @JoinColumn(name="mno")
    @ToString.Exclude // [양방향일때는 필수]
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name="bcno")
    @ToString.Exclude // [양방향일때는 필수]
    private BcategoryEntity bcategoryEntity;




    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bfilename(this.bfile)
                .bview(this.bview)
                .memail(this.memberEntity.getMemail())
                .build();

    }


}
   /* 자바--------------> DB자료형
    int                int
    double float       float
    String             VARCHAR




   columnDefinition = "SQL자료형"
    */