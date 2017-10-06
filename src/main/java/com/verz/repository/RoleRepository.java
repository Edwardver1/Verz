package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.security.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByname(String name);
}
