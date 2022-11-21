package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.visit.VisitEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VisitDto {




    private int vno;

    private String vtitle;

    private String vwriter;

    private String vcontent;
    private int vcno;

    public VisitEntity toEntity (){
        return VisitEntity.builder()
                .vno(this.vno)
                .vtitle(this.vtitle)
                .vwriter(this.vwriter)
                .vcontent(this.vcontent)
                .build();
    }



}
