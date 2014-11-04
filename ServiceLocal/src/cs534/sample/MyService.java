package cs534.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	private static final int SIMPLE_NOTIFICATION_ID = 0;
	private static final String TAG = "SERVTEST";
	private MyThread thread; 
	private int counter;
	
	
	public void onCreate() {
		super.onCreate();
		Log.d(TAG,"MyService created");
		
		thread=new MyThread();
		thread.start();
		counter=20;
		
	}
	
	public void onDestroy() {
		
		Log.d(TAG,"MyService stopped");
		
		thread.running=false;
		// set up notification 
		Notification notification = new Notification(R.drawable.icon, "Timer expired!", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults|=Notification.DEFAULT_VIBRATE;
		notification.defaults|=Notification.DEFAULT_SOUND;
		long[] vibrate={0,100,200,300};
		notification.vibrate = vibrate;
		// set up intent that will be launched when 
		Intent intent = new Intent(this, MyServiceCounterActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Timer Expired Notification", "Call myServiceCounterActivity", pIntent);
			
		NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
		
		super.onDestroy();
	}
	
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG,"MyService started");
		counter=20;
		return super.onStartCommand(intent, flags, startId);
	}
	
	class MyThread extends Thread {
		
		private static final int SIMPLE_NOTIFICATION_ID = 0;
		public boolean running;
		
		public MyThread() {
			super("MyThread");
			running=true;
		}
		
		
		public void run() {
			
			  while(running) {
				  
				  try{Thread.sleep(1000);} catch (InterruptedException e){}
				 
				  counter--;
				  
				  Log.d(TAG,"MyService running "+counter);
			  
				  if (counter <=0) {
					  	
					  stopSelf();
				  }	  
			  }
			
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
