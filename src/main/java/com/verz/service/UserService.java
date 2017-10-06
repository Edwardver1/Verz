package com.verz.service;

import java.util.Set;

import com.verz.domain.User;
import com.verz.domain.security.UserRole;


public interface UserService {
	
	User findByUsername(String username);
	
    User findByEmail (String email);
	
	User findById(Long id);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);

}
