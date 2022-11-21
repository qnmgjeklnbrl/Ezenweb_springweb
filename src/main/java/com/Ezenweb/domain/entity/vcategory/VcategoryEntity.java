package com.Ezenweb.domain.entity.vcategory;

import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.visit.VisitEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vcategory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VcategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int vcno;
    @Column(nullable = false)
    private String vcname;
    @OneToMany(mappedBy = "VcategoryEntity")
    @Builder.Default
    private List<VisitEntity> visitEntityList = new ArrayList<>();

    public VcategoryDto  toDto(){
        return VcategoryDto.builder()
                .vcno(this.vcno)
                .vcname(this.vcname)
                .build();


    }

}
