package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
public class Patient implements Serializable  {

	private static final long seriaLVersionUID=1L;
	private long PatID;
	private String PatFirstName;
	private String PatLastName;
	private Gender PatGender;
	private Calendar PatBirthDate;
	private String PatAdress;
	private String PatTelephoneNumber;
	private long PatAccount;
	private String PatMail;
	
	// get&set ==========================================================
	
	
	public long getPatID() {
		return PatID;
	}
	public void setPatID(long patID) {
		PatID = patID;
	}
	public String getPatFirstName() {
		return PatFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		PatFirstName = patFirstName;
	}
	public String getPatLastName() {
		return PatLastName;
	}
	public void setPatLastName(String patLastName) {
		PatLastName = patLastName;
	}
	public Gender getPatGender() {
		return PatGender;
	}
	public void setPatGender(Gender patGender) {
		PatGender=patGender;
	}
	public Calendar getPatBirthDate() {
		return PatBirthDate;
	}
	public void setPatBirthDate(Calendar patBirthDate) {
		PatBirthDate = patBirthDate;
	}
	public String getPatAdress() {
		return PatAdress;
	}
	public void setPatAdress(String patAdress) {
		PatAdress = patAdress;
	}
	public String getPatTelephoneNumber() {
		return PatTelephoneNumber;
	}
	public void setPatTelephoneNumber(String patTelephoneNumber) {
		PatTelephoneNumber = patTelephoneNumber;
	}
	public long getPatAccount() {
		return PatAccount;
	}
	public void setPatAccount(long patAccount) {
		PatAccount = patAccount;
	}
	public String getPatMail() {
		return PatMail;
	}
	public void setPatMail(String patMail) {
		PatMail = patMail;
	}
	
	public String getDateAsString()
	{		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getPatBirthDate().getTime());
	}
	
	

	// constructor ==========================================================
	
	public Patient(long patID, String patFirstName, Gender patGender, String patLastName,
			Calendar patBirthDate, String patAdress, String patTelephoneNumber,
			long patAccount, String patMail) {
		PatID = patID;
		PatFirstName = patFirstName;
		PatLastName = patLastName;
		PatGender = patGender;
		PatBirthDate = patBirthDate;
		PatAdress = patAdress;
		PatTelephoneNumber = patTelephoneNumber;
		PatAccount = patAccount;
		PatMail = patMail;
	}
	// default constructor ===================================================
	public Patient() {
		PatID = 0;
		PatFirstName = "";
		PatLastName = "";
		PatGender = null;
		PatBirthDate = null;
		PatAdress = "";
		PatTelephoneNumber = "";
		PatAccount = 0;
		PatMail ="";
	}
	// equals function =======================================================
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (PatID != other.PatID)
			return false;	
		return true;
	}


}
