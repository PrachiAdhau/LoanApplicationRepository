package com.customer.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class CustomerVerification {
	@Id
	
	private int verificationID;
    private Date verificationDate;
    private String status;
    private String remarks;

}
