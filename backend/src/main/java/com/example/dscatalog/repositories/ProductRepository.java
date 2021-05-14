package com.example.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dscatalog.entities.Category;
import com.example.dscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories categ WHERE "
			+ "(:category IS NULL  OR  :category IN categ )  AND "
			+ "(LOWER(obj.name)  LIKE  LOWER(CONCAT('%',:name,'%')) )")
	
	Page<Product> find(Category category,String name,Pageable pageable);
	
}
