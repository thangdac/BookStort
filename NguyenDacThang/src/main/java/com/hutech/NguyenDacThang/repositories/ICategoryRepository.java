package com.hutech.NguyenDacThang.repositories;

import com.hutech.NguyenDacThang.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends
        JpaRepository<Category, Long> {
}
