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
				
				
//				cu.getAllpersonalDocument().setAddressProof(addressProof.getBytes());
//				
//				cu.getAllpersonalDocument().setPanCard(pancard.getBytes());
//				cu.getAllpersonalDocument().setIncomeTax(incomeTax.getBytes());
//				cu.getAllpersonalDocument().setAddharCard(addharCard.getBytes());
//				cu.getAllpersonalDocument().setPhoto(photo.getBytes());
//				cu.getAllpersonalDocument().setSignature(signature.getBytes());
//				cu.getAllpersonalDocument().setBankCheque(bankCheque.getBytes());
//				cu.getAllpersonalDocument().setSalarySlips(salarySlips.getBytes());

			}

			catch (IOException e) {
				e.printStackTrace();
			}

	
//		  // dependent info
//		  
//		  cu.getFamilydependentInfo().getNoOfFamilyMember();
//		  cu.getFamilydependentInfo().getNoOfChild();
//		  cu.getFamilydependentInfo().getMaritalStatus();
//		  cu.getFamilydependentInfo().getDependentMember();
//		  cu.getFamilydependentInfo().getFamilyIncome();
//		 
//		  // permanent address
//		 
//		  cu.getCustomerAddress().getPermanentAddress().getAreaname();
//		 cu.getCustomerAddress().getPermanentAddress().getCityname();
//		  cu.getCustomerAddress().getPermanentAddress().getDistrict();
//		  cu.getCustomerAddress().getPermanentAddress().getState();
//		  cu.getCustomerAddress().getPermanentAddress().getPincode();
//		  cu.getCustomerAddress().getPermanentAddress().getHouseNumber();
//		  cu.getCustomerAddress().getPermanentAddress().getStreetName();
//		 
//		 // local address
//		 
//		  cu.getCustomerAddress().getLocalAddress().getAreaname();
//		  cu.getCustomerAddress().getLocalAddress().getCityname();
//		  cu.getCustomerAddress().getLocalAddress().getDistrict();
//		 cu.getCustomerAddress().getLocalAddress().getState();
//		  cu.getCustomerAddress().getLocalAddress().getPincode();
//		  cu.getCustomerAddress().getLocalAddress().getHouseNumber();
//		 cu.getCustomerAddress().getLocalAddress().getStreetName();
//		 
//		 // Account details
//		 
//		 cu.getAccountdetails().getAccounType();
//		 cu.getAccountdetails().getAccountBalance();
//		  cu.getAccountdetails().getAccountHolderName();
//		  cu.getAccountdetails().getAccountStatus();
//		  cu.getAccountdetails().getAccountNumber();
//		 
//		 // guarantor details
//		  
//		  cu.getGurantordetails().getGuarantorName();
//		  cu.getGurantordetails().getGuarantorDateOfBirth();
//		  cu.getGurantordetails().getGuarantorRelationshipwithCustomer();
//		  cu.getGurantordetails().getGuarantorMobileNumber();
//		  cu.getGurantordetails().getGuarantorAdharCardNo();
//		  cu.getGurantordetails().getGuarantorMortgageDetails();
//		  cu.getGurantordetails().getGuarantorJobDetails();
//		  cu.getGurantordetails().getGuarantorLoaclAddress();
//		 cu.getGurantordetails().getGuarantorPermanentAddress();
//		 
//		
		return cr.save(cu);
//return null;
		
	}

	@Override
	public java.util.List<Customer> getAllData() {
		
		return cr.findAll();
	}

	
}
