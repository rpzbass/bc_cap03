package com.example.dscatalog.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.example.dscatalog.entities.User;
import com.example.dscatalog.repositories.UserRepository;



@Component
public class jwtTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	UserRepository repository;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = repository.findByEmail(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
		map.put("userFirstName", user.getFirstName());
		map.put("userId", user.getId());
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		
		token.setAdditionalInformation(map);
		
		return accessToken;
	}
		
}
