package com.Ezenweb.domain.entity.bcategory;

import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BcategoryRepository extends JpaRepository<BcategoryEntity,Integer> {
}
