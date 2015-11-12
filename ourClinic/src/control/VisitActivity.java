package control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.backend.BackendFactory;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import entities.Illness;
import entities.Medicine;
import entities.Password;
import entities.Visit;
import Data.CustomDatePicker;
import Data.CustomTimePicker;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VisitActivity extends FragmentActivity {
	final Context context = this;
	long numVis;
    ProgressDialog progressDialog;
    EditText opinion ;
    ArrayList<Medicine>meds =  new ArrayList<Medicine>();
    ArrayList<Illness>ills = new ArrayList<Illness>();
   
    //========================================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit);
		
		// ������ �� ��� ����� ����� ��� �� ������ ����� ������� ��.� �� �������,����� ����
		final String id = (String)getIntent().getSerializableExtra("id_pat");	
		final String licence = (String)getIntent().getSerializableExtra("licence_doc");
		
		CustomDatePicker date = (CustomDatePicker)findViewById( R.id.visDate_customDatePicker );
		CustomTimePicker time = (CustomTimePicker)findViewById( R.id.visTime_customTimePicker );
       
	    Calendar date1 = date.getCalendar();
	    Calendar time1 = time.getCalendar();

	    final int year = date1.get(Calendar.YEAR);
	    final int month = date1.get(Calendar.MONTH);
	    final int day = date1.get(Calendar.DAY_OF_MONTH);
	    final int hour = time1.get(Calendar.HOUR_OF_DAY);
	    final int minute = time1.get(Calendar.MINUTE);
		
	    // ����� ����� ��� ���� �������, �� ��� ������� ��� ��� ������ ��� ����� ������ ������ ������ ������ ����.
	    // ���� ��� ����� ��� ������ �� ������� , �� ��� ������ ���� �����:����� ����� �� ������
	    try {
			myAsyncTask t1 = new myAsyncTask(VisitActivity.this, progressDialog, new myFunc() {						
			@Override
			public void run() throws Exception // �� ����
			{   
				// ����� ����� ��� ���� �������
				BackendFactory.getInstance(getApplicationContext()).addVis(new Visit (new GregorianCalendar(year,month,day,hour,minute),Long.valueOf(id),Long.valueOf(licence),""));
				// ���� �� ���� ������ �� ��� ����� ��� ������
				numVis = BackendFactory.getInstance(getApplicationContext()).getLastVisForPat(Long.valueOf(id));
				ills = BackendFactory.getInstance(getApplicationContext()).getIllnessList();
				meds = BackendFactory.getInstance(getApplicationContext()).getMedicineList();

			}
		    }, new myFunc() {
			
			@Override
			public void run() // �� ����� ������
			{					
		        // ���� ������ �������� �� ���� ������
		        TextView visNum= (TextView)findViewById( R.id.visNum_textView1 );
		        visNum.setText(String.valueOf(numVis));	
				TextView visPat= (TextView)findViewById( R.id.visPatID_textView );
				visPat.setText(id);		
				TextView visDoc= (TextView)findViewById( R.id.visDocLicence_textView);
				visDoc.setText(licence);
				View v = (View)findViewById(R.id.addIlls_RadioButton);
				onSelectedButton(v);
			}
		    });
			// ���� ������
			t1.execute();
		} 
		catch (Exception e) {
				 Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();// ���� ��� ��� ������� ������....

		}	
	    //========================================================================================
		
		//  ��� ����� �� ����� ��, ��� ����� �� ������ ������ ���� �������, �"� �������� ������ ����� ����
		Button saveVis = (Button)findViewById( R.id.saveVis_button );
		saveVis.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				// ����� ������� ������ ������ ���� �� ����
				opinion = (EditText)findViewById( R.id.visOpinion_editText );
				CustomDatePicker date = (CustomDatePicker)findViewById( R.id.visDate_customDatePicker );
				CustomTimePicker time = (CustomTimePicker)findViewById( R.id.visTime_customTimePicker );
		       
			    Calendar date1 = date.getCalendar();
			    Calendar time1 = time.getCalendar();

			    final int year = date1.get(Calendar.YEAR);
			    final int month = date1.get(Calendar.MONTH);
			    final int day = date1.get(Calendar.DAY_OF_MONTH);
			    final int hour = time1.get(Calendar.HOUR_OF_DAY);
			    final int minute = time1.get(Calendar.MINUTE);			    			    
			    try {
				    myAsyncTask t1 = new myAsyncTask(VisitActivity.this, progressDialog, new myFunc() {						
					@Override
					public void run() throws Exception // �� ����
					{   // ����� �� ������ �� �� �������� ������
					    BackendFactory.getInstance(getApplicationContext()).updateVis(numVis, new GregorianCalendar(year,month,day,hour,minute), opinion.getText().toString());
					}
				    }, new myFunc() {
					
					@Override
					public void run() // �� ����� ������
					{		
						 Toast.makeText(getApplication(),"Update", Toast.LENGTH_SHORT).show();// ������ ����� ������ 												
					}
				    });
					// ���� ������
					t1.execute();
				} 
				catch (Exception e) 
			    {
					Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();// ���� ������
			    }
			    }
		}
		);
	    //========================================================================================

	}
    //========================================================================================

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visit, menu);
		return true;
	}
    //========================================================================================

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
    //========================================================================================
    // ��� ����� �� �����- ���� ������ ������ �����. ��� ��� ����� �� ������
	public void onSelectedButton(View view)
	{
		
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
	    
		if(view==findViewById(R.id.addMeds_RadioButton))
		{
			//Show medicine list to add===================================
				MedicinesToAddListViewDemoFragment fragment1 = null;
				ActivityCompat.invalidateOptionsMenu(this);
				try {
					fragment1 = new MedicinesToAddListViewDemoFragment(meds,numVis);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				fragment1.setRetainInstance(true);
				transaction.replace(R.id.second1_layout, fragment1);
				transaction.commit();
				
			} 
			
		if(view==findViewById(R.id.addIlls_RadioButton))
		{
			//Show illness list to add===================================
			IllnesseToAddListViewDemoFragment fragment1 = null;
			ActivityCompat.invalidateOptionsMenu(this);
			try {
				fragment1 = new IllnesseToAddListViewDemoFragment(ills,numVis);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			fragment1.setRetainInstance(true);
			transaction.replace(R.id.second1_layout, fragment1);
			transaction.commit();
		}
		
		
	}
}
