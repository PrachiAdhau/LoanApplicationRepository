package com.customer.ServiceImpl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.customer.Repository.CustomerRepository;
import com.customer.ServiceI.SanctionService;
import com.customer.model.Customer;
import com.customer.model.SanctionLetter;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;




@Service
public class SanctionServiceImpl implements SanctionService{
	
	@Autowired
	CustomerRepository cr;
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public Customer generateSanctionId(int customerID , SanctionLetter sanctionLetter)
	{
	   Optional<Customer> customerdetails=cr.findById(customerID);
	   Customer customerdetails1=customerdetails.get();
	   if(customerdetails.isPresent())
	   {
	    SanctionLetter s1 = new SanctionLetter();
	    s1.setSanctionId(sanctionLetter.getSanctionId());
	    s1.setSanctionDate(sanctionLetter.getSanctionDate());
	    s1.setApplicantName(sanctionLetter.getApplicantName());
	    s1.setContactDetails(sanctionLetter.getContactDetails());
	    s1.setProducthomeEquity(sanctionLetter.getProducthomeEquity());
	    s1.setLoanAmtSanctioned(sanctionLetter.getLoanAmtSanctioned());
	    s1.setInterestType(sanctionLetter.getInterestType());
	    s1.setRateOfInterest(sanctionLetter.getRateOfInterest());
	    s1.setLoanTenureInMonth(sanctionLetter.getLoanTenureInMonth());
	    s1.setMonthlyEmiAmount(sanctionLetter.getMonthlyEmiAmount());
	    s1.setModeOfPayment(sanctionLetter.getModeOfPayment());
	    s1.setRemarks(sanctionLetter.getRemarks());
	    s1.setTermsCondition(sanctionLetter.getTermsCondition());
	    s1.setStatus(sanctionLetter.getStatus());

	    customerdetails1.setCustomersanctionletter(s1);
	    
	    String title = "ABC Sanction Letter.";
	     
	    Document document = new Document(PageSize.A4);

		String content1 = "\n\n Dear " + customerdetails1.getCustomerName()
				+ ","
				+ "\nABC Finance Ltd. is Happy to informed you that your loan application has been approved. ";

		String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory "
				+ "and that it will help you meet your financial needs.\n\nIf you have any questions or need any assistance regarding your loan, "
				+ "please do not hesitate to contact us.\n\nWe wish you all the best and thank you for choosing us."
				+ "\n\nSincerely,\n\n" + "Vijay Chaudhari (Credit Manager)";

		ByteArrayOutputStream opt = new ByteArrayOutputStream();
		
		PdfWriter.getInstance(document, opt);
		document.open();

		Image img = null;
		
		try {
			img = Image.getInstance("C:\\Users\\lenovo\\Desktop\\HomeLoan\\home.jpg");
			img.scalePercent(50, 50);
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);

		} catch (BadElementException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
		Paragraph titlepara = new Paragraph(title, titlefont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);

		Font titlefont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Paragraph paracontent1 = new Paragraph(content1, titlefont2);
		document.add(paracontent1);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 2 });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.WHITE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(5, 5, 161);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(5, 5, 161);

		cell.setPhrase(new Phrase("Loan amount Sanctioned"));
		table.addCell(cell);

		cell.setPhrase(new Phrase(String.valueOf("₹ " + customerdetails1.getCustomersanctionletter().getLoanAmtSanctioned()),
				font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("loan tenure",font));
		table.addCell(cell);

		cell.setPhrase(new Phrase(String.valueOf(customerdetails1.getCustomersanctionletter().getLoanTenureInMonth()), font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("interest rate", font));
		table.addCell(cell);

		cell.setPhrase(
				new Phrase(String.valueOf(customerdetails1.getCustomersanctionletter().getRateOfInterest()) + " %", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Sanction letter generated Date", font));
		table.addCell(cell);

		Date date = new Date();
		String curdate = date.toString();
		customerdetails1.getCustomersanctionletter().setSanctionDate(date);
		cell.setPhrase(
				new Phrase(String.valueOf(customerdetails1.getCustomersanctionletter().getSanctionDate()), font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Total loan Amount with Intrest", font));
		table.addCell(cell);

		document.add(table);

		Font titlefont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Paragraph paracontent2 = new Paragraph(content2, titlefont3);
		document.add(paracontent2);
		document.close();
		
		ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
		byte[] bytes = byt.readAllBytes();
		customerdetails1.getCustomersanctionletter().setSanctionLetter(bytes);

		return cr.save(customerdetails1);
	}
	else 
	{
		return null;
	}	
}

    @Override
    public Optional<Customer> findById(Integer cusid)
    {
	    Optional<Customer> findById = cr.findById(cusid);
	    return findById;
    }

    @Override
    public Customer changeStatus(Customer customerDetails)
    {
	    return cr.save(customerDetails);
    }

}
