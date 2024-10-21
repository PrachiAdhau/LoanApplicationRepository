package com.customer.ServiceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.customer.Repository.CustomerRepository;
import com.customer.ServiceI.CustomerServiceI;
import com.customer.exception.InvalidMobileNoException;
import com.customer.exception.InvalidPinCodeException;
import com.customer.exception.invalidEmailException;
import com.customer.model.AllPersonalDocuments;
import com.customer.model.Customer;
import com.customer.model.CustomerVerification;
import com.customer.model.LoanDisbursement;
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

			// Mobilbe no exception

			String mobileNo = String.valueOf(cu.getCustomerMobileNumber());
			if (mobileNo.length() == 10) {
				System.out.println("valid no" + mobileNo);

			} else {
				throw new InvalidMobileNoException("invalidMobileNoException  " + mobileNo);
			}

			// gmail Exception
			String email = cu.getCustomerEmail();
			if (cu.getCustomerEmail().endsWith("@gmail.com")) {

				System.out.println("gmail is correct");
			} else {
				throw new invalidEmailException("invalidEmailException " + email);
			}

			// pincodeException for permanent address

			String ppincode = String.valueOf(cu.getCustomerAddress().getPermanentAddress().getPincode());
			if (ppincode.length() == 6) {
				System.out.println("valid pincode" + ppincode);
			} else {
				throw new InvalidPinCodeException("InvalidPinCodeException");
			}

			// pincodeException for local address

			String lpincode = String.valueOf(cu.getCustomerAddress().getLocalAddress().getPincode());
			if (lpincode.length() == 6) {
				System.out.println("valid pincode" + lpincode);
			} else {
				throw new InvalidPinCodeException("InvalidPinCodeException");
			}

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
		Customer c = cr.findById(customerID).get();
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
			cu.setCustomerName(c.getCustomerName());
			cu.setCustomerDateOfBirth(c.getCustomerDateOfBirth());
			cu.setCustomerAge(c.getCustomerAge());
			cu.setRequiredTenure(c.getRequiredTenure());
			cu.setCustomerGender(c.getCustomerGender());
			cu.setCustomerEmail(c.getCustomerEmail());
			cu.setCustomerMobileNumber(c.getCustomerMobileNumber());
			cu.setCustomerAdditionalMobileNumber(c.getCustomerAdditionalMobileNumber());
			cu.setCustomerAmountPaidForHome(c.getCustomerAmountPaidForHome());
			cu.setCustomerTotalLoanRequired(c.getCustomerTotalLoanRequired());
			cu.setLoanStatus(c.getLoanStatus());
			cu.setAllpersonalDocument(c.getAllpersonalDocument());
			cu.setFamilydependentInfo(c.getFamilydependentInfo());
			cu.setCustomerAddress(c.getCustomerAddress());
			cu.setAccountdetails(c.getAccountdetails());
			cu.setGurantordetails(c.getGurantordetails());
			cu.setLoandisbursement(c.getLoandisbursement());
			cu.setLedger(c.getLedger());
			cu.setCustomersanctionletter(c.getCustomersanctionletter());

			cu.setCustomerverification(c.getCustomerverification());
			cr.save(cu);
		} else {
			System.out.println("Data is not present");
		}

	}

	@Override
	public CustomerVerification customerVerificationDetails(int id, CustomerVerification cv) {

		Customer cc = cr.findById(id).get();

		int cid = cc.getCustomerID();
		cv.setVerificationID(id);
		Date currentDate = new Date();
		cv.setVerificationDate(currentDate);
		cv.setStatus("all basic data submitted");
		cv.setRemarks("Good");
		cc.setCustomerverification(cv);
		cr.save(cc);
		return cv;

	}

	@Override
	public Optional<Customer> findById(Integer enquid) {
		Optional<Customer> findById = cr.findById(enquid);
		return findById;
	}

	
	
}
