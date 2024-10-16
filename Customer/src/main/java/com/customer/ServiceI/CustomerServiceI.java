package com.customer.ServiceI;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.customer.model.Customer;

public interface CustomerServiceI {

	public Customer saveData(String jsondata, MultipartFile addressProof, MultipartFile pancard, MultipartFile incomeTax,
			MultipartFile addharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips);



	public void deleteSingle(int customerID);

	public Customer getSingleMethod(int customerID);


	public void deleteAllCustomer();



	public void editcustomer(Customer c, int customerID);



	public List<Customer> getAllData();



	



	

	

	

}
