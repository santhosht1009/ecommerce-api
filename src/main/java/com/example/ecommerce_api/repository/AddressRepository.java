package com.example.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce_api.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
