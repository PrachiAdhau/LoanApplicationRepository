package com.customer.ServiceI;





import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;


import com.customer.model.Customer;
import com.customer.model.CustomerVerification;

public interface CustomerServiceI {

	public Customer saveData(String jsondata, MultipartFile addressProof, MultipartFile pancard, MultipartFile incomeTax,
			MultipartFile addharCard, MultipartFile photo, MultipartFile signature, MultipartFile bankCheque,
			MultipartFile salarySlips);


	


	public void deleteSingle(int customerID);

	public Customer getSingleMethod(int customerID);


	public void deleteAllCustomer();





	public List<Customer> getAllData();





	public void editcustomer(Customer c, int id);





	public CustomerVerification customerVerificationDetails(int customerID, CustomerVerification cv);





	public Optional<Customer> findById(Integer customerId);





	



	







	



	



	

}
