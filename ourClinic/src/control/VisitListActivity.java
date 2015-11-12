package control;

import java.util.ArrayList;
import java.util.Calendar;

import model.backend.BackendFactory;

import com.example.ourclinic.R;

import entities.Visit;
import android.R.integer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

public class VisitListActivity extends FragmentActivity {
	final Context context = this;
    ProgressDialog progressDialog;
	ArrayList<Visit>visits= new ArrayList<Visit>();
	FragmentManager fm = getFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();		
	VisitListViewDemoFragment fragment1 = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        final long licence =Long.valueOf((String)getIntent().getSerializableExtra("doc_licence")) ;
        final int year =((Integer)getIntent().getSerializableExtra("year")) ;
        final int month =((Integer)getIntent().getSerializableExtra("month")) ;
        final int day =((Integer)getIntent().getSerializableExtra("day")) ;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit_list);			
		try {
			myAsyncTask t1 = new myAsyncTask(VisitListActivity.this, progressDialog, new myFunc() {						
			@Override
			public void run() throws Exception // רץ ברקע
			{ 
				visits =BackendFactory.getInstance(getApplicationContext()).getVisitsByDocLicenceAndDate(licence,year,month,day); 
			}
		    }, new myFunc() {
			
			@Override
			public void run() // רץ בסיום התהליך
			{	
				fragment1 = new VisitListViewDemoFragment(visits);		
				fragment1.setRetainInstance(true);
				transaction.replace(R.id.visitsss_layout, fragment1);
				transaction.commit();						
			}
		    });
			// הרצת התהליך
			t1.execute();
		} 
		catch (Exception e) {
			  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();// כאשר אין שום סיסמאות במערכת....

		}		
	}

}
