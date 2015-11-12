package entities;

public class Illness {
	
	private long IllNum;	
	private String IllName;
	private String IllDescription;
	
	// get&set ==========================================================

	public long getIllNum() {
		return IllNum;
	}
	public void setIllNum(long illNum) {
		IllNum = illNum;
	}
	public String getIllName() {
		return IllName;
	}
	public void setIllName(String illName) {
		IllName = illName;
	}
	public String getIllDescription() {
		return IllDescription;
	}
	public void setIllDescription(String illDescription) {
		IllDescription = illDescription;
	}
	
	// constructor ==========================================================

	public Illness(long illNum, String illName, String illDescription) {
		super();
		IllNum = illNum;
		IllName = illName;
		IllDescription = illDescription;
	}
	
	// default constructor ===================================================

	public Illness() {
		super();
		IllNum = 0;
		IllName = "";
		IllDescription = "";
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
		Illness other = (Illness) obj;
		if (IllNum != other.IllNum)
			return false;
		return true;
	}
	// ToStribg function =======================================================
	@Override
	public String toString() {
		return "Name: " + IllName + "\n" + "Description: " + IllDescription;
	}

}
