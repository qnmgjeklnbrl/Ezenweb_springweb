package com.Ezenweb.domain.entity.visit;


import com.Ezenweb.domain.dto.VisitDto;
import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "visit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vno;
    @Column(nullable = false)
    private String vtitle;
    @Column(nullable = false)
    private String vwriter;
    @Column(nullable = false)
    private String vcontent;
    @ManyToOne
    @JoinColumn(name="vcno")
    @ToString.Exclude
    private VcategoryEntity VcategoryEntity;





    public VisitDto toDto(){
        return VisitDto.builder()
                .vno(this.vno)
                .vtitle(this.vtitle)
                .vwriter(this.vwriter)
                .vcontent(this.vcontent)
                .build();
    }










}
