package model.datasource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.backend.Backend;
import entities.Doctor;
import entities.Gender;
import entities.Illness;
import entities.IllnessVisit;
import entities.Medicine;
import entities.MedicineVisit;
import entities.Password;
import entities.Patient;
import entities.Visit;

public class DatabaseList implements Backend {
	
	private static long numVis=1000;
	//Initialization of the lists
		
	public ArrayList<Password> passwords=new ArrayList<Password>();
	public ArrayList<Patient> patients= new ArrayList<Patient>();
	public ArrayList<Visit> visits= new ArrayList<Visit>();
	public ArrayList<Doctor> doctors= new ArrayList<Doctor>();
	public ArrayList<Medicine> medicines= new ArrayList<Medicine>();
	public ArrayList<Illness> illnesses= new ArrayList<Illness>();
	public ArrayList<MedicineVisit> medicineVisit= new ArrayList<MedicineVisit>();
	public ArrayList<IllnessVisit> illnessVisit= new ArrayList<IllnessVisit>();

	public DatabaseList() 
	{
		setLists();
	}
	
	@Override
	 // Data entry to the lists
	public void setLists()  
	{
		// TODO Auto-generated method stub
		try
		{
		passwords.add(new Password(1234,"12345"));
		passwords.add(new Password(1235,"12356"));
		addPat(new Patient(204308803,"Michal",Gender.Female,"Malik",new GregorianCalendar(1994,05,05),"Cochav Yaakov","0545348536",5555,"michalush17@gmail.com"));
		addPat(new Patient(225683921,"Shmuel",Gender.Male,"Cohen",new GregorianCalendar(1995,11,05),"Jerusalem","0557872392",9999,"shmuel.cohen@gmail.com"));
		addPat(new Patient(337605173, "Chana",Gender.Female,"Goldberg",new GregorianCalendar(2000,06,05),"Tel Aviv","0556437857",1111,"chana@gmail.com"));
		addVis(new Visit (new GregorianCalendar(2014,10,28,9,30),204308803,1234,"I am tired"));
		addVis(new Visit (new GregorianCalendar(2013,9,28,6,50),337605173,12345,"I am angry"));
		addVis(new Visit (new GregorianCalendar(2013,10,28,12,12),225683921,1234,"lack of sleep"));
		addIll(new Illness(12345678, "head acke", "bla bla" ));
		addIll(new Illness(11111111, "eyes acke", "bla bla bla" ));
		addIll(new Illness (22222222,"stomach acke", "blablabla"));
		addIll(new Illness (33333333, "throat pain", "drink water"));
		addMed(new Medicine(10000, "Acamol"));
		addMed(new Medicine(20000, "Optalgin"));
		addMed(new Medicine(30000, "Antibiotic"));
		addMed(new Medicine(40000, "Dorixin"));
		addMedToVis(1000,10000);
		addMedToVis(1001,20000);
		addMedToVis(1002,40000);
		addIllToVis(1000, 11111111);
		addIllToVis(1001,22222222);



		
		
		} 
		catch (Exception e) 
		{
				e.printStackTrace();

		}

	}
	

	
    //-----------------------------------------------------------
	
	@Override
	//
	public void addPassword (Password pas)  throws Exception  {
		passwords.add(pas);
		
	}
	
	
    //-----------------------------------------------------------
	@Override
	// Add new patient to the list if it doesnt exist
	public void addPat(Patient pat) throws Exception {
		// TODO Auto-generated method stub
		for(Patient patItem : patients)
			if(patItem.equals(pat))
			   throw new Exception
			   ("Patient already exist");		
		patients.add(pat);
		
	}
	//-----------------------------------------------------------
	@Override
	// Add new doctor to the list if it doesnt exist
	public void addDoc(Doctor doc) throws Exception {
		// TODO Auto-generated method stub
		for(Doctor docItem : doctors)
			if(docItem.equals(doc))
			   throw new Exception
			   ("Doctor already exist");		
		//par.setShopID(ShopCounter++);
		doctors.add(doc);
	}
	//-----------------------------------------------------------
	@Override
	// Add new medicine to the list if it doesnt exist
	public void addMed(Medicine med) throws Exception {
		// TODO Auto-generated method stub
		for(Medicine medItem : medicines)
			if(medItem.equals(med))
			   throw new Exception
			   ("Medicine already exist");		
		//par.setShopID(ShopCounter++);
		medicines.add(med);		
	}
	//-----------------------------------------------------------
	@Override
	// Add new illness to the list if it doesnt exist
	public void addIll(Illness ill) throws Exception {
		// TODO Auto-generated method stub
		for(Illness illItem : illnesses)
			if(illItem.equals(ill))
			   throw new Exception
			   ("Illness already exist");		
		//par.setShopID(ShopCounter++);
		illnesses.add(ill);		
	}
	//-----------------------------------------------------------
	@Override
	// Add a new visit to the list and sets its number first
	public void addVis(Visit vis) throws Exception {
		// TODO Auto-generated method stub
		vis.setVisNum(numVis++);
		visits.add(vis);
	}
	//-----------------------------------------------------------
	@Override
	// The function receives visit number and illness number, and adds the illness to this visit (if it doesnt exist already)
	public void addIllToVis(long visNum, long illNum) throws Exception {
		// TODO Auto-generated method stub
		IllnessVisit conector = new IllnessVisit();
		conector.setIllNum(illNum);
		conector.setVisNum(visNum);
		for(IllnessVisit item : illnessVisit)
			if(item.equals(conector))
			   throw new Exception
			   ("Illness already exists in this visit");
		illnessVisit.add(conector);
	}
	//-----------------------------------------------------------
	@Override
	// The function receives visit number and medicine number, and adds the medicine to this visit (if it doesnt exist already)
	public void addMedToVis(long visNum, long medNum) throws Exception {
		// TODO Auto-generated method stub
		MedicineVisit conector = new MedicineVisit();
		conector.setMedNum(medNum);
		conector.setVisNum(visNum);
		for(MedicineVisit item : medicineVisit)
			if(item.equals(conector))
			   throw new Exception
			   ("Medicine already exists in this visit");
		medicineVisit.add(conector);
	}
	//-----------------------------------------------------------
	@Override
	public void deletePat(long patID) throws Exception {
		// TODO Auto-generated method stub		
	}


	@Override
	public void deleteDoc(long docID) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteMed(long medID) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteIll(long illID) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updatePat(Patient pat) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateDoc(Doctor doc) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateMed(Medicine med) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateIll(Illness ill) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//-----------------------------------------------------------
	@Override
	// Returns the patient list if its not empty
	public ArrayList<Patient> getPatientList() throws Exception {
		// TODO Auto-generated method stub		
		if (patients.size()!= 0)
			   return patients;
			else
			   throw new Exception
				   ("There not found patients");
	}
	//-----------------------------------------------------------
	@Override
	// Returns the doctor list if its not empty
	public ArrayList<Doctor> getDoctorList() throws Exception {
		// TODO Auto-generated method stub
		if (doctors.size()!= 0)
			   return doctors;
			else
			   throw new Exception
				   ("There not found doctors");
	}
	//-----------------------------------------------------------
	@Override
	// Returns the medicine list if its not empty
	public ArrayList<Medicine> getMedicineList() throws Exception {
		// TODO Auto-generated method stub
		if (medicines.size()!= 0)
			   return medicines;
			else
			   throw new Exception
				   ("There not found medicines");
	}
	//-----------------------------------------------------------
	@Override
	// Returns the illness list if its not empty
	public ArrayList<Illness> getIllnessList()  {
		// TODO Auto-generated method stub		
		if (illnesses.size()!= 0)
			   return illnesses;
		return illnesses;
			  
	}
	//-----------------------------------------------------------
	@Override
	// The function receives licence number and returns all the visit for that doctor if there is any
	public ArrayList<Visit> getVisitsByDocLicence(long licence)throws Exception 
	{
		// TODO Auto-generated method stub		
		ArrayList<Visit> visitsByDocLicence = new ArrayList<Visit>();
		for (Visit visItem : visits)
			if(visItem .getDocLicence() == licence)
				visitsByDocLicence.add(visItem);			
		
		if(visitsByDocLicence.size() != 0)
		   return visitsByDocLicence;
		else
		   throw new Exception ("Not exist visits for this doctor");			 
	}
	//-----------------------------------------------------------
	@Override
	// The function receives id of patient and returns all the visits for that patient if there is any
	public ArrayList<Visit> getVisitsByPatID(long id) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Visit> visitsByPatID = new ArrayList<Visit>();
		for (Visit visItem : visits)
			if(visItem .getPatID() == id)
				visitsByPatID.add(visItem);			
		
		if(visitsByPatID.size() != 0)
		   return visitsByPatID;
		else
		   throw new Exception ("Not exist visits for this patient");	
	}
	//-----------------------------------------------------------
	@Override
	// Returns the password list if its not empty
	public ArrayList<Password> getPasswordsList() throws Exception {
		// TODO Auto-generated method stub
		if (passwords.size()!= 0)
			   return passwords;
			else
			   throw new Exception
				   ("There are no passwords");
	}
	//-----------------------------------------------------------
	@Override
	// Returns the conector list of illnesses and visits if there is some illness for some visit
	public ArrayList<IllnessVisit> getIllnessVisitList() throws Exception {
		// TODO Auto-generated method stub
		if (illnessVisit.size()!= 0)
			   return illnessVisit;
			else
			   throw new Exception
				   ("There are no illnesses for any visit");
	}
	//-----------------------------------------------------------
	@Override
	//Returns the conector list of medicines and visits if there is some medicine for some visit 
	public ArrayList<MedicineVisit> getMedicineVisitList() throws Exception {
		// TODO Auto-generated method stub
		if (medicineVisit.size()!= 0)
			   return medicineVisit;
			else
			   throw new Exception
				   ("Empty");		
	}
	//-----------------------------------------------------------
	@Override
	// Receives id of patient and returns all the illnesses in the history of the patient (if there is some)
	public ArrayList<Illness> getHistoricIllnessesForPat(long id)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Illness>ill = new ArrayList<Illness>();
		if (illnessVisit.size()==0)
			throw new Exception
			   ("Error");
		for (Visit item : getVisitsByPatID(id)) // מעבר על כל ביקורי הפציינט
		{
			for (IllnessVisit illVis : illnessVisit) // עבור כל ביקור, עוברים על רשימת ביקור-מחלה, ומחפשים אותו שם
			{
				if(item.getVisNum()==illVis.getVisNum())
					ill.add(getIllByNum(illVis.getIllNum()));					
			}
		}
		return ill;
	}
	
	//-----------------------------------------------------------
	@Override
	// Receives id of patient and returns all the medicines in the history of the patient (if there is some)
	public ArrayList<Medicine> getHistoricMedicinesForPat(long id)
			throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<Medicine>meds = new ArrayList<Medicine>();
		if (medicineVisit.size()==0)
			throw new Exception
			   ("Error");
		for (Visit item : getVisitsByPatID(id)) // מעבר על כל ביקורי הפציינט
		{
			for (MedicineVisit medvis : medicineVisit) // עבור כל ביקור, עוברים על רשימת ביקור-תרופה, ומחפשים אותו שם
			{
				if(item.getVisNum()==medvis.getVisNum())
					meds.add(getMedByNum(medvis.getMedNum()));					
			}
		}
		return meds;
	}
	//-----------------------------------------------------------
	@Override
	// Receives number of visit and returns the illnesses for that visit (if there is some)
	public ArrayList<Illness> getIllnessesForVisit(long numVis)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Illness>ills = new ArrayList<Illness>();
		for (IllnessVisit illVis : illnessVisit) // עבור כל ביקור, עוברים על רשימת ביקור-מחלה, ומחפשים אותו שם
			{
				if(illVis.getVisNum()==numVis)
					ills.add(getIllByNum(illVis.getIllNum()));					
			}
		
		return ills;
	}
	//-----------------------------------------------------------
	@Override
	// Receives number of visit and returns the medicines for that visit (if there is some)
	public ArrayList<Medicine> getMedicinesForVisit(long numVis)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Medicine>meds = new ArrayList<Medicine>();
		for (MedicineVisit medVis : medicineVisit) // עבור כל ביקור, עוברים על רשימת ביקור-תרופה, ומחפשים אותו שם
			{
				if(medVis.getVisNum()==numVis)
					meds.add(getMedByNum(medVis.getMedNum()));					
			}
		
		return meds;
	}
	//-----------------------------------------------------------
	@Override
	// Returns medicine according to the received number
	public Medicine getMedByNum(long num) {
		// TODO Auto-generated method stub
		Medicine medToReturn = new Medicine();
		for (Medicine med : medicines) 
			if(med.getMedNum()==num)
				medToReturn = med;
	    return medToReturn;		
	}
	//-----------------------------------------------------------
	@Override
	// Returns illness according to the received number
	public Illness getIllByNum(long num) {
		// TODO Auto-generated method stub
		Illness illbynum = new Illness();
		for (Illness ill : illnesses) 
			if(ill.getIllNum()==num)
				illbynum = ill;
	    return illbynum;	
		
	}
	//-----------------------------------------------------------
	@Override
	// Receives number, date and opinion of visit and updates the data for that visit according to its number
	public void updateVis(long visNum, Calendar date, String opinion){
		// TODO Auto-generated method stub
		for (Visit item : visits) 
		{
			if(item.getVisNum() == visNum)
			{
			    
				Calendar date1 = date;
				int year = date1.get(Calendar.YEAR);
				int month = date1.get(Calendar.MONTH);
				int day = date1.get(Calendar.DAY_OF_MONTH);
				int hour = date1.get(Calendar.HOUR_OF_DAY);
				int minute = date1.get(Calendar.MINUTE);
				item.setDocOpinions(opinion);
				item.setVisDate(new GregorianCalendar(year,month,day,hour,minute));
			}							
		}		
	}
	//-----------------------------------------------------------
	@Override
	// Receives patient id and returns the number of the last visit of that patient
	public long getLastVisForPat(long id) {
		// TODO Auto-generated method stub
		ArrayList<Visit>allVisitsForPat = null ;
		try {
			allVisitsForPat = getVisitsByPatID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long maxNumVis = 1000;
		for (Visit visit : allVisitsForPat) 
		{
			if(visit.getVisNum()>maxNumVis)
				maxNumVis = visit.getVisNum();				
		}

		return maxNumVis;
	}
	//-----------------------------------------------------------
	@Override
	// Receives patient id and returns that patient
	public Patient getPatById(long id) {
		// TODO Auto-generated method stub
	    Patient patbyid = new Patient();
		for (Patient pat : patients) 
			if(pat.getPatID()==id)
				patbyid = pat;
	    return patbyid;	
	
	}
	//-----------------------------------------------------------
	@Override
	// Returns the visits for an specific doctor in an specific date 
	public ArrayList<Visit> getVisitsByDocLicenceAndDate(long licence,
			int year, int month, int day) throws Exception
			{
		// TODO Auto-generated method stub
		ArrayList<Visit>allVisitsForDoc = null;
		ArrayList<Visit>VisitsForDoc =new ArrayList<Visit>();			
		allVisitsForDoc = getVisitsByDocLicence(licence);
			for (Visit visit : allVisitsForDoc) 
			{
				Calendar VisDate= visit.getVisDate();
				int cyear = VisDate.get(Calendar.YEAR);  
				int cmonth =VisDate.get(Calendar.MONTH);  
				int cday = VisDate.get(Calendar.DAY_OF_MONTH);
				if(cyear==year && cmonth== month && cday == day)
					VisitsForDoc.add(visit);		
	        }
			if(VisitsForDoc.size() != 0)	
				return VisitsForDoc;
			else
				  throw new Exception ("Not exist visits for this date!");	
	}

}
