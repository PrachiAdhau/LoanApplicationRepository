package com.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class LocalAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int localAddressId;
	private String areaname;
	private String cityname;
	private String  district;
	private String state;
	private int pincode;
	private int houseNumber;
	private String streetName;

}
