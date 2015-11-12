package control;

import java.util.ArrayList;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import model.backend.BackendFactory;
import model.datasource.DatabaseList;
import entities.Password;
import entities.Patient;
import android.R.bool;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DoctorLoginActivity extends ActionBarActivity {
	ProgressDialog progressDialog;
	ArrayList<Password> passwords= new ArrayList<Password>();// �� ���� ����� �� ��������

	//====================================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_login);

		Button docLoginButton = (Button)findViewById( R.id.docLogin_button );
		// ��� ����� �� ������ "����" ,���� �� ����� ������
		docLoginButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				// ����� ���� ������� ������ �����
				final EditText docLicence = (EditText)findViewById( R.id.docLicence_editText );
				final EditText docPassword = (EditText)findViewById( R.id.docPassword_editText );
				
				 try {
				        myAsyncTask t1 = new myAsyncTask(DoctorLoginActivity.this, progressDialog, new myFunc() {						
						@Override
						public void run() throws Exception // �� ����
						{    // ���� ���� �� ����� �������� �� �����
			            	 passwords = BackendFactory.getInstance(getApplicationContext()).getPasswordsList();   
						}
					    }, new myFunc() {
						
						@Override
						public void run() // �� ����� ������
						{		
							 boolean isExist= false;// ����� ����� �� ��� ���� ���� ��� �� ����� ����
					            for (Password i :passwords)
				                {
							        //����� ����� ����� ����
				                	if(i.getDocLicence() ==Long.parseLong(docLicence.getText().toString()) &&
				                			i.getDocPassword().equals(docPassword.getText().toString()))
				                	{
				                		 Intent intent = new Intent(DoctorLoginActivity.this, MenuForDocActivity.class);
				                		 intent.putExtra("Doctor_licence",docLicence.getText().toString());
				         				 startActivity(intent);// ���� ����� �� ����� �����
				         				 isExist = true;    
				                	}         	
				                }	           
				                if(isExist==false)// ����� ����� ���� �� �����,���� �� ������� �� �����, ���� ����� �����
				                {
				                 docLicence.setText(""); 
				                 docPassword.setText("");
						 		 Toast.makeText(getApplication(), "* ERROR! please check your details again", Toast.LENGTH_SHORT).show();
				                }															
						}
					    });
						// ���� ������
						t1.execute();
					} catch (Exception e) {
				 		  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();// ���� ��� ��� ������� ������....

					}
				 
				 
				 
				}
			});
		}
	//====================================================================================

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_login, menu);
		return true;
	}
	//====================================================================================

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
}













