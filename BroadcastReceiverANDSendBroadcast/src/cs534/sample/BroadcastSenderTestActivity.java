package cs534.sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
// sends and receives a broadcast
// registration is done programmatically, not in manifest

public class BroadcastSenderTestActivity extends Activity {
    private static final String ACTION_SEND_BCAST = "bcast";
	/** Called when the activity is first created. */
	
    
	private final BroadcastReceiver receiver= new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Toast t=Toast.makeText(BroadcastSenderTestActivity.this, "BCAST Received", Toast.LENGTH_LONG);
			t.show();
		}
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    protected void onResume() {
    	super.onResume();
    	registerReceiver(receiver,new IntentFilter(ACTION_SEND_BCAST));
   }
    
    
    protected void onPause() {
    	super.onPause();
    	unregisterReceiver(receiver);
    }
    
    
    public void onClick(View v) {
    	Intent intent=new Intent();
    	intent.setAction(ACTION_SEND_BCAST);
    	sendBroadcast(intent);
    }
    
}   