package com.customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;


import org.springframework.web.bind.annotation.PathVariable;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.customer.ServiceI.CustomerServiceI;
import com.customer.model.Customer;




@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerServiceI csi;
	
	@PostMapping("/save")
	public ResponseEntity<Customer> addData(@RequestPart("info") String jsondata,@RequestPart("addressProof")MultipartFile addressProof,
			                                @RequestPart("pancard")MultipartFile pancard,@RequestPart("incomeTax") MultipartFile incomeTax,
			                                @RequestPart("addharCard")MultipartFile addharCard,
			                                @RequestPart("photo")MultipartFile photo,@RequestPart("signature")MultipartFile signature,
			                                @RequestPart("bankCheque")MultipartFile bankCheque,@RequestPart("salarySlips") MultipartFile salarySlips)
	{
		Customer cu=csi.saveData(jsondata,addressProof,pancard,incomeTax,addharCard,photo,signature,bankCheque,salarySlips);
		
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
		
		
		
		return  new ResponseEntity<Customer>(cu,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllData()
	{
	List<Customer>list	= csi.getAllData();
	return new  ResponseEntity<List<Customer>>(list,HttpStatus.OK);
	}
	
	

	@GetMapping("/getsingleData/{customerID}")
	public ResponseEntity<Customer> getSingleMethod(@PathVariable("customerID") int customerID) 
	{
		Customer c = csi.getSingleMethod(customerID);
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
    }

	

	@DeleteMapping("/delSingle/{customerID}")
	public ResponseEntity<String> deleteSingleData(@PathVariable int customerID) {

		csi.deleteSingle(customerID);
		return new ResponseEntity<String>("delete single data", HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delByAllCustomer")
	public ResponseEntity<String> delByAllCustomer(){
		csi.deleteAllCustomer();
		return new ResponseEntity<String>("delete Customer",HttpStatus.OK);

     }


}
