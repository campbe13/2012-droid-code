package cs534.sample.dialogexample;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogExampleActivity extends Activity implements OnClickListener {

	Button firstDialog;
	Button secondDialog;
	Button thirdDialog, fourthDialog, fifthDialog;

	LinearLayout layout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (LinearLayout) findViewById(R.id.ll);

		firstDialog = (Button) findViewById(R.id.btnFirst);
		secondDialog = (Button) findViewById(R.id.btnSecond);
		thirdDialog = (Button) findViewById(R.id.btnThird);
		fourthDialog = (Button) findViewById(R.id.btn4);
		fifthDialog = (Button) findViewById(R.id.btn5);
		firstDialog.setOnClickListener(this);
		secondDialog.setOnClickListener(this);
		thirdDialog.setOnClickListener(this);
		fourthDialog.setOnClickListener(this);
		fifthDialog.setOnClickListener(this);
		findViewById(R.id.btn6).setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v == firstDialog) {
			showAlertDialog();
		} else if (v == secondDialog) {
			showAlertDialogWList();
		} else if (v == thirdDialog) {
			// Always init the time picker to current time
			Calendar cal1 = Calendar.getInstance();
			int iHour = cal1.get(Calendar.HOUR_OF_DAY);
			int iMinute = cal1.get(Calendar.MINUTE);

			TimePickerDialog tpd = new TimePickerDialog(this,  
					new TimePickerDialog.OnTimeSetListener() {
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						  
							Toast.makeText(getBaseContext(), "Hour "+hourOfDay + " Min " + minute, Toast.LENGTH_SHORT).show();	
						}
					} , iHour,	iMinute, true);
			
			tpd.show();
			
			// not using the data
		} else if (v == fourthDialog) {
			DatePickerDialog dateDialog = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Time dtOfInterest = new Time();
							dtOfInterest.set(dayOfMonth, monthOfYear, year);
							long dtDob = dtOfInterest.toMillis(true);
							CharSequence strDate = DateFormat.format(
									"MMMM dd, yyyy", dtDob);
							Toast.makeText(getBaseContext(), strDate,
									Toast.LENGTH_SHORT).show();
						}
					}, 2013, 10, 24);

			dateDialog.setTitle("Pick a date");
			dateDialog.setMessage("Choose wisely");

			dateDialog.show();
		} else if (v == fifthDialog) {
			ProgressDialog progressDialog = new ProgressDialog(this);
			// make it a spinner
			progressDialog.setIndeterminate(true);
			progressDialog.setTitle("Showing Progress");
			progressDialog.setMessage("Houston, we're making progress!");
			progressDialog.show();
		} else {
			showCustomDialog();
		}
	}

	/*
	 * basic AlertDialog Positive & Negative response buttons If cancelable,
	 * back button will cancel it, can capture this wit onBack()
	 */
	public void showAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("Are you sure you want to exit?").setCancelable(
				false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				DialogExampleActivity.this.finish();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

	/*
	 * AlertDialog with a list of items
	 */

	public void showAlertDialogWList() {
		final CharSequence[] items = { "Red", "Green", "Blue" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a color");

		final boolean[] itemChecked = { false, false, false };
		builder.setMultiChoiceItems(items, itemChecked,
				new DialogInterface.OnMultiChoiceClickListener() {

					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {

						// clicking on an item does not dismiss the dialog
						// code here for individual selected items
					}

				});

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				if (itemChecked[0] == true)
					if (itemChecked[1] == true)
						if (itemChecked[2] == true)
							layout.setBackgroundResource(R.color.White);
						else
							layout.setBackgroundResource(R.color.Yellow);
					else if (itemChecked[2] == true)
						layout.setBackgroundResource(R.color.Violet);
					else
						layout.setBackgroundResource(R.color.Red);
				else if (itemChecked[1] == true)
					if (itemChecked[2] == true)
						layout.setBackgroundResource(R.color.Cyan);
					else
						layout.setBackgroundResource(R.color.Green);
				else if (itemChecked[2] == true)
					layout.setBackgroundResource(R.color.Blue);
				else
					layout.setBackgroundResource(R.color.Black);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void showCustomDialog() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		// Instantiate a layout XML file
		// into its corresponding View objects
		View layout = inflater.inflate(R.layout.custom_dialogue, null);
		final EditText et = (EditText) layout.findViewById(R.id.editText);
		
		// create a new dialog builder context: current activity class
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
						
				Toast.makeText(getBaseContext(), et.getText(),
						Toast.LENGTH_SHORT).show();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		builder.setView(layout);
		builder.setCancelable(false);
		builder.setTitle(R.string.dialogue_title);
		AlertDialog alert = builder.create();
		alert.show();
	}
}