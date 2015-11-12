package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Doctor {

	
	private long DocLicence;
	private Gender DocGender;
	private String DocFirstName;
	private String DocLastName;
	private String DocAdress;
	private String DocTelephoneNumber;
	private Expertise DocExpertise; 
	private Calendar DocBirthDate;
	private String DocMail; 
	
	// get&set ==========================================================
	
	public long  getDocLicence() {
		return DocLicence;
	}
	public void setDocLicence(long docLicence) {
		DocLicence = docLicence;
	}
	public Gender getDocGender() {
		return DocGender;
	}
	public void setDocGender(Gender docGender) {
		DocGender = docGender;
	}
	public String getDocFirstName() {
		return DocFirstName;
	}
	public void setDocFirstName(String docFirstName) {
		DocFirstName = docFirstName;
	}
	public String getDocLastName() {
		return DocLastName;
	}
	public void setDocLastName(String docLastName) {
		DocLastName = docLastName;
	}
	public String getDocAdress() {
		return DocAdress;
	}
	public void setDocAdress(String docAdress) {
		DocAdress = docAdress;
	}
	public String getDocTelephoneNumber() {
		return DocTelephoneNumber;
	}
	public void setDocTelephoneNumber(String docTelephoneNumber) {
		DocTelephoneNumber = docTelephoneNumber;
	}
	public Expertise getDocExpertise() {
		return DocExpertise;
	}
	public void setDocExpertise(Expertise docExpertise) {
		DocExpertise = docExpertise;
	}
	public Calendar getDocBirthDate() {
		return DocBirthDate;
	}
	public void setDocBirthDate(Calendar docBirthDate) {
		DocBirthDate = docBirthDate;
	}
	public String getDocMail() {
		return DocMail;
	}
	public void setDocMail(String docMail) {
		DocMail = docMail;
	}
	

	public String getDateAsString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getDocBirthDate().getTime());
	}
	

	
	// constructor ==========================================================

	
	public Doctor(long docLicence, Gender docGender, String docFirstName, String docLastName,
			String docAdress, String docTelephoneNumber, Expertise docExpertise,
			Calendar docBirthDate, String docMail) {
		DocLicence = docLicence;
		DocGender = docGender;
		DocFirstName = docFirstName;
		DocLastName = docLastName;
		DocAdress = docAdress;
		DocTelephoneNumber = docTelephoneNumber;
		DocExpertise = docExpertise;
		DocBirthDate = docBirthDate;
		DocMail = docMail;
	}
	
	// default constructor ===================================================
	
	public Doctor(){
		DocLicence = 0;
		DocGender =null;
		DocFirstName = "";
		DocLastName = "";
		DocAdress = "";
		DocTelephoneNumber = "";
		DocExpertise = null;
		DocBirthDate = null;
		DocMail = "";
	}
	
	// equals function ========================================================
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;		
		if (DocLicence != other.DocLicence)
			return false;		
		return true;
	}
	
	
}
