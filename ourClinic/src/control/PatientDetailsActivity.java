package control;

import model.backend.BackendFactory;

import com.example.ourclinic.R;

import entities.Patient;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class PatientDetailsActivity extends Fragment {
    Context context = null;
    ProgressDialog progressDialog;
	long patId;
	View view;
	Patient p ;
	public PatientDetailsActivity(long id) {
		// TODO Auto-generated constructor stub	
		patId=id;
	}

	public  View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState)
	{	
	    context = this.getActivity();
		view = inflator.inflate(R.layout.activity_patient_details, container, false);		
		
		try {
			myAsyncTask t1 = new myAsyncTask((Activity) context, progressDialog, new myFunc() {						
			@Override
			public void run() throws Exception // רץ ברקע
			{    // ניגש למסד הנתונים על מנת לשאוב את פרטי הפציינט על פי מספר זהות
			    p= BackendFactory.getInstance(context).getPatById(patId);
			}
		    }, new myFunc() {
			
			@Override
			public void run() // רץ בסיום התהליך
			{   // ממראה את פרטי הפציינט בחלון
				TextView id = (TextView) view.findViewById(R.id.patId_textView1);	    
				id.setText(String.valueOf(patId));
				
				TextView firstName= (TextView) view.findViewById(R.id.patFirstName_textView1);
				firstName.setText(p.getPatFirstName());
				
				TextView lastName= (TextView) view.findViewById(R.id.patLastName_textView1);
				lastName.setText(p.getPatLastName());
				
				TextView email= (TextView) view.findViewById(R.id.patMail_textView1);
				email.setText(p.getPatMail());
				
				TextView bankAccount= (TextView) view.findViewById(R.id.patBankAccount_textView1);
				bankAccount.setText(String.valueOf(p.getPatAccount()));
				
				TextView telephoneNum= (TextView) view.findViewById(R.id.patTelephone_textView1);
				telephoneNum.setText(String.valueOf(p.getPatTelephoneNumber()));
				
				TextView birthDate= (TextView) view.findViewById(R.id.patBirthDate_textView1);
				birthDate.setText((p.getDateAsString()));
		        
				TextView gender= (TextView) view.findViewById(R.id.patGender_textView1);
				gender.setText(String.valueOf(p.getPatGender()));
				
				TextView address= (TextView) view.findViewById(R.id.patAddress_textView1);
				address.setText(String.valueOf(p.getPatAdress()));
											
			}

		    }
		    
					);
			// הרצת התהליך
			t1.execute();
		} 
		catch (Exception e) 
		{
		    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();// כאשר אין שום סיסמאות במערכת....

		}		
		
		return view;		
	}		
		
}
