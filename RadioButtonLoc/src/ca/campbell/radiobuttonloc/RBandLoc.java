package ca.campbell.radiobuttonloc;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RBandLoc extends Activity {
	private final static String TAG = "RBLOC";
    private String[] buttonText;
    private RadioButton rb1, rb2, rb3;
    private RadioGroup rg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rband_loc);
		buttonText = getResources().getStringArray(R.array.buttonTxt);

		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		rb3 = (RadioButton) findViewById(R.id.radioButton3);
		
		rb1.setText(buttonText[0]);
		rb2.setText(buttonText[1]);
		rb3.setText(buttonText[2]);

		rg = (RadioGroup) findViewById(R.id.buttonGroup);
		rg.setOnCheckedChangeListener(checkClicked);

    }
    public RadioGroup.OnCheckedChangeListener checkClicked = new RadioGroup.OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			Log.d(TAG, "checkClicked");
			RadioButton selected  = (RadioButton)group.findViewById(checkedId);
			// This puts the value (true/false) into the variable
			/*
	        if (selected.isChecked()) 
		        Toast.makeText(getBaseContext(), selected.getText(), Toast.LENGTH_LONG).show();
	        }
			// correctAnswer is assigned the string of the correct answer
			if ((String)selected.getText().toString() ==  correctAnswer) {
				Toast.makeText(getBaseContext(), getResources().getString(R.string.correct), Toast.LENGTH_LONG).show();
			} else { 
				Toast.makeText(getBaseContext(), getResources().getString(R.string.incorrect), Toast.LENGTH_LONG).show();
			}
			*/
			Toast.makeText(getBaseContext(), selected.getText().toString(), Toast.LENGTH_LONG).show();
		} //onCheckChanged()
		};  //onCheckChangedListener()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_rband_loc, menu);
        return true;
    }
}
