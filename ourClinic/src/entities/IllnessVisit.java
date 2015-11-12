package entities;

public class IllnessVisit {
	
	
	private long VisNum;
	private long IllNum;
	
	// get&set ==========================================================

	
	public long getVisNum() {
		return VisNum;
	}
	public void setVisNum(long visNum) {
		VisNum = visNum;
	}
	public long getIllNum() {
		return IllNum;
	}
	public void setIllNum(long illNum) {
		IllNum = illNum;
	}
	// constructor ==========================================================
	public IllnessVisit(long visNum, long illNum) {
		super();
		VisNum = visNum;
		IllNum = illNum;
	}
	// default constructor ===================================================
	public IllnessVisit() {
		VisNum = 0;
		IllNum = 0;
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
		IllnessVisit other = (IllnessVisit) obj;
		if (IllNum != other.IllNum)
			return false;
		if (VisNum != other.VisNum)
			return false;
		return true;
	}
	
	
}
