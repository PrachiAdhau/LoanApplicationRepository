package com.customer.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.Repository.CustomerRepository;
import com.customer.ServiceI.LegderServiceI;
import com.customer.model.Customer;
import com.customer.model.Ledger;

@Service
public class LedgerServiceImpl implements LegderServiceI {

	@Autowired
	CustomerRepository cr;

	@Override

	public Ledger generateLedgerDetails(int customerId, Ledger le) {

		List<Ledger> ls = new ArrayList<Ledger>();
		Optional<Customer> customerdetails = cr.findById(customerId);
		Customer customerdetails1 = customerdetails.get();
		if (customerdetails.isPresent()) {
			Ledger ll = new Ledger();

			Random random = new Random();
			int num = 1000 + random.nextInt(90000);

			Double amt = customerdetails1.getCustomersanctionletter().getLoanAmtSanctioned();
			System.out.println(amt);
			float rti = customerdetails1.getCustomersanctionletter().getRateOfInterest();

			int duration = customerdetails1.getCustomersanctionletter().getLoanTenureInMonth();

			Double payAmt = amt * (1 + rti * duration);

			Double memi = payAmt / duration;

			Double aptd;

			Double rmningamt;
			//le.setLedgerId(num);
			for (int i = 1; i <= duration; i++) { // Corrected loop condition
				aptd = memi * i;
				System.out.println("Amount Paid Till Month " + i + ": " + aptd);

				rmningamt = payAmt - aptd; // Corrected calculation of remaining amount
				System.out.println("Remaining Amount After Month " + i + ": " + rmningamt);

				le.setLedgerCreatedDate("10-11-24");
				 le.setTotalLoanAmount(amt);
				 le.setTenure(duration);
				 le.setPayableAmountwithInterest(payAmt);
				 le.setMonthlyEMI(memi);
				 le.setAmountPaidtillDate(aptd);
				 le.setRemainingAmount(rmningamt);
				le.setNextEmiDatestart("1");
				le.setNextEmiDateEnd("1");
				le.setDefaulterCount(0);
				le.setPreviousEmitStatus("PAID");
				le.setCurrentMonthEmiStatus("PAID");
				le.setLoanEndDate("30");
				le.setLoanStatus("PAID");

				ls.add(le);
				customerdetails1.setLedger(ls);
				Customer cc = cr.save(customerdetails1);
			}
			

			
		}

		return null;

	}
}
