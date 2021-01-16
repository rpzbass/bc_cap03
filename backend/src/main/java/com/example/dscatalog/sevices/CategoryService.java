package com.example.dscatalog.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository respository;

	public List<Category> findAll() {

			return respository.findAll();
		
	}

}
