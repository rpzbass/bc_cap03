package com.example.dscatalog.sevices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import com.example.dscatalog.sevices.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository respository;

	@Transactional
	public List<CategoryDTO> findAll() {

			List<Category> list =  respository.findAll();
			
		    return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	
		    
	}

	@Transactional
	public CategoryDTO findById(Long id) throws EntityNotFoundException {

			Optional<Category> obj = respository.findById(id);
			
			Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not Found"));
			
			return  new CategoryDTO(entity);
		    
	}
	
	
	
	
	
	
}
