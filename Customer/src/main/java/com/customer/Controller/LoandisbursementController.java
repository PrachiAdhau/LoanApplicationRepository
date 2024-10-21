package com.customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.ServiceI.LoandisbursmentServiceI;
import com.customer.model.LoanDisbursement;

@RestController
public class LoandisbursementController {
	
	@Autowired
	LoandisbursmentServiceI lds;
	
	
	@PutMapping("/getLoandis/{id}")
	public ResponseEntity<LoanDisbursement> customerLoanDisbursement(@PathVariable int id,@RequestBody LoanDisbursement dd)
	{
		LoanDisbursement loand=lds.getcustomerLoanDisbursement(id,dd);
		return new ResponseEntity<LoanDisbursement>(loand,HttpStatus.OK);
		
	}
	

}
