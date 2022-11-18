package com.Ezenweb.domain.entity.bcategory;

import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bcategory")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class BcategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bcno;
    @Column
    private String bcname;

    @OneToMany(mappedBy = "bcategoryEntity") //1:n 일때 pk에 해당 어노테이션
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();

}
