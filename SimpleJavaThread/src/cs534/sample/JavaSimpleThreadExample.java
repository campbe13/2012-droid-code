package cs534.sample;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class JavaSimpleThreadExample extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private static final String TAG = "JTHREAD";
	private CounterThread workerThread;
	public Handler handler;
	private TextView text;
	private ToggleButton toggle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text=(TextView)findViewById(R.id.textView1);
        toggle=(ToggleButton)findViewById(R.id.toggleButton1);
        final DecimalFormat df=new DecimalFormat("#.##");
        // we need this for our threads to communicate with UI thread
        handler=new Handler() {
        	public void handleMessage(Message msg) {
        		double value=msg.getData().getDouble("time");
        		String label="Time: "+ df.format(value);
        		text.setText(label);
        		Log.d(TAG,label);
        	}
        };        
        toggle.setOnClickListener((OnClickListener) this);
    }
    
    
	public void onClick(View arg0) {
		if (toggle.isChecked()) {
			workerThread=new CounterThread();
			workerThread.start();
		} else {
			workerThread.reset();
		}				
	} // onClick()
    
    class CounterThread extends Thread {
    	
    	private boolean running;
    	private double timeValue;
    	
    	public CounterThread() {
    		super("CounterThread");
    		running=false;
    		timeValue=0.0;
    	} // constructor
    	
    	public void run() {
    		while(running) {
    			try{Thread.sleep(10);} catch (InterruptedException e){} 
    			timeValue+=0.01;
    			Message msg=handler.obtainMessage();
    			Bundle b=new Bundle();
    			b.putDouble("time", timeValue);
    			msg.setData(b);
    			handler.sendMessage(msg);
    			Log.d(TAG,"RUNNING");
    		}
    	} // run()
    	
    	public void start() {
    		running=true;
    		super.start();
    	} // start()
    	
    	
    	public void reset() {
    		running=false;
    	}  // reset()
    } // CounterThread  
}   