package com.example.dscatalog.sevices.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dscatalog.dto.UserInsertDTO;
import com.example.dscatalog.entities.User;
import com.example.dscatalog.repositories.UserRepository;
import com.example.dscatalog.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	@Autowired
	private UserRepository userRepository;
	@Override
	public void initialize(UserInsertValid ann) {

	}

	@Override
	public boolean isValid(UserInsertDTO value, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		User user = userRepository.findByEmail(value.getEmail());
		if(user != null ) {
			list.add(new FieldMessage("email","Email já Existe"));
		}
		
		
		for (FieldMessage f : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();

	}

}
