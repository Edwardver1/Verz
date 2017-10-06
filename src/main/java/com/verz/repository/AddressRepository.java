package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
	
	

}
