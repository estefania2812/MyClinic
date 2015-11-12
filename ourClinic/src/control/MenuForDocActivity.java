package control;

import java.util.ArrayList;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import model.backend.BackendFactory;
import model.datasource.DatabaseList;
import entities.Patient;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuForDocActivity extends ActionBarActivity {
final Context context = this;
ArrayList<Patient> patients=new  ArrayList<Patient>();
ProgressDialog progressDialog;
boolean error = true;           	



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_for_doc);
		
		// הצגת מספר רישיון הרופא בחלון התפריט
		final String licence = (String)getIntent().getSerializableExtra("Doctor_licence");		
		TextView licenceDoc= (TextView)findViewById( R.id.licenceDocOptions_textView);
		licenceDoc.setText(licence);
		
		//טיפול בלחיצה על :יצפייה בכל ביקורי הרופא-------------------------------------------------------------------

		Button b1 = (Button) findViewById(R.id.showVisitsForDoc_button);
		b1.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MenuForDocActivity.this, ScheduleForDocActivity.class);
				intent.putExtra("doc_licence", licence);//העברת מספר רופא עמ"נ שנוכל לראות רק את הביקורים שלו
				startActivity(intent);
			}
		});
		
		//טיפול בלחיצה על :יצירת טיפול חדש עבור טיפול בפציינט------------------------------------------------------------
		
		Button b = (Button) findViewById(R.id.treatmentPat_button);
		b.setOnClickListener(new View.OnClickListener() {				
		        //When you click the button, Alert dialog will be showed
			@Override
		        public void onClick(View v) {
				
		       /* Alert Dialog Code Start*/     
		            AlertDialog.Builder alert = new AlertDialog.Builder(context);
		            alert.setTitle("Our Clinic"); //Set Alert dialog title here
		            alert.setMessage("Enter ID Of Patient"); //Message here
		 
		            // Set an EditText view to get user input 
		            final EditText input = new EditText(context);
		            alert.setView(input);
		 
		            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		             //You will get as string input data in this variable.
		             // here we convert the input to a string and show in a toast.
		            	
		            	
		            	final String srt = input.getEditableText().toString();
		            	
		            	try {
							myAsyncTask t1 = new myAsyncTask (MenuForDocActivity.this, progressDialog, new myFunc() {						
		            		@Override
		            		public void run() throws Exception // רץ ברקע
		            		{    
				            	 patients = BackendFactory.getInstance(getApplicationContext()).getPatientList();
		            		}
		            	    }, new myFunc() {
		            		
		            		@Override
		            		public void run() throws Exception // רץ בסיום התהליך
		            		{	
		            			try
		            		{
		            			if(!tryParseLong(srt.trim()))// בעת שהלקוח הכניס תווים אחרים מלבד מספרים
					            	 throw new Exception("Enter only numbers");
		            			else
		            				
		            			 for (Patient pat : patients )//  מחפש האם הפציינט קיים במערכת
				            	 {
		  				            	 if(pat.getPatID()==Long.parseLong(srt.trim()))
											{	
										     error = false;							
									         Intent intent = new Intent(MenuForDocActivity.this, PatientInfoActivity.class);		
						                     intent.putExtra("id_pat", srt);
						                     intent.putExtra("licence_doc",licence);
									         startActivity(intent);	
											
											}
							     } 
		            			 if(error == true)
							            Toast.makeText(context,"Patient did not exist",Toast.LENGTH_LONG).show();                
		            		} catch (Exception e) // טעויות שנזרקו בעקבות bhxhi kjhpua vpmhhby gk ph nv avuea
		            			        {
								            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();                
										}				            		 	
		            		     }
		            		
		            	    });
		            		// הרצת התהליך
		            		t1.execute();
		            	} // טעות שמגיעה בעקבות הגישה למסד הנתונים . כמו לשמל שאין שום פציינטים
		            	catch (Exception e) {
		            		 Toast.makeText(getApplication(),e.getMessage(), Toast.LENGTH_SHORT).show();// כאשר אין שום סיסמאות במערכת....
		            	}
		         		            	 		            
		            } // End of onClick(DialogInterface dialog, int whichButton)
		        }); //End of alert.setPositiveButton
		            alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
		              public void onClick(DialogInterface dialog, int whichButton) {
		                // Canceled.
		                  dialog.cancel();
		              }
		        }); //End of alert.setNegativeButton
		            AlertDialog alertDialog = alert.create();
		            alertDialog.show();
		       /* Alert Dialog Code End*/       								
			}
		});
		//----------------------------------------------------------------------------		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_for_doc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	//------------------------------------
	boolean tryParseLong(String value)  
	{  
	     try  
	     {  
	         Long.parseLong(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return false;  
	      }  
	}
	//------------------------------------

}
/*

try {
	    myAsyncTask t1 = new myAsyncTask(DoctorLoginActivity.this, progressDialog, new myFunc() {						
		@Override
		public void run() throws Exception // רץ ברקע
		{    
		}
	    }, new myFunc() {
		
		@Override
		public void run() // רץ בסיום התהליך
		{		
			 												
		}
	    });
		// הרצת התהליך
		t1.execute();
	} 
	catch (Exception e) {
			  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();// כאשר אין שום סיסמאות במערכת....

	}

*/