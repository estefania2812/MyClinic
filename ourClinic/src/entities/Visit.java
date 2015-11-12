package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;

public class Visit implements Serializable{
	
	private long VisNum;
	private Calendar VisDate;
	private long PatID;
	private long  DocLicence;	
	private String DocOpinions;
	
	// get&set ==========================================================

	
	public long getVisNum() {
		return VisNum;
	}
	public void setVisNum(long visNum) {
		VisNum = visNum;
	}
	public Calendar getVisDate() {
		return VisDate;
	}
	public void setVisDate(Calendar visDate) {
		VisDate = visDate;
	}
	public long getPatID() {
		return PatID;
	}
	public void setPatID(long patID) {
		PatID = patID;
	}
	public long getDocLicence() {
		return DocLicence;
	}
	public void setDocLicence(long docLicence) {
		DocLicence = docLicence;
	}
	public String getDocOpinions() {
		return DocOpinions;
	}
	public void setDocOpinions(String docOpinions) {
		DocOpinions = docOpinions;
	}
	public String getDateAsString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(getVisDate().getTime());
		
	}
	// constructor ==========================================================
	
	public Visit(Calendar visDate, long patID, long docLicence,String docOpinions) {
		super();
		VisNum = 0;
		VisDate = visDate;
		PatID = patID;
		DocLicence = docLicence;
		DocOpinions = docOpinions;
	}
	// default constructor ===================================================
	
	public Visit(){
		VisNum =0;//  StaticVisNum++; **???
		VisDate = null;
		PatID = 0;
		DocLicence = 0;
		DocOpinions = "";
	}
	// ToString ===================================================
	@Override
	public String toString() {
		
		return this.getDateAsString() +"\n"+ "Patient: "+ PatID + ", Doctor: " + DocLicence;
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
		Visit other = (Visit) obj;
		if (VisNum != other.VisNum)
			return false;
		return true;
	}

	

}
