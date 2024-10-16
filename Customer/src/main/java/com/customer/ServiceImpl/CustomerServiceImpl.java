package com.customer.ServiceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.customer.Repository.CustomerRepository;
import com.customer.ServiceI.CustomerServiceI;
import com.customer.model.AllPersonalDocuments;
import com.customer.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

	@Autowired
	CustomerRepository cr;

	@Override
	public Customer saveData(String jsondata, MultipartFile addressProof, MultipartFile pancard,
			MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo, MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips) {
		
		
//		System.out.println(jsondata);
//		
//		System.out.println(addressProof);
//		System.out.println(pancard);
//		System.out.println(incomeTax);
//		System.out.println(addharCard);
//		System.out.println(photo);
//		System.out.println(signature);
//		System.out.println(bankCheque);
//		System.out.println(salarySlips);

		ObjectMapper obj = new ObjectMapper();

		Customer cu = null;

		try {
			cu = obj.readValue(jsondata, Customer.class);
			System.out.println(cu);
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		if (cu != null)
			try {
				System.out.println("In if of service");
				
				AllPersonalDocuments all = new AllPersonalDocuments();
				all.setAddressProof(addressProof.getBytes());
			    all.setIncomeTax(incomeTax.getBytes());
				all.setAddharCard(addharCard.getBytes());
				all.setPhoto(photo.getBytes());
				all.setSignature(signature.getBytes());
				all.setBankCheque(bankCheque.getBytes());
				all.setSalarySlips(salarySlips.getBytes());
				cu.setAllpersonalDocument(all);
				
							
			}

			catch (IOException e) {
				e.printStackTrace();
			}

	
		return cr.save(cu);

	}

	@Override
	public void deleteAllCustomer() {
		
		cr.deleteAll();
		
	}

	@Override
	public Customer getSingleMethod(int customerID) {
		Customer c=cr.findById(customerID).get();
		return c;
	}

}
