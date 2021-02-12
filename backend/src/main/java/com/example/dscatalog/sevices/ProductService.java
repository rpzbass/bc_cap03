package com.example.dscatalog.sevices;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.dto.ProductDTO;
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.entities.Product;
import com.example.dscatalog.repositories.CategoryRepository;
import com.example.dscatalog.repositories.ProductRepository;
import com.example.dscatalog.sevices.exceptions.DatabaseException;
import com.example.dscatalog.sevices.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {

		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));

	}

	@Transactional
	public ProductDTO findById(Long id) throws ResourceNotFoundException {

		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new ProductDTO(entity,entity.getCategories());

	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {

		Product entity = new Product();	
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);

	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {

			Product entity = repository.getOne(id);
			copyDtoToEntity(dto,entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);

		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Id not found" + id);

		}

	}

	public void delete(Long id) {

		try {

			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFoundException("ID not found" + id);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException("Integrity violation" + id);

		}

	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {

		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImg_url());
		entity.setPrice(dto.getPrice());

		entity.getCategories().clear();		
		
		for(CategoryDTO catDTO : dto.getCategories()) {
			
			Category category = categoryRepository.getOne(catDTO.getId());
			entity.getCategories().add(category);
			
		}
		
	}

}	

