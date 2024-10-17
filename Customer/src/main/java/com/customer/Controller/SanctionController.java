package com.customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.ServiceI.CustomerServiceI;
import com.customer.ServiceI.SanctionService;
import com.customer.model.Customer;
import com.customer.model.SanctionLetter;
@RestController
public class SanctionController {
	@Autowired
	CustomerServiceI csi;

	@Autowired
	SanctionService ss;

	@Value("${spring.mail.username}")
	private String fromEmail;


	@PutMapping("/generatedPdf/{customerID}")
	public Customer updateSactionLetter(@PathVariable int customerID,@RequestBody SanctionLetter sanctionLetter)
	{
	  	return ss.generateSanctionId(customerID, sanctionLetter);
		}


}
