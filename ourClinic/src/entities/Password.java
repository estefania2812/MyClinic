package entities;

public class Password {
	
	private long DocLicence;
	private String DocPassword;
	// get&set ==========================================================

	public long getDocLicence() {
		return DocLicence;
	}
	public void setDocLicence(long docLicence) {
		DocLicence = docLicence;
	}
	public String getDocPassword() {
		return DocPassword;
	}
	public void setDocPassword(String docPassword) {
		DocPassword = docPassword;
	}
	
	// constructor ==========================================================

	public Password( long docLicence, String docPassword) {
		super();
		DocLicence = docLicence;
		DocPassword = docPassword;
	}
	public Password() {
		super();
		DocLicence = 0;
		DocPassword ="";
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
		Password other = (Password) obj;
		if (DocLicence != other.DocLicence)
			return false;
		if (DocPassword == null) {
			if (other.DocPassword != null)
				return false;
		} else if (!DocPassword.equals(other.DocPassword))
			return false;
		return true;
	}




}
