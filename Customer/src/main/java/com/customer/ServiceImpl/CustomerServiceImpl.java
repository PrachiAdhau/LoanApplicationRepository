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

	public void deleteSingle(int customerID) {
		cr.deleteById(customerID);
		
	}
	public Customer getSingleMethod(int customerID) {
		Customer c=cr.findById(customerID).get();
		return c;

	}
	@Override
	public java.util.List<Customer> getAllData() {
		
		return cr.findAll();
	}
		
	@Override
	public void editcustomer(Customer c, int customerID) {
		Customer cu = cr.findById(customerID).get();

		if (null != cu) {
			cu.setAccountdetails(c.getAccountdetails());
			cu.setAllpersonalDocument(c.getAllpersonalDocument());
			cu.setCustomerAdditionalMobileNumber(c.getCustomerAdditionalMobileNumber());
			cu.setCustomerAddress(c.getCustomerAddress());
			cu.setCustomerAge(c.getCustomerAge());
			cu.setCustomerAmountPaidForHome(c.getCustomerAmountPaidForHome());
			cu.setCustomerDateOfBirth(c.getCustomerDateOfBirth());
			cu.setCustomerEmail(c.getCustomerEmail());
			cu.setCustomerGender(c.getCustomerGender());
			cu.setCustomerID(c.getCustomerID());
			cu.setCustomerMobileNumber(c.getCustomerMobileNumber());
			cu.setCustomerName(c.getCustomerName());
			cu.setCustomerTotalLoanRequired(c.getCustomerTotalLoanRequired());
			cu.setCustomerverification(c.getCustomerverification());
			cu.setFamilydependentInfo(c.getFamilydependentInfo());
			cu.setGurantordetails(c.getGurantordetails());
			cu.setLedger(c.getLedger());
			cu.setLoandisbursement(c.getLoandisbursement());
			cu.setLoanStatus(c.getLoanStatus());
			cu.setRequiredTenure(c.getRequiredTenure());
			cu.setSanctionletter(c.getSanctionletter());
			cr.save(cu);
		} else {
			System.out.println("Data is not present");
		}

		
	}

}

