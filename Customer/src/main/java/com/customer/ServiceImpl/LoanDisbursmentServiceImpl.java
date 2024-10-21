package com.customer.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.Repository.CustomerRepository;
import com.customer.ServiceI.LoandisbursmentServiceI;
import com.customer.model.Customer;
import com.customer.model.LoanDisbursement;

@Service
public  class LoanDisbursmentServiceImpl implements LoandisbursmentServiceI {
	@Autowired
	CustomerRepository cr;

	@Override
	public LoanDisbursement getcustomerLoanDisbursement(int id, LoanDisbursement dd) {
		Optional<Customer> customerdetails = cr.findById(id);
		Customer customerdetails1 = customerdetails.get();
		LoanDisbursement lnd=new LoanDisbursement();
		if(customerdetails.isPresent())
		   {
			
			lnd.setLoanNo(dd.getLoanNo());
			lnd.setAgreementDate(dd.getAgreementDate());
			lnd.setAmountPayType(dd.getAmountPayType());
			lnd.setTotalAmount(dd.getTotalAmount());
			lnd.setBankName(dd.getBankName());
			lnd.setAccountNumber(dd.getAccountNumber());
			lnd.setIfsccode(dd.getIfsccode());
			lnd.setAccountType(dd.getAccountType());
			lnd.setTransferAmount(dd.getTransferAmount());
			lnd.setPaymentStatus(dd.getPaymentStatus());
			lnd.setAmountPaidDate(dd.getAmountPaidDate());
			customerdetails1.setLoandisbursement(lnd);
			Customer s= cr.save(customerdetails1);
			return lnd;
			
		   }
		else
		{
		return null;
	}
	}
	
	
}
