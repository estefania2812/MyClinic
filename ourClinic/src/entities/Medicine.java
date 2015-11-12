package entities;

public class Medicine {
	
	private long MedNum;
	private String MedName;
	
	// get&set ==========================================================
	
	public long getMedNum() {
		return MedNum;
	}
	public void setMedNum(long medNum) {
		MedNum = medNum;
	}
	public String getMedName() {
		return MedName;
	}
	public void setMedName(String medName) {
		MedName = medName;
	}
	
	// constructor ==========================================================
	
	public Medicine(long medNum, String medName) {
		super();
		MedNum = medNum;
		MedName = medName;
	}		
	// default constructor ===================================================

	public Medicine() {
		MedNum = 0;
		MedName = "";
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
		Medicine other = (Medicine) obj;		
		if (MedNum != other.MedNum)
			return false;
		return true;
	}
	// ToStribg function =======================================================

	@Override
	public String toString() {
		return "Number: " + MedNum + ",Name: " + MedName;
	}

}
