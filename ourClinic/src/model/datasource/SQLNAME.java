package model.datasource;

public class SQLNAME {		
		
	
	// All Static variables
	// Database Version
	public static final int DATABASE_VERSION = 1;
	
	// Database Name
	
	public static final String DATABASE_NAME = "our_clinic.db";
	
	// Tables names
	
	public static final String TABLE_PASSWORD = "password_table";
	public static final String TABLE_DOCTOR = "doctor_table";
	public static final String TABLE_PATIENT = "patient_table";
	public static final String TABLE_MEDICINE = "medicine_table";
	public static final String TABLE_ILLNESS = "illness_table";
	public static final String TABLE_VISIT = "visit_table";
	public static final String TABLE_MEDICINE_VISIT = "medicine_visit_table";
	public static final String TABLE_ILLNESS_VISIT = "illness_visit_table";
	
	// Password table - column names
	
	public static final String KEY_P_DOC_LICENCE = "doctor_licence";
	public static final String KEY_P_PASSWORD = "password";
	
	// Doctor table - column names

	public static final String KEY_DOC_LICENCE = "doctor_licence";
	public static final String KEY_DOC_FIRST_NAME = "doctor_fname";
	public static final String KEY_DOC_LAST_NAME = "doctor_lname";
	public static final String KEY_DOC_GENDER = "doctor_gender";
	public static final String KEY_DOC_PHONE = "doc_phone_number";
	public static final String KEY_DOC_ADDRESS = "doc_address";
	public static final String KEY_DOC_EXPERTISE = "doctor_expertise";
	public static final String KEY_DOC_BIRTH_DATE = "doctor_birth_date";
	public static final String KEY_DOC_MAIL = "doctor_mail";
   

	// Patient table  - column names
	
	public static final String KEY_PAT_ID = "patient_id";
	public static final String KEY_PAT_GENDER = "patient_gender";
	public static final String KEY_PAT_FIRST_NAME = "patient_fname";
	public static final String KEY_PAT_LAST_NAME = "patient_lname";
	public static final String KEY_PAT_ADDRESS = "patient_address";
	public static final String KEY_PAT_PHONE = "patient_phone_number";
	public static final String KEY_PAT_BIRTH_DATE = "patient_birth_date";
	public static final String KEY_PAT_ACCOUNT = "patient_account";
	public static final String KEY_PAT_MAIL = "patient_mail";
	

	// Medicine table  - column names
	
	public static final String KEY_MED_NUM = "medicine_num";
	public static final String KEY_MED_NAME = "medicine_name";


	// Illness table  - column names
	
	public static final String KEY_ILL_NUM = "illness_num";
	public static final String KEY_ILL_NAME = "illness_name";
	public static final String KEY_ILL_DESCRIPTION = "illness_decription";
	
	// Visit table - column names
	
	public static final String KEY_VIS_NUM = "visit_num";
	public static final String KEY_VIS_DATE = "visit_date";
	public static final String KEY_VIS_PAT_ID= "visit_pat_id";
	public static final String KEY_VIS_DOC_LICENCE = "visit_doc_licence";
	public static final String KEY_VIS_DOC_OPINIONS = "visit_doc_opinions";
	
	// Medicine-visit - column tables
	
	public static final String KEY_MEDVIS_VIS_NUM = "medvis_visnum";
	public static final String KEY_MEDVIS_MED_NUM = "medvis_mednum";
	
	// Illness-visit - column tables
	
	public static final String KEY_ILLVIS_VIS_NUM = "illvis_visnum";
	public static final String KEY_ILLVIS_ILL_NUM = "illvis_illnum";

	}


