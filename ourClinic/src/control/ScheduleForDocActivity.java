package control;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.backend.BackendFactory;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

public class ScheduleForDocActivity extends ActionBarActivity {

	final Context context = this;
	private int mYear;
	private int mMonth;
	private int mDay;
	long licence;
	@Override
	public void onCreate(Bundle savedInstanceState) {
     licence =Long.valueOf((String)getIntent().getSerializableExtra("doc_licence")) ;

	super.onCreate(savedInstanceState);
	CalendarView calendar = new CalendarView(this);
	calendar.setOnDateChangeListener(mDateSetListener);
	setContentView(calendar);
	}	 
	// the callback received when the user "sets" the date in the dialog
	private CalendarView.OnDateChangeListener mDateSetListener =
	new CalendarView.OnDateChangeListener() {
	public void onSelectedDayChange(CalendarView view, int year,// בעת לחיצה על תאריך, נפתח לרופא את כל ביקוריו באותו תאריך
	int monthOfYear, int dayOfMonth) {
	mYear = year;
	mMonth = monthOfYear;
	mDay = dayOfMonth;
	// פותח את כל ביקורי הרופא עבור אותו תאריך
	Intent intent = new Intent(ScheduleForDocActivity.this, VisitListActivity.class);
	intent.putExtra("doc_licence",String.valueOf(licence));//העברת מספר רופא עמ"נ שנוכל לראות רק את הביקורים שלו
	intent.putExtra("year", mYear);//העברת התאריך הנבחר
	intent.putExtra("month", mMonth);
	intent.putExtra("day", mDay);

	startActivity(intent);														
						
	}
	};

	
}
