package control;

import java.util.ArrayList;

import model.backend.BackendFactory;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import entities.Illness;
import entities.Medicine;
import entities.Visit;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PatientInfoActivity extends FragmentActivity {
    ProgressDialog progressDialog;
    ArrayList<Visit>visits = new ArrayList<Visit>();
    ArrayList<Medicine>meds = new ArrayList<Medicine>();
    ArrayList<Illness> ills = new ArrayList<Illness>();
    String licence ;
    String id ;
  
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_info);
		

		// ----------------------------------------------------הצגת מספר רישיון הרופא ומספר פציינט
				
		       licence = (String)getIntent().getSerializableExtra("licence_doc");
		       id = (String)getIntent().getSerializableExtra("id_pat");

				final TextView licenceDoc= (TextView)findViewById( R.id.logedDoc_textView1);
				licenceDoc.setText(licence);
				final TextView idPat= (TextView)findViewById( R.id.treatedPatient_textView1);
				idPat.setText(id);
				Button b = (Button)findViewById( R.id.addVis_button );
				b.setOnClickListener(new OnClickListener()
				   {
				             @Override
				             public void onClick(View v)
				             {
				            	Intent intent = new Intent(v.getContext(), VisitActivity.class);
				            	intent.putExtra("id_pat", id);				            	
				            	intent.putExtra("licence_doc", licence);
				 				startActivity(intent);				 				
				             } 
				   }); 
				// מראה את פרטי הפציינט
				View v = (View)findViewById(R.id.patientDetails_RadioButton);
				onSelectedButton(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_info, menu);
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
	
	public void onSelectedButton(View view)
	{
		String licence = (String)getIntent().getSerializableExtra("licence_doc");
		final String id = (String)getIntent().getSerializableExtra("id_pat");		
		//Show Details of patient==========================================
		if (view == findViewById(R.id.patientDetails_RadioButton)) {
			android.app.FragmentManager fm = getFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			PatientDetailsActivity patDetails = new PatientDetailsActivity(Long.valueOf(id));
			transaction.replace(R.id.second_layout, patDetails);
			transaction.commit();			
		} 
		//Show historic visits of patient===================================

		if (view == findViewById(R.id.HistoricVisitsForPat_RadioButton)) {

			 ActivityCompat.invalidateOptionsMenu(this);	
			 try {
					myAsyncTask t1 = new myAsyncTask(PatientInfoActivity.this, progressDialog, new myFunc() {						
					@Override
					public void run() throws Exception // רץ ברקע
					{  
						visits = BackendFactory.getInstance(getApplicationContext()).getVisitsByPatID(Long.valueOf(id));
					}
				    }, new myFunc() {
					
					@Override
					public void run() // רץ בסיום התהליך
					{	
						android.app.FragmentManager fm1 = getFragmentManager();
					    FragmentTransaction transaction1 = fm1.beginTransaction();
					    VisitListViewDemoFragment fragment1 = new VisitListViewDemoFragment(visits);								
						fragment1.setRetainInstance(true);
						transaction1.replace(R.id.second_layout, fragment1);
						transaction1.commit();			
					}							
				    });
					// הרצת התהליך
					t1.execute();
				    } 
				catch (Exception e) {
						  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

				}	
		} 
		//Show historic medicines of patient==========================================================================
				if (view == findViewById(R.id.HistoricMedicinesForPat_RadioButton)) {
					 ActivityCompat.invalidateOptionsMenu(this);	
					 try {
							myAsyncTask t1 = new myAsyncTask(PatientInfoActivity.this, progressDialog, new myFunc() {						
							@Override
							public void run() throws Exception // רץ ברקע
							{  
								meds = BackendFactory.getInstance(getApplicationContext()).getHistoricMedicinesForPat(Long.valueOf(id));
							}
						    }, new myFunc() {
							
							@Override
							public void run() // רץ בסיום התהליך
							{	
								android.app.FragmentManager fm1 = getFragmentManager();
							    FragmentTransaction transaction1 = fm1.beginTransaction();
							    MedicineListViewDemoFragment fragment1 = new MedicineListViewDemoFragment(meds);								
								fragment1.setRetainInstance(true);
								transaction1.replace(R.id.second_layout, fragment1);
								transaction1.commit();			
							}							
						    });
							// הרצת התהליך
							t1.execute();
						    } 
						catch (Exception e) {
								  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

						}	
				} 		
		//Show historic illnesses of patient======================================================================
				
				if (view == findViewById(R.id.HistoricIllnessesForPat_RadioButton)) {
                    ActivityCompat.invalidateOptionsMenu(this);	
					 try {
							myAsyncTask t1 = new myAsyncTask(PatientInfoActivity.this, progressDialog, new myFunc() {						
							@Override
							public void run() throws Exception // רץ ברקע
							{  
								ills = BackendFactory.getInstance(getApplicationContext()).getHistoricIllnessesForPat(Long.valueOf(id));
							}
						    }, new myFunc() {
							
							@Override
							public void run() // רץ בסיום התהליך
							{	
								android.app.FragmentManager fm1 = getFragmentManager();
							    FragmentTransaction transaction1 = fm1.beginTransaction();
							    IllnessesListViewDemoFragment fragment1 = new IllnessesListViewDemoFragment(ills);								
								fragment1.setRetainInstance(true);
								transaction1.replace(R.id.second_layout, fragment1);
								transaction1.commit();			
							}							
						    });
							// הרצת התהליך
							t1.execute();
						    } 
						catch (Exception e) {
								  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

						}	
				} 		
	}
	
	
	
}
