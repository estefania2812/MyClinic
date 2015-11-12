package control;


import java.util.GregorianCalendar;

import model.backend.BackendFactory;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import entities.Gender;
import entities.Illness;
import entities.Medicine;
import entities.Password;
import entities.Patient;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	ProgressDialog progressDialog;
	//====================================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// כפתור "המשך
		Button b = (Button) findViewById(R.id.mainContinue_button);
		// בעת לחיצה עליו- עובר לחלון כניסה עבור רופא
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
// יוצרים תהליך שירוץ ברקע
myAsyncTask t1 = new myAsyncTask(MainActivity.this,
		progressDialog, new myFunc() {

			@Override
			public void run() throws Exception // רץ ברקע
			{
					if (BackendFactory.getInstance(getApplicationContext()).getPasswordsList().size()==0)
						BackendFactory.getInstance(getApplicationContext()).setLists();
			 		 // Toast.makeText(getApplication(),BackendFactory.getInstance(getApplicationContext()).getPatientList().size(), Toast.LENGTH_SHORT).show();//					
					
					
			}
		}, new myFunc() {

								@Override
								public void run() // רץ בסיום התהליך
								{
									Intent intent = new Intent(MainActivity.this,DoctorLoginActivity.class);
									startActivity(intent);
								}
							});

					// הרצת התהליך
					t1.execute();

				} catch (Exception e) {
			 		  Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

				}

			}
		});
	}
	//====================================================================================

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
