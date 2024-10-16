package com.customer.ServiceI;

import org.springframework.web.multipart.MultipartFile;

import com.customer.model.Customer;

public interface CustomerServiceI {

	public Customer saveData(String jsondata, MultipartFile addressProof, MultipartFile pancard, MultipartFile incomeTax,
			MultipartFile addharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips);


	public void deleteSingle(int customerID);

	public void deleteAllCustomer();

	

	

}
