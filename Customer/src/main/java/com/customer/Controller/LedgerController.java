package com.customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.ServiceI.LegderServiceI;
import com.customer.model.Ledger;

@RestController
public class LedgerController {

	
	@Autowired
	LegderServiceI lg;
	
	
	
	@PutMapping("/genLedger/{customerId}")
	public ResponseEntity<Ledger> generateLedger(@PathVariable int customerId,@RequestBody Ledger le)
	{
	   Ledger lgdr= lg.generateLedgerDetails(customerId,le);
	return new ResponseEntity<Ledger>(lgdr,HttpStatus.OK);
	   
		
	}
}
