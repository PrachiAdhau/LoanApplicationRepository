package com.customer.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerID;
	private String customerName;
	private String customerDateOfBirth;
	private int customerAge;
	private int requiredTenure;
	private String customerGender;
	private String customerEmail;
	private Double customerMobileNumber;
	private Double customerAdditionalMobileNumber;
	private Double customerAmountPaidForHome;
	private Double customerTotalLoanRequired;
	private String loanStatus;
	@OneToOne(cascade = CascadeType.ALL)
	private AllPersonalDocuments allpersonalDocument;
	@OneToOne(cascade = CascadeType.ALL)
	private DependentInforamtion familydependentInfo;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerAddress customerAddress;
	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails accountdetails;
	@OneToOne(cascade = CascadeType.ALL)
	private GuarantorDetails  gurantordetails;
	@OneToOne(cascade = CascadeType.ALL)
	private LoanDisbursement loandisbursement;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ledger> ledger;
	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionletter;
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerVerification customerverification;
	

}
