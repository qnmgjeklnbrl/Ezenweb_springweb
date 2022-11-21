package com.Ezenweb.domain.dto;


import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VcategoryDto {



        private int vcno;

        private String vcname;


        public VcategoryEntity toEntity (){
            return VcategoryEntity.builder()
                    .vcno(this.vcno)
                    .vcname(this.vcname)
                    .build();
        }

}

