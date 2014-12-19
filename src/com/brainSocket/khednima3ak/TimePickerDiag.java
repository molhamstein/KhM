package com.brainSocket.khednima3ak;

import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerDiag extends DialogFragment {

	private static final int maxHours = 2;
	private static final int maxMinutes = 59;
	public String titlePrefix = "";

	TimePickerDialog.OnTimeSetListener callback;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = 0;// c.get(Calendar.HOUR_OF_DAY);
		int minute = 0;// c.get(Calendar.MINUTE);

		TimePickerDialog diag = new DelayPickerDialog(getActivity(), callback,
				hour, minute, true);
		titlePrefix = getActivity()
				.getString(R.string.choose_time_dialog_title);
		diag.setTitle(titlePrefix);

		// TimePicker timePicker = (TimePicker) diag.findViewById(R.id.d) ;

		// Create a new instance of TimePickerDialog and return it
		return diag;
	}

	/*
	 * public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	 * callback. }
	 */
	public void setonTimePickedCallback(
			TimePickerDialog.OnTimeSetListener callback) {
		this.callback = callback;
	}

	private class DelayPickerDialog extends TimePickerDialog {

		public DelayPickerDialog(Context context, OnTimeSetListener callBack,
				int hourOfDay, int minute, boolean is24HourView) {

			super(context, android.R.style.Theme_Holo_Dialog_NoActionBar,
					callBack, hourOfDay, minute, is24HourView);
		}

		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			if (hourOfDay > maxHours) {
				hourOfDay = 0;
				minute = 0;
				updateTime(hourOfDay, minute);
				Toast.makeText(getActivity(), getActivity().getString(R.string.warning_max_sched_time), Toast.LENGTH_SHORT).show();
			}
			setTitle(titlePrefix + " " + hourOfDay + ":" + minute);
			super.onTimeChanged(view, hourOfDay, minute);
		}

		@Override
		public void updateTime(int hourOfDay, int minutOfHour) {
			if (hourOfDay <= maxHours) {
				super.updateTime(hourOfDay, minutOfHour);
			}
		}
	}

}
