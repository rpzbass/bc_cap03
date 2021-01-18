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
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import com.example.dscatalog.sevices.exceptions.DatabaseException;
import com.example.dscatalog.sevices.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {

		Page<Category> list = repository.findAll(pageRequest);
		return list.map(x -> new CategoryDTO(x));

	}

	@Transactional
	public CategoryDTO findById(Long id) throws ResourceNotFoundException {

		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new CategoryDTO(entity);

	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {

		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);

	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {

			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);

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

}
