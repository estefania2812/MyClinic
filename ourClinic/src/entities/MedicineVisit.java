package entities;

public class MedicineVisit {
	
	
	public static long StaticMedVisNum=1;
	private long VisNum;
	private long MedNum;
	
	// get&set ==========================================================
	
	public long getVisNum() {
		return VisNum;
	}
	public void setVisNum(long visNum) {
		VisNum = visNum;
	}
	public long getMedNum() {
		return MedNum;
	}
	public void setMedNum(long medNum) {
		MedNum = medNum;
	}
	// constructor ==========================================================
	
	public MedicineVisit(long visNum, long medNum) {
		super();
		VisNum = visNum;
		MedNum = medNum;
	}
	// default constructor ===================================================
	public MedicineVisit() {
		VisNum = 0;
		MedNum =0;
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
		MedicineVisit other = (MedicineVisit) obj;
		if (MedNum != other.MedNum)
			return false;
		if (VisNum != other.VisNum)
			return false;
		return true;
	}

}
