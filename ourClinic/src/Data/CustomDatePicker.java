package Data;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class CustomDatePicker extends TextView {
	public CustomDatePicker(Context context) {
		super(context);
		init();
	}

	public CustomDatePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomDatePicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	Calendar calendar;

	public void setDate(Date date) {
		calendar.setTime(date);
		int cyear = calendar.get(Calendar.YEAR);
		int cmonth = calendar.get(Calendar.MONTH);
		int cday = calendar.get(Calendar.DAY_OF_MONTH);
		this.setText(cday + "/" + (cmonth + 1) + "/" + cyear);
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	private void initOnClick() {
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Context context = CustomDatePicker.this.getContext();
				DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						calendar.set(year, monthOfYear, dayOfMonth, 0, 0);
						CustomDatePicker.this.setText(dayOfMonth + "/"
								+ (monthOfYear + 1) + "/" + year);
					}
				};
				int cyear = calendar.get(Calendar.YEAR);
				int cmonth = calendar.get(Calendar.MONTH);
				int cday = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(context, callBack,
						cyear, cmonth, cday);
				dpd.show();
			}
		});
	}

	private void init() {
		calendar = Calendar.getInstance();
		this.setFocusable(false);
		setDate(calendar.getTime());
		initOnClick();
	}
}
