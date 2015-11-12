package model.backend;

import java.util.ArrayList;
import java.util.Calendar;

import entities.Doctor;
import entities.Illness;
import entities.IllnessVisit;
import entities.Medicine;
import entities.MedicineVisit;
import entities.Password;
import entities.Patient;
import entities.Visit;


public interface Backend {

	//ADD ============================================================================	
	public void addPassword(Password pas) throws Exception;
	public void addPat (Patient pat) throws Exception;
	public void addDoc (Doctor doc) throws Exception;
	public void addMed (Medicine med) throws Exception;
	public void addIll (Illness ill) throws Exception;
	public void addVis (Visit vis) throws Exception;
	void addMedToVis(long visNum, long medNum) throws Exception;
	void addIllToVis(long visNum, long illNum) throws Exception;
	
	//DELETE ==========================================================================	

	public void deletePat (long patID) throws Exception;
	public void deleteDoc (long docID) throws Exception;
	public void deleteMed (long medID) throws Exception;
	public void deleteIll (long illID) throws Exception;

	//UPDATE ==========================================================================	

	public void updatePat (Patient pat) throws Exception;
	public void updateDoc (Doctor doc) throws Exception;
	public void updateMed (Medicine med) throws Exception;
	public void updateIll (Illness ill) throws Exception;	
	public void updateVis (long visNum, Calendar date, String opinion) throws Exception;
	//GETS ============================================================================	

	public Medicine getMedByNum(long num)throws Exception;
	public Illness getIllByNum(long num)throws Exception;
	public long getLastVisForPat(long id);
	public Patient getPatById (long id)throws Exception;
	
	public ArrayList<Patient> getPatientList () throws Exception;
	public ArrayList<Doctor> getDoctorList () throws Exception;
	public ArrayList<Medicine> getMedicineList () throws Exception;
	public ArrayList<Illness> getIllnessList () throws Exception;
	public ArrayList<Password> getPasswordsList () throws Exception;
	public ArrayList<IllnessVisit> getIllnessVisitList () throws Exception;
	public ArrayList<MedicineVisit> getMedicineVisitList () throws Exception;
	
	public ArrayList<Illness> getHistoricIllnessesForPat (long id) throws Exception;
	public ArrayList<Medicine> getHistoricMedicinesForPat (long id) throws Exception;
	public ArrayList<Illness> getIllnessesForVisit (long numVis) throws Exception;
	public ArrayList<Medicine> getMedicinesForVisit (long numVis) throws Exception;
	public ArrayList<Visit> getVisitsByDocLicence (long licence) throws Exception;
	public ArrayList<Visit> getVisitsByDocLicenceAndDate (long licence,int year, int month, int day) throws Exception;
	public ArrayList<Visit> getVisitsByPatID (long id) throws Exception;

	public void setLists();
	
}
