package com.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
