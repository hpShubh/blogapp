package com.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
