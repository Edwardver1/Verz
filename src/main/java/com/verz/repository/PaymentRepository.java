package com.verz.repository;

import org.springframework.data.repository.CrudRepository;

import com.verz.domain.Payment;


public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
