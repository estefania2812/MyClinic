package Data;

import java.util.Calendar;
import java.util.Date;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class CustomTimePicker extends TextView {
	public CustomTimePicker(Context context) {
		super(context);
		init();
	}

	public CustomTimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomTimePicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	Calendar calendar;

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setDate(Date date) {
		calendar.setTime(date);
		int chour = calendar.get(Calendar.HOUR_OF_DAY);
		int cminute = calendar.get(Calendar.MINUTE);
		if (cminute < 10)
			this.setText(chour + ":0" + cminute);
		else
			this.setText(chour + ":" + cminute);
	}

	private void initOnClick() {
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Context context = CustomTimePicker.this.getContext();
				TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						calendar.set(0, 0, 0, hourOfDay, minute);
						if (minute < 10)
							CustomTimePicker.this.setText(hourOfDay + ":0" + minute);
						else
							CustomTimePicker.this.setText(hourOfDay + ":" + minute);
					}
				};
				int chour = calendar.get(Calendar.HOUR_OF_DAY);
				int cminute = calendar.get(Calendar.MINUTE);
				TimePickerDialog tpd = new TimePickerDialog(context, callBack,
						chour, cminute, true);
				tpd.show();
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
