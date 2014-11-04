package cs534.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class MyServiceCounterActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private Intent sIntent;
	private ToggleButton toggle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sIntent=new Intent(this,MyService.class);
        toggle=(ToggleButton)findViewById(R.id.toggleButton1);
        toggle.setOnClickListener(this);
    }

public void onClick(View arg0) {
		if (toggle.isChecked()) {
			startService(sIntent);
		} else {
			stopService(sIntent);
		}
		
    }
}