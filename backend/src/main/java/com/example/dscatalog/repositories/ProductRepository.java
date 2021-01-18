package com.example.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dscatalog.entities.Category;

@Repository
public interface ProductRepository extends JpaRepository<Category,Long> {

	
	
}
