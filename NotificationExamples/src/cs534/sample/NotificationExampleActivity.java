package cs534.sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class NotificationExampleActivity extends Activity implements OnClickListener {
	
	private final static int SIMPLE_NOTIFICATION_ID = 1;
	
    /** Called when the activity is first created. */
	public Button notify1;
	public Button notify2;
	public Button notify3;
	public Button notify4;
	public Button notify5;
	public Button notify6;
	public Button notify7;
	
	
	public NotificationManager notificationManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        notify1=(Button)findViewById(R.id.button1);
        notify2=(Button)findViewById(R.id.button2);  
        notify3=(Button)findViewById(R.id.button3);  
        notify4=(Button)findViewById(R.id.button4);  
        notify5=(Button)findViewById(R.id.button5);  
        notify6=(Button)findViewById(R.id.button6); 
        notify7=(Button)findViewById(R.id.button7);  
        
        
        notify1.setOnClickListener((OnClickListener) this);
        notify2.setOnClickListener(this); 
        notify3.setOnClickListener(this);
        notify4.setOnClickListener(this);
        notify5.setOnClickListener(this);
        notify6.setOnClickListener(this);
        notify7.setOnClickListener(this);
       
        
        notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    
    
	public void onClick(View arg0) {
		if (arg0 == notify1 ) {
			
			Notification notification = new Notification(R.drawable.icon, "This is a very basic Notification to catch your attention!", System.currentTimeMillis());
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			Intent intent = new Intent(this, NotificationActivity.class);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT);
			notification.setLatestEventInfo(this, "Notification", "Click to launch NotificationActivity", pIntent);
			notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
			
		}
		
		if (arg0 == notify2 ) {
			
			Notification notification = new Notification(R.drawable.icon, "This is a Notification with sound!", System.currentTimeMillis());
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.defaults|=Notification.DEFAULT_SOUND;
			Intent intent = new Intent(this, NotificationActivity.class);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT);
			notification.setLatestEventInfo(this, "Notification with Sound", "Click to launch NotificationActivity", pIntent);
			notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
			
		}
		
	if (arg0 == notify3 ) {
			
			Notification notification = new Notification(R.drawable.icon, "This is a Notification with insistent Sound!", System.currentTimeMillis());
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.defaults|=Notification.DEFAULT_SOUND;
			notification.flags |= Notification.FLAG_INSISTENT;
			Intent intent = new Intent(this, NotificationActivity.class);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT);
			notification.setLatestEventInfo(this, "Notification with insistent Sound", "Click to launch NotificationActivity", pIntent);
			notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
			
		}
	
	
	if (arg0 == notify4 ) {
		
		Notification notification = new Notification(R.drawable.icon, "This is a Notification with lights??", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults|=Notification.DEFAULT_LIGHTS;
		notification.flags |=Notification.FLAG_SHOW_LIGHTS;
		Intent intent = new Intent(this, NotificationActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Notification with Light", "Click to launch NotificationActivity", pIntent);
		notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
		
	}
	
    if (arg0 == notify5 ) {
		
		Notification notification = new Notification(R.drawable.icon, "This is a Notification with custom lights???", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		notification.ledARGB = 0xff00ff00;
		notification.ledOnMS = 300;
		notification. ledOffMS = 1000;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		Intent intent = new Intent(this, NotificationActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Notification with Custom Lights", "Click to launch NotificationActivity", pIntent);
		notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
		
	 }
    
  if (arg0 == notify6 ) {
		
		Notification notification = new Notification(R.drawable.icon, "This is a Notification with Vibrations??", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults|=Notification.DEFAULT_VIBRATE;
		long[] vibrate={0,100,200,300};
		notification.vibrate = vibrate;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		Intent intent = new Intent(this, NotificationActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(this, "Notification with Vibrations", "Click to launch NotificationActivity", pIntent);
		notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
		
	 }
 
  if (arg0 == notify7 ) {
		
	  Context context=getApplicationContext();

	  // Define text and duration of the notification
	  CharSequence text= "This is a Toast Notification";
	  int duration=Toast.LENGTH_SHORT;

	  Toast toast=Toast.makeText(context, text, duration);

	  // Send the notification to the screen
	  toast.show();

		
	 }  
  
 
  
	}
}