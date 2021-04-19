package com.example.dscatalog.sevices;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dscatalog.dto.RoleDTO;
import com.example.dscatalog.dto.UserDTO;
import com.example.dscatalog.dto.UserInsertDTO;
import com.example.dscatalog.dto.UserUpdateDTO;
import com.example.dscatalog.entities.Role;
import com.example.dscatalog.entities.User;
import com.example.dscatalog.repositories.RoleRepository;
import com.example.dscatalog.repositories.UserRepository;
import com.example.dscatalog.sevices.exceptions.DatabaseException;
import com.example.dscatalog.sevices.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository repositoryRole;
	
	
	@Transactional
	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {

		Page<User> list = repository.findAll(pageRequest);
		return list.map(x -> new UserDTO(x));

	}

	@Transactional
	public UserDTO findById(Long id) throws ResourceNotFoundException {

		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new UserDTO(entity);

	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {

		User entity = new User();	
		copyDtoToEntity(dto,entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);

	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO dto) {
		try {

			User entity = repository.getOne(id);
			copyDtoToEntity(dto,entity);
			entity = repository.save(entity);
			return new UserDTO(entity);

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

	private void copyDtoToEntity(UserDTO dto, User entity) {

		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());


		entity.getRoles().clear();		
		
		for(RoleDTO roleDTO : dto.getRoles()) {
			
			Role role = repositoryRole.getOne(roleDTO.getId());
			entity.getRoles().add(role);
			
		}
		
	}

}	

