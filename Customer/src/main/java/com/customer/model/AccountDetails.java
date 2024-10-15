package com.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class AccountDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	private String accounType; 
	private Double accountBalance;
	private String accountHolderName;
	private String accountStatus;
	private Long accountNumber;

}
