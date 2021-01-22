package org.vincent.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vincent.data.model.DemoEntity;

import java.math.BigDecimal;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity, Integer> {

    int deleteByProductNameAndPrice(String productName, BigDecimal price);

}
