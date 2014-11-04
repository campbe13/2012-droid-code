package cs534.sample.multithread;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MultiThread extends Activity implements OnClickListener {
	private TextView tv;
	private String data;
	private Time today;
	private final String FN = "myfile.txt";
	private final String TAG = "MTHREAD";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_thread);
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.textView1);
		today = new Time(Time.getCurrentTimezone());
		today.setToNow();
	}

	public void onClick(View v) {
		new getDataAsync().execute();
		if (data != "") 
			tv.setText("finished "+ today.format("%F: %T") + "\n " +data );
		else
			tv.setText("finished "+ today.format("%F: %T") + "\nno data" );
	} 

	private void getData() {
		String line = null;
		data = "";
		try     {
			FileInputStream fis = openFileInput(FN);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			while ((line = reader.readLine()) != null)
				data += line + "\n";
			reader.close();
			fis.close();
			Log.d(TAG, "File successfully loaded.");
		} catch (FileNotFoundException e)     {
			Log.d(TAG, "Error loading file: " + e.getLocalizedMessage());
		}  catch (IOException e)     {
			Log.d(TAG, "Error loading file: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	// parameters input, progress, returning
	public class getDataAsync extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getData();
			return null;
		}
	} // AsyncTask class
} // Activity class
