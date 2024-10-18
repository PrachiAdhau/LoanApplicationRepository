package com.customer.ServiceI;

import java.util.Optional;

import com.customer.model.Customer;
import com.customer.model.SanctionLetter;

public interface SanctionService {

	public Customer generateSanctionId(int customerID, SanctionLetter sanctionLetter);

	public Optional<Customer> findById(Integer cusid);

	public Customer changeStatus(Customer customerDetails);


}
