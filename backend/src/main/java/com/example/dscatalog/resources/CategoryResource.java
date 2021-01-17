package com.example.dscatalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.sevices.CategoryService;
import com.example.dscatalog.sevices.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired  //Notação para injetar automaticamente a dependencia da camada de serviço
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		
		List<CategoryDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
		
		CategoryDTO obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
		
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	
}