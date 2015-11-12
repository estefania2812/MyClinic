package model.datasource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import entities.Doctor;
import entities.Expertise;
import entities.Gender;
import entities.Illness;
import entities.IllnessVisit;
import entities.Medicine;
import entities.MedicineVisit;
import entities.Password;
import entities.Patient;
import entities.Visit;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ParseException;
import android.util.Log;
import model.backend.Backend;


	@SuppressLint("SimpleDateFormat")
	public class DatabaseSqlite extends SQLiteOpenHelper implements Backend{
        //-------------------------------------------------------------------------------

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");// for birth date.
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");// for vis date.
        //-------------------------------------------------------------------------------
		public DatabaseSqlite(Context context)
		{
		  super(context, SQLNAME.DATABASE_NAME, null, SQLNAME.DATABASE_VERSION);
		}		
        //-------------------------------------------------------------------------------

		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
				/** 
				 *  Creates Password Table
				 *  long DocLicence
				 *  String DocPassword
				 *  
				 */
			 
				 String CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_PASSWORD + "("
						 + SQLNAME.KEY_P_DOC_LICENCE + " INTEGER,"
						 + SQLNAME.KEY_P_PASSWORD + " TEXT,"  + 
						 "constraint pk1 primary key (" + SQLNAME.KEY_P_DOC_LICENCE + ")," +
						 "constraint fk1 foreign key (" + SQLNAME.KEY_P_DOC_LICENCE + ") references " + SQLNAME.TABLE_DOCTOR
					+ "(" + SQLNAME.KEY_DOC_LICENCE + ") ON DELETE CASCADE ON UPDATE NO ACTION "
					+	  ")";
					db.execSQL(CREATE_TABLE);
					
					
					/**
					 * Creates Doctor Table
					 * long DocLicence
					 * Gender DocGender
					 * DocFirstName
					 * String DocLastName
					 * String DocAdress
					 * long DocTelephoneNumber
					 * Expertise DocExpertise
					 * Calendar DocBirthDate
					 * String DocMail
					 */

				   CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_DOCTOR + "(" +
				    SQLNAME.KEY_DOC_LICENCE + " INTEGER PRIMARY KEY, " + 
				    SQLNAME.KEY_DOC_GENDER + " TEXT, " + 
					SQLNAME.KEY_DOC_FIRST_NAME + " TEXT," + 
				    SQLNAME.KEY_DOC_LAST_NAME + " TEXT," + 
					SQLNAME.KEY_DOC_ADDRESS + " TEXT, " +
					SQLNAME.KEY_DOC_PHONE + " TEXT, " + 
					SQLNAME.KEY_DOC_EXPERTISE + " TEXT, " + 
					SQLNAME.KEY_DOC_BIRTH_DATE + " DATETIME, "+
					SQLNAME.KEY_DOC_MAIL + " TEXT" + ")";
					db.execSQL(CREATE_TABLE);
					
					/** 
					 * Creates Patient Table
					 * long PatID
					 * String PatFirstName
					 * String PatLastName
					 * Gender PatGender
					 * Calendar PatBirthDate
					 * String PatAdress
					 * String PatTelephoneNumber
					 * long PatAccount
					 * String PatMail
		
					 */

			       CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_PATIENT + "(" +
				    SQLNAME.KEY_PAT_ID + " INTEGER PRIMARY KEY," + 
					SQLNAME.KEY_PAT_GENDER + " TEXT," + 
					SQLNAME.KEY_PAT_FIRST_NAME + " TEXT," + 
					SQLNAME.KEY_PAT_LAST_NAME + " TEXT," + 
					SQLNAME.KEY_PAT_ADDRESS + " TEXT," +
					SQLNAME.KEY_PAT_PHONE + " TEXT," + 
					SQLNAME.KEY_PAT_BIRTH_DATE + " DATETIME,"+
					SQLNAME.KEY_PAT_ACCOUNT + " INTEGER,"+
					SQLNAME.KEY_PAT_MAIL + " TEXT" + ")";
					db.execSQL(CREATE_TABLE);
					
					/** 
					 * Creates Medicine Table 
					 * long MedNum
					 * String MedName
				     */
					
					CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_MEDICINE + "("
							+ SQLNAME.KEY_MED_NUM + " INTEGER PRIMARY KEY, " 
							+ SQLNAME.KEY_MED_NAME + " TEXT"
						    + ")";
					db.execSQL(CREATE_TABLE);
					
					/** 
					 * Creates Illness Table 
					 * long IllNum
					 * String IllName
					 * String IllDescription
				     */
					
					CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_ILLNESS + "("
							+ SQLNAME.KEY_ILL_NUM + " INTEGER PRIMARY KEY, " 
							+ SQLNAME.KEY_ILL_NAME + " TEXT, "
							+ SQLNAME.KEY_ILL_DESCRIPTION + " TEXT"
						    + ")";
					db.execSQL(CREATE_TABLE);
					
					/** 
					 *Creates Visit Table 
					 *long VisNum
					 *Calendar VisDate 
					 *long PatID
					 *long  DocLicence
					 *String DocOpinions
				     */
				
					CREATE_TABLE = String.format("CREATE TABLE [%s] (\r\n" + 
							"	[%s]	INTEGER PRIMARY KEY,\r\n" + // מספר ביקור
							"	[%s]	DATETIME,\r\n" + // תאריך
							"	[%s]	INTEGER,\r\n" + // פציינט
							"	[%s]	INTEGER,\r\n" + // רופא
							"	[%s]	TEXT,\r\n" + // דעה
							"FOREIGN KEY ([%s]) REFERENCES [%s]([%s]) " +
							"ON DELETE CASCADE ON UPDATE NO ACTION, " +
							"FOREIGN KEY ([%s]) REFERENCES [%s]([%s]) " +
							"ON DELETE CASCADE ON UPDATE NO ACTION " +
							");", SQLNAME.TABLE_VISIT,SQLNAME.KEY_VIS_NUM,  SQLNAME.KEY_VIS_DATE ,SQLNAME.KEY_VIS_PAT_ID, 
							SQLNAME.KEY_VIS_DOC_LICENCE, SQLNAME.KEY_VIS_DOC_OPINIONS,
							SQLNAME.KEY_VIS_DOC_LICENCE, SQLNAME.TABLE_DOCTOR ,SQLNAME.KEY_DOC_LICENCE, 
							SQLNAME.KEY_VIS_PAT_ID, SQLNAME.TABLE_PATIENT,SQLNAME.KEY_PAT_ID);
					db.execSQL(CREATE_TABLE);				
					
					/** 
					 * Creates medicine_visit table
					 * long VisNum
					 * long MedNum
					 */

					
					CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_MEDICINE_VISIT + "("
							+ SQLNAME.KEY_MEDVIS_VIS_NUM + " INTEGER , " + SQLNAME.KEY_MEDVIS_MED_NUM + " INTEGER, " 						
							+ "constraint pk1 primary key (" + SQLNAME.KEY_MEDVIS_VIS_NUM + ", " + SQLNAME.KEY_MEDVIS_MED_NUM + "),"
							+ "constraint fk1 foreign key (" + SQLNAME.KEY_MEDVIS_VIS_NUM + ") references " + SQLNAME.TABLE_VISIT
							+ "(" + SQLNAME.KEY_VIS_NUM + ")ON DELETE CASCADE ON UPDATE NO ACTION ,"
							+ "constraint fk2 foreign key (" + SQLNAME.KEY_MEDVIS_MED_NUM + ") references " + SQLNAME.TABLE_MEDICINE
							+ "(" + SQLNAME.KEY_MED_NUM + ") ON DELETE CASCADE ON UPDATE NO ACTION)";

					db.execSQL(CREATE_TABLE);
					
					/** 
					 * Creates illness_visit table
					 * long VisNum
					 * long IllNum
					 */

					CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_ILLNESS_VISIT + "("
							+ SQLNAME.KEY_ILLVIS_VIS_NUM + " INTEGER , " + SQLNAME.KEY_ILLVIS_ILL_NUM + " INTEGER, " 						
							+ "constraint pk1 primary key (" + SQLNAME.KEY_ILLVIS_VIS_NUM + ", " + SQLNAME.KEY_ILLVIS_ILL_NUM + "),"
							+ "constraint fk1 foreign key (" + SQLNAME.KEY_ILLVIS_VIS_NUM + ") references " + SQLNAME.TABLE_VISIT
							+ "(" + SQLNAME.KEY_VIS_NUM + ")ON DELETE CASCADE ON UPDATE NO ACTION ,"
							+ "constraint fk2 foreign key (" + SQLNAME.KEY_ILLVIS_ILL_NUM + ") references " + SQLNAME.TABLE_ILLNESS
							+ "(" + SQLNAME.KEY_ILL_NUM + ") ON DELETE CASCADE ON UPDATE NO ACTION)";
					db.execSQL(CREATE_TABLE);												
			}
			
        //-------------------------------------------------------------------------------

		@Override
		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Drops older table if existed
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_PASSWORD);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_DOCTOR);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_PATIENT);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_MEDICINE);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_ILLNESS);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_VISIT);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_MEDICINE_VISIT);
					db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_ILLNESS_VISIT);

			// Create tables again
			onCreate(db);
		}

        //-------------------------------------------------------------------------------

		@Override
		// Adds new Password
		public void addPassword(Password pas) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_P_PASSWORD,pas.getDocPassword());
			values.put(SQLNAME.KEY_P_DOC_LICENCE,pas.getDocLicence());
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_PASSWORD, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection						
		}
        //-------------------------------------------------------------------------------

		@Override
		//Adds new patient if it doesnt exist
		public void addPat(Patient pat) throws Exception {
			
			for(Patient patItem : this.getPatientList())
				if(patItem.equals(pat))
				   throw new Exception
				   ("ERROR - Patient already exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
					
			values.put(SQLNAME.KEY_PAT_ID,pat.getPatID());
			values.put(SQLNAME.KEY_PAT_GENDER,pat.getPatGender().toString());
			values.put(SQLNAME.KEY_PAT_FIRST_NAME,pat.getPatFirstName());
			values.put(SQLNAME.KEY_PAT_LAST_NAME,pat.getPatLastName());
			values.put(SQLNAME.KEY_PAT_ADDRESS,pat.getPatAdress());
			values.put(SQLNAME.KEY_PAT_PHONE,pat.getPatTelephoneNumber());
			values.put(SQLNAME.KEY_PAT_BIRTH_DATE, sdf.format(pat.getPatBirthDate().getTime()));
			values.put(SQLNAME.KEY_PAT_ACCOUNT,pat.getPatAccount());
			values.put(SQLNAME.KEY_PAT_MAIL,pat.getPatMail());
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_PATIENT, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection
			
		}
        //-------------------------------------------------------------------------------

		@Override
		//Adds new doctor if it doesnt exist
		public void addDoc(Doctor doc) throws Exception {
			
			for(Doctor docItem : this.getDoctorList())
				if(docItem.equals(doc))
				  throw new Exception ("Doctor already exist");	
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_DOC_LICENCE,doc.getDocLicence());
			values.put(SQLNAME.KEY_DOC_FIRST_NAME,doc.getDocFirstName());
			values.put(SQLNAME.KEY_DOC_LAST_NAME,doc.getDocLastName());
			values.put(SQLNAME.KEY_DOC_GENDER,doc.getDocGender().toString());
			values.put(SQLNAME.KEY_DOC_ADDRESS,doc.getDocAdress());
			values.put(SQLNAME.KEY_DOC_PHONE,doc.getDocTelephoneNumber());
			values.put(SQLNAME.KEY_DOC_EXPERTISE,doc.getDocExpertise().toString());
			values.put(SQLNAME.KEY_DOC_BIRTH_DATE,sdf.format(doc.getDocBirthDate().getTime()));
			values.put(SQLNAME.KEY_DOC_MAIL,doc.getDocMail());						
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_DOCTOR, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Adds new medicine if it doesnt exist
		public void addMed(Medicine med) throws Exception {
			
			for(Medicine medItem : this.getMedicineList())
				if(medItem.equals(med))
				   throw new Exception
				   ("ERROR - Medicine already exist");	
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_MED_NUM,med.getMedNum());
			values.put(SQLNAME.KEY_MED_NAME,med.getMedName());
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_MEDICINE, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection			
		}
        //-------------------------------------------------------------------------------

		@Override
		//Adds new illness if it doesnt exist
		public void addIll(Illness ill) throws Exception {
			
			for(Illness illItem : this.getIllnessList())
				if(illItem.equals(ill))
				   throw new Exception
				   ("ERROR - Illness already exist");	
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_ILL_NUM,ill.getIllNum());
			values.put(SQLNAME.KEY_ILL_NAME,ill.getIllName());
			values.put(SQLNAME.KEY_ILL_DESCRIPTION,ill.getIllDescription());
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_ILLNESS, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection			
		}
        //-------------------------------------------------------------------------------

		@Override
		//Adds new visit
		public void addVis(Visit vis) throws Exception {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_VIS_DATE,sdf1.format(vis.getVisDate().getTime()));
			values.put(SQLNAME.KEY_VIS_PAT_ID,vis.getPatID());
			values.put(SQLNAME.KEY_VIS_DOC_LICENCE,vis.getDocLicence());
			values.put(SQLNAME.KEY_VIS_DOC_OPINIONS,vis.getDocOpinions());
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_VISIT, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection			
		}
        //-------------------------------------------------------------------------------

		@Override
        // Adds new medicine to an specific visit if it doesnt exist already
		public void addMedToVis(long visNum, long medNum) throws Exception {
			
			MedicineVisit conector = new MedicineVisit();
			conector.setMedNum(medNum);
			conector.setVisNum(visNum);
			for(MedicineVisit item : this.getMedicineVisitList())
				if(item.equals(conector))
				   throw new Exception
				   ("ERROR - Medicine already exists in this visit");
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_MEDVIS_VIS_NUM,visNum);
			values.put(SQLNAME.KEY_MEDVIS_MED_NUM,medNum);
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_MEDICINE_VISIT, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection
		}
        //-------------------------------------------------------------------------------

		@Override
		// Adds new illness to an specific visit if it doesnt exist already
		public void addIllToVis(long visNum, long illNum) throws Exception {
			
			IllnessVisit conector = new IllnessVisit();
			conector.setIllNum(illNum);
			conector.setVisNum(visNum);
			for(IllnessVisit item : this.getIllnessVisitList())
				if(item.equals(conector))
				   throw new Exception
				   ("ERROR - Illness already exists in this visit");			
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

			values.put(SQLNAME.KEY_ILLVIS_VIS_NUM,visNum);
			values.put(SQLNAME.KEY_ILLVIS_ILL_NUM,illNum);
			
			// Inserting Row
			db.insertWithOnConflict(SQLNAME.TABLE_ILLNESS_VISIT, null, values,SQLiteDatabase.CONFLICT_IGNORE);
			db.close(); // Closing database connection			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Deletes patient if it exists
		public void deletePat(long patID) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Patient patItem : this.getPatientList())
				if(patItem.getPatID()==patID)
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Patient doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(SQLNAME.TABLE_PATIENT, SQLNAME.KEY_PAT_ID + " = ?", 
					new String[] {String.valueOf(patID)});
			db.close(); 
			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Deletes doctor if it exists
		public void deleteDoc(long docID) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Doctor docItem : this.getDoctorList())
				if(docItem.getDocLicence()==docID)
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Doctor doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(SQLNAME.TABLE_DOCTOR, SQLNAME.KEY_DOC_LICENCE+ " = ?", 
					new String[] {String.valueOf(docID)});
			db.close(); 
			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Deletes medicine if it exists
		public void deleteMed(long medID) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Medicine medItem : this.getMedicineList())
				if(medItem.getMedNum()==medID)
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Medicine doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(SQLNAME.TABLE_MEDICINE, SQLNAME.KEY_MED_NUM + " = ?", 
					new String[] {String.valueOf(medID)});
			db.close(); 
			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Deletes illness if it exists
		public void deleteIll(long illID) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Illness illItem : this.getIllnessList())
				if(illItem.getIllNum()==illID)
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Illness doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(SQLNAME.TABLE_ILLNESS, SQLNAME.KEY_ILL_NUM + " = ?", 
					new String[] {String.valueOf(illID)});
			db.close(); 
			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Updates patient if it exists
		public void updatePat(Patient pat) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Patient patItem : this.getPatientList())
				if(patItem.equals(pat))
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Patient doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put(SQLNAME.KEY_PAT_ID,pat.getPatID());
			values.put(SQLNAME.KEY_PAT_GENDER,pat.getPatGender().toString());
			values.put(SQLNAME.KEY_PAT_FIRST_NAME,pat.getPatFirstName());
			values.put(SQLNAME.KEY_PAT_LAST_NAME,pat.getPatLastName());
			values.put(SQLNAME.KEY_PAT_ADDRESS,pat.getPatAdress());
			values.put(SQLNAME.KEY_PAT_PHONE,pat.getPatTelephoneNumber());
			values.put(SQLNAME.KEY_PAT_BIRTH_DATE, sdf.format(pat.getPatBirthDate().getTime()));
			values.put(SQLNAME.KEY_PAT_ACCOUNT,pat.getPatAccount());
			values.put(SQLNAME.KEY_PAT_MAIL,pat.getPatMail());
			
			db.update(SQLNAME.TABLE_PATIENT, values,
					SQLNAME.KEY_PAT_ID+ " = ?", new String[] {String.valueOf(pat.getPatID())});
			db.close();
			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Updates doctor if it exists
		public void updateDoc(Doctor doc) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Doctor docItem : this.getDoctorList())
				if(docItem.equals(doc))
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Doctor doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(SQLNAME.KEY_DOC_LICENCE,doc.getDocLicence());
			values.put(SQLNAME.KEY_DOC_FIRST_NAME,doc.getDocFirstName());
			values.put(SQLNAME.KEY_DOC_LAST_NAME,doc.getDocLastName());
			values.put(SQLNAME.KEY_DOC_GENDER,doc.getDocGender().toString());
			values.put(SQLNAME.KEY_DOC_ADDRESS,doc.getDocAdress());
			values.put(SQLNAME.KEY_DOC_PHONE,doc.getDocTelephoneNumber());
			values.put(SQLNAME.KEY_DOC_EXPERTISE,doc.getDocExpertise().toString());
			values.put(SQLNAME.KEY_DOC_BIRTH_DATE,sdf.format(doc.getDocBirthDate().getTime()));
			values.put(SQLNAME.KEY_DOC_MAIL,doc.getDocMail());
						
			db.update(SQLNAME.TABLE_DOCTOR, values,
					SQLNAME.KEY_DOC_LICENCE+ " = ?", new String[] {String.valueOf(doc.getDocLicence())});
			db.close();
		}
        //-------------------------------------------------------------------------------

		@Override
		// Updates medicine if it exists
		public void updateMed(Medicine med) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Medicine medItem : this.getMedicineList())
				if(medItem.equals(med))
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Medicine doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(SQLNAME.KEY_MED_NUM,med.getMedNum());
			values.put(SQLNAME.KEY_MED_NAME,med.getMedName());
						
			db.update(SQLNAME.TABLE_MEDICINE, values,
					SQLNAME.KEY_MED_NUM+ " = ?", new String[] {String.valueOf(med.getMedNum())});
			db.close();
			
		}
        //-------------------------------------------------------------------------------

		@Override
		// Updates illness if it exists
		public void updateIll(Illness ill) throws Exception {
			// TODO Auto-generated method stub
			
			boolean isExist = false;
			for(Illness illItem : this.getIllnessList())
				if(illItem.equals(ill))
					isExist = true;
			if(isExist == false)
				   throw new Exception("ERROR - Illness doesn't exist");
			
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(SQLNAME.KEY_ILL_NUM,ill.getIllNum());
			values.put(SQLNAME.KEY_ILL_NAME,ill.getIllName());
			values.put(SQLNAME.KEY_ILL_DESCRIPTION,ill.getIllDescription());
			
			db.update(SQLNAME.TABLE_ILLNESS, values,
					SQLNAME.KEY_ILL_NUM+ " = ?", new String[] {String.valueOf(ill.getIllNum())});
			db.close();
		}
        //-------------------------------------------------------------------------------

		@Override
		// Updates visit
		public void updateVis(long visNum, Calendar date, String opinion) throws Exception {
			// TODO Auto-generated method stub

			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_VISIT;
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Visit vis = new Visit();
				    vis.setVisNum(cursor.getLong(0));
				    String timestampToParse = (cursor.getString(1));
				    vis.setVisDate ((parseTimestampForVis(timestampToParse)));
					vis.setPatID(cursor.getLong(2));					
					vis.setDocLicence(cursor.getLong(3));
					vis.setDocOpinions((String)cursor.getString(4));					
					if(vis.getVisNum()==visNum)	
					{
						SQLiteDatabase db1 = this.getWritableDatabase();
						
						ContentValues values = new ContentValues();
						values.put(SQLNAME.KEY_VIS_DATE,sdf1.format(date.getTime()));
						values.put(SQLNAME.KEY_VIS_PAT_ID,vis.getPatID());
						values.put(SQLNAME.KEY_VIS_DOC_LICENCE,vis.getDocLicence());
						values.put(SQLNAME.KEY_VIS_DOC_OPINIONS,opinion);
						db1.update(SQLNAME.TABLE_VISIT, values,
								SQLNAME.KEY_VIS_NUM+ " = ?", new String[] {String.valueOf(visNum)});
						db.close();
						return;					
					}
				} while (cursor.moveToNext());
			}						
		}
        //-------------------------------------------------------------------------------

		@Override
		//Returns medicine if it exists
		public Medicine getMedByNum(long num) throws Exception {
			// TODO Auto-generated method stub
			
			try {
				for (Medicine m : this.getMedicineList()) {
					if(m.getMedNum()==num)
						return m;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new Exception("ERROR - Medicine didn't exist");	
		}
        //-------------------------------------------------------------------------------

		@Override
		// Returns illness if it exist
		public Illness getIllByNum(long num)throws Exception{
			// TODO Auto-generated method stub
			try {
				for (Illness p : this.getIllnessList()) {
					if(p.getIllNum()==num)
						return p;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new Exception("ERROR - Illness didn't exist");	
		}
        //-------------------------------------------------------------------------------

		@Override
		// Returns the number of the last visit for the patient with the received id number
		public long getLastVisForPat(long id){
			// TODO Auto-generated method stub
			
			ArrayList<Visit>allVisitsForPat = null ;
			try {
				allVisitsForPat = getVisitsByPatID(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long maxNumVis = 0;
			for (Visit visit : allVisitsForPat) 
			{
				if(visit.getVisNum()>maxNumVis)
					maxNumVis = visit.getVisNum();				
			}
			return maxNumVis;		
			}
        //-------------------------------------------------------------------------------

		@Override
		// Returns patient according to the received id number
		public Patient getPatById(long id)throws Exception {
			// TODO Auto-generated method stub
			
			try {
				for (Patient p : this.getPatientList()) {
					if(p.getPatID()==id)
						return p;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   throw new Exception("ERROR - Patient doesnt exist");
		}
        //-------------------------------------------------------------------------------

		@Override
		// Returns the list of patients
		public ArrayList<Patient> getPatientList() throws Exception {
			// TODO Auto-generated method stub
			ArrayList<Patient> patList = new ArrayList<Patient>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_PATIENT;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

	       if (cursor.moveToFirst()) {
		    do {
			Patient pat = new Patient();
			pat.setPatID(cursor.getLong(0));										
			pat.setPatGender(entities.Gender.valueOf(cursor.getString(1)));
			pat.setPatFirstName(cursor.getString(2));					
			pat.setPatLastName(cursor.getString(3));
			pat.setPatAdress(cursor.getString(4));					
			pat.setPatTelephoneNumber(cursor.getString(5));	
			String timestampToParse = (cursor.getString(6));
		    pat.setPatBirthDate (parseTimestampForBirthDate(timestampToParse));				    
		 	pat.setPatAccount(cursor.getLong(7));					
			pat.setPatMail(cursor.getString(8));					
			patList.add(pat);
		    } while (cursor.moveToNext());		
	          }
	    	   return patList;
	  	
		}
        //-------------------------------------------------------------------------------

		@Override
		//Returns the list of doctors
		public ArrayList<Doctor> getDoctorList() throws Exception {
			ArrayList<Doctor> docList = new ArrayList<Doctor>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_DOCTOR;
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Doctor doc = new Doctor();
				    doc.setDocLicence(cursor.getLong(0));	
					doc.setDocGender(Gender.valueOf(cursor.getString(1)));
					doc.setDocFirstName((String)cursor.getString(2));					
					doc.setDocLastName((String)cursor.getString(3));
					doc.setDocAdress((String)cursor.getString(4));					
					doc.setDocTelephoneNumber(cursor.getString(5));
					doc.setDocExpertise(Expertise.valueOf(cursor.getString(6)));	
					String timestampToParse = (cursor.getString(7));
				    doc.setDocBirthDate (parseTimestampForBirthDate(timestampToParse));			
					doc.setDocMail((String)cursor.getString(8));					
					docList.add(doc);
				} while (cursor.moveToNext());
			}
				return docList;
				
		}
		
	
		
        //-------------------------------------------------------------------------------

		@Override
		// Returns the list of medicines
		public ArrayList<Medicine> getMedicineList() throws Exception {
			// TODO Auto-generated method stub
				
			ArrayList<Medicine> medList = new ArrayList<Medicine>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_MEDICINE;
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Medicine med = new Medicine();
				    med.setMedNum(cursor.getLong(0));						
					med.setMedName((String)cursor.getString(1));							
					medList.add(med);
				} while (cursor.moveToNext());
			}
				return medList;
				
		}
        //-------------------------------------------------------------------------------

		@Override
		//Returns the list of illnesses
		public ArrayList<Illness> getIllnessList()  throws Exception {
			// TODO Auto-generated method stub
			ArrayList<Illness> illList = new ArrayList<Illness>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_ILLNESS;
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Illness ill = new Illness();
				    ill.setIllNum(cursor.getLong(0));						
					ill.setIllName((String)cursor.getString(1));
					ill.setIllDescription((String)cursor.getString(2));							

					illList.add(ill);
				} while (cursor.moveToNext());
			}
				return illList;
			
			}
        //-------------------------------------------------------------------------------

		@Override
		// Returns the list of passwords
		public ArrayList<Password> getPasswordsList() throws Exception {
			// TODO Auto-generated method stub
			
				ArrayList<Password> pasList = new ArrayList<Password>();
				String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_PASSWORD;
				SQLiteDatabase db = this.getReadableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);
				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						Password pas = new Password();
						pas.setDocLicence(cursor.getLong(0));
						pas.setDocPassword((String)cursor.getString(1));
						pasList.add(pas);
					} while (cursor.moveToNext());
				}

				return pasList;
			}
		
        //-------------------------------------------------------------------------------

		@Override
		// Returns the list of Illness-Visit
		public ArrayList<IllnessVisit> getIllnessVisitList() throws Exception {
			// TODO Auto-generated method stub
			ArrayList<IllnessVisit> illVisList = new ArrayList<IllnessVisit>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_ILLNESS_VISIT;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					IllnessVisit illVis = new IllnessVisit();
					illVis.setVisNum(cursor.getLong(0));						
					illVis.setIllNum(cursor.getLong(1));

					illVisList.add(illVis);
				} while (cursor.moveToNext());
			}

			return illVisList;			
			}
        //-------------------------------------------------------------------------------

		@Override
		// Returns the list of Medicine-Visit
		public ArrayList<MedicineVisit> getMedicineVisitList() throws Exception {
			// TODO Auto-generated method stub
			ArrayList<MedicineVisit> medVisList = new ArrayList<MedicineVisit>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_MEDICINE_VISIT;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					MedicineVisit medVis = new MedicineVisit();
					medVis .setVisNum(cursor.getLong(0));						
					medVis.setMedNum(cursor.getLong(1));

					medVisList.add(medVis);
				} while (cursor.moveToNext());
			}

			return medVisList;			
			}
        //-------------------------------------------------------------------------------
		@Override
		// Receives id of patient and returns all the illnesses in the history of the patient (if there is some)
		public ArrayList<Illness> getHistoricIllnessesForPat(long id)
				throws Exception {
			// TODO Auto-generated method stub
			
			ArrayList<Illness>ill = new ArrayList<Illness>();
			if (getIllnessVisitList().size()==0)
				throw new Exception
				   ("Error");
			
			for (Visit item : getVisitsByPatID(id)) // מעבר על כל ביקורי הפציינט
			{
				for (IllnessVisit illVis : getIllnessVisitList()) // עבור כל ביקור, עוברים על רשימת ביקור-מחלה, ומחפשים אותו שם
				{
					if(item.getVisNum()==illVis.getVisNum())
						ill.add(getIllByNum(illVis.getIllNum()));					
				}
			}
				return ill;
			
			}
		
        //-------------------------------------------------------------------------------
		@Override
		// Receives id of patient and returns all the medicines in the history of the patient (if there is some)
		public ArrayList<Medicine> getHistoricMedicinesForPat(long id)
				throws Exception {
			// TODO Auto-generated method stub
			
			ArrayList<Medicine>meds = new ArrayList<Medicine>();
			if (getMedicineVisitList().size()==0)
				throw new Exception
				   ("Error");
			
			for (Visit item : getVisitsByPatID(id)) // מעבר על כל ביקורי הפציינט
			{
				for (MedicineVisit medvis :getMedicineVisitList()) // עבור כל ביקור, עוברים על רשימת ביקור-תרופה, ומחפשים אותו שם
				{
					if(item.getVisNum()==medvis.getVisNum())
						meds.add(getMedByNum(medvis.getMedNum()));					
				}
			}
				return meds;
		
		}
        //-------------------------------------------------------------------------------

		@Override
		// Receives number of visit and returns the illnesses for that visit (if there is some)
		public ArrayList<Illness> getIllnessesForVisit(long numVis)
				throws Exception {
			// TODO Auto-generated method stub
			ArrayList<Illness>ills = new ArrayList<Illness>();
			for (IllnessVisit illVis : this.getIllnessVisitList()) // עבור כל ביקור, עוברים על רשימת ביקור-מחלה, ומחפשים אותו שם			
					if(illVis.getVisNum()==numVis)
						ills.add(getIllByNum(illVis.getIllNum()));					
			
			return ills;
		}
        //-------------------------------------------------------------------------------

		@Override
		// Receives number of visit and returns the medicines for that visit (if there is some)
		public ArrayList<Medicine> getMedicinesForVisit(long numVis)
				throws Exception {
			// TODO Auto-generated method stub
			ArrayList<Medicine>meds = new ArrayList<Medicine>();
			for (MedicineVisit medVis : this.getMedicineVisitList()) // עבור כל ביקור, עוברים על רשימת ביקור-תרופה, ומחפשים אותו שם
					if(medVis.getVisNum()==numVis)
						meds.add(getMedByNum(medVis.getMedNum()));					
			
			return meds;
		}
        //-------------------------------------------------------------------------------

		@Override
		// Returns all the visits for a doctor according to his licence number
		public ArrayList<Visit> getVisitsByDocLicence(long licence)
				throws Exception {
			// TODO Auto-generated method stub
			
			ArrayList<Visit> visList = new ArrayList<Visit>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_VISIT;
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Visit vis = new Visit();
				    vis.setVisNum(cursor.getLong(0));
				    String timestampToParse = (cursor.getString(1));
				    vis.setVisDate ((parseTimestampForVis(timestampToParse)));
					vis.setPatID(cursor.getLong(2));					
					vis.setDocLicence(cursor.getLong(3));
					vis.setDocOpinions((String)cursor.getString(4));					
					if(vis.getDocLicence()==licence)			
						visList.add(vis);
				} while (cursor.moveToNext());
			}				
		
				   return visList;
				
		}
        //-------------------------------------------------------------------------------

		@Override
		// Returns all the visit for an specific doctor in a specific date (if there is any)
		public ArrayList<Visit> getVisitsByDocLicenceAndDate(long licence,
				int year, int month, int day) throws Exception {
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
        //-------------------------------------------------------------------------------

		@Override
		// Returns all the visit for a patient according with the received ID number
		public ArrayList<Visit> getVisitsByPatID(long id) throws Exception {
			// TODO Auto-generated method stub		
			ArrayList<Visit> visList = new ArrayList<Visit>();
			String selectQuery = "SELECT  * FROM " + SQLNAME.TABLE_VISIT;
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Visit vis = new Visit();
				    vis.setVisNum(cursor.getLong(0));
				    String timestampToParse = (cursor.getString(1));
				    vis.setVisDate ((parseTimestampForVis(timestampToParse)));
				    vis.setPatID(cursor.getLong(2));					
					vis.setDocLicence(cursor.getLong(3));
					vis.setDocOpinions((String)cursor.getString(4));					
					if(vis.getPatID()==id)			
						visList.add(vis);
				} while (cursor.moveToNext());
			}				
			return visList;	
		}

        //-------------------------------------------------------------------------------
        @Override
        // Sets data in all the lists
		public void setLists() {
			try {
			this.addPassword(new Password(1234,"12345"));
			this.addPassword(new Password(1235,"12356"));
		
			this.addDoc(new Doctor(1234, Gender.Male, "Ehud", "Malik", "Chchav yaacov","052546789",Expertise.cardiologist, new GregorianCalendar(1990,11,5), "eeee@docMail"));
			this.addPat(new Patient(1,"Michal",Gender.Female,"Malik", new GregorianCalendar(1980,11,5),"Cochav Yaakov","0545348536",5555,"michalush17@gmail.com"));
			this.addPat(new Patient(2,"Michal",Gender.Female,"Malik",parseTimestampForBirthDate("11/25/1995"),"Cochav Yaakov","0545348536",5555,"michalush17@gmail.com"));
			this.addPat(new Patient(3,"Michal",Gender.Female,"Malik",new GregorianCalendar(1994,12,01),"Cochav Yaakov","0545348536",5555,"michalush17@gmail.com"));

			this.addPat(new Patient(204308803,"Michal",Gender.Female,"Malik",parseTimestampForBirthDate("05/06/1994"),"Cochav Yaakov","0545348536",5555,"michalush17@gmail.com"));
			this.addVis(new Visit (new GregorianCalendar(2014,12,8,11,30),204308803,1234,"I am tired"));
			this.addVis(new Visit (new GregorianCalendar(2014,12,8,12,30),204308803,1234,"I am tired"));
			this.addIll(new Illness(12345678, "head acke", "bla bla" ));
			this.addIll(new Illness(11111111, "eyes acke", "bla bla bla" ));
			this.addIll(new Illness (22222222,"stomach acke", "blablabla"));
			this.addIll(new Illness (33333333, "throat pain", "drink water"));
			this.addMed(new Medicine(10000, "Acamol"));
			this.addMed(new Medicine(20000, "Optalgin"));
			this.addMed(new Medicine(30000, "Antibiotic"));
			this.addMed(new Medicine(40000, "Dorixin"));
			this.addMedToVis(1,10000);
			this.addMedToVis(2,20000);
			this.addMedToVis(1,40000);
			this.addIllToVis(1,11111111);
			this.addIllToVis(2,22222222);
			
			  } catch (Exception e) {
					e.printStackTrace();
				  }
			
		}

        //-------------------------------------------------------------------------------
        public static Calendar parseTimestampForBirthDate(String timestamp)
        	    throws Exception {
        	   /*
        	   ** we specify Locale.US since months are in english
        	   */
        	   SimpleDateFormat sdf = new SimpleDateFormat
        	     ("MM/dd/yyyy", Locale.US);
        	   Date d = sdf.parse(timestamp);
        	   Calendar cal = Calendar.getInstance();
        	   cal.setTime(d);
        	   return cal;
        	 }
        //-------------------------------------------------------------------------------
        public static Calendar parseTimestampForVis(String timestamp)
        	    throws Exception {
        	   /*
        	   ** we specify Locale.US since months are in english
        	   */
        	   SimpleDateFormat sdf = new SimpleDateFormat
        	     ("MM/dd/yyyy HH:mm", Locale.US);
        	   Date d = sdf.parse(timestamp);
        	   Calendar cal = Calendar.getInstance();
        	   cal.setTime(d);
        	   return cal;
        	 }
        //-------------------------------------------------------------------------------
        
    	
}
