package com.customer.ServiceI;

import com.customer.model.Customer;
import com.customer.model.SanctionLetter;

public interface EmailService {

	SanctionLetter sendSantionLetterMail(Customer customerDetails);

}
