package com.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class GuarantorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int guarantorId;
	private String guarantorName;
	private String guarantorDateOfBirth;
	private String guarantorRelationshipwithCustomer;
	private Long guarantorMobileNumber;
	private Long guarantorAdharCardNo;
	private String guarantorMortgageDetails;
	private String guarantorJobDetails;
	private String guarantorLoaclAddress;
	private String guarantorPermanentAddress;

}
