package cs534.sample.dialogexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class DialogExampleActivity extends Activity implements OnClickListener {
	
	Button firstDialog;
	Button secondDialog;
	Button thirdDialog;
	
	LinearLayout layout;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        layout = (LinearLayout)findViewById(R.id.ll);
        
        firstDialog = (Button)findViewById(R.id.btnFirst);
        secondDialog = (Button)findViewById(R.id.btnSecond);
        thirdDialog = (Button)findViewById(R.id.btnThird);
        firstDialog.setOnClickListener(this);
        secondDialog.setOnClickListener(this);
        thirdDialog.setOnClickListener(this);
        
    }

	
	public void onClick(View v) {
		if (v == firstDialog) {
		      AlertDialog.Builder builder = new AlertDialog.Builder(this);

		      builder.setMessage("Are you sure you want to exit?").setCancelable(false);
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
		} else if (v == secondDialog) {
			
			final CharSequence[] items = {"Red", "Green", "Blue"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Pick a color");
			
			final boolean[] itemChecked = {false,false,false};
			builder.setMultiChoiceItems(items, itemChecked, new DialogInterface.OnMultiChoiceClickListener() {

				
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {	}
				
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
					else
						if (itemChecked[1] == true)
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
			
//			builder.setItems(items, new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int item) {
//					Toast.makeText(getApplicationContext(), items[item], 	Toast.LENGTH_SHORT).show();
//					switch (item) {
//						case 0: layout.setBackgroundResource(R.color.Red);
//								break;
//						case 1: layout.setBackgroundResource(R.color.Green);
//								break;
//						case 2: layout.setBackgroundResource(R.color.Blue);
//								break;
//					}
//					
//					
//				}
//			});
			builder.show();
		} else {
			TimePickerDialog tpd = new TimePickerDialog(this, null, 0, 23, true);
			tpd.show();
		}
	}
}