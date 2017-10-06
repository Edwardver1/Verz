package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.Order;



public interface OrderRepository extends CrudRepository<Order, Long>{

}
