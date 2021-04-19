package com.example.dscatalog.sevices.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.dscatalog.dto.UserUpdateDTO;
import com.example.dscatalog.entities.User;
import com.example.dscatalog.repositories.UserRepository;
import com.example.dscatalog.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public void initialize(UserUpdateValid ann) {

	}

	@Override
	public boolean isValid(UserUpdateDTO value, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		User user = userRepository.findByEmail(value.getEmail());
		if(user != null && userId != user.getId()) {
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
