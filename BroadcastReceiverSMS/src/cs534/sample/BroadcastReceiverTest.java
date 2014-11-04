package cs534.sample;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class BroadcastReceiverTest extends BroadcastReceiver {
private static final String TAG = "BCASTR";
	private static final int SIMPLE_NOTIFICATION_ID = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Intent received: " + intent.getAction());
		
		Bundle bundle = intent.getExtras();
        if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                if (messages.length > -1) {
                    Log.i(TAG, "Message received: " + messages[0].getMessageBody());
                    
                 
                    NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                    // deprecated in API 11 (2.3.3. is 10)
                    Notification notification = new Notification(R.drawable.icon, messages[0].getMessageBody(), System.currentTimeMillis());
        			notification.flags |= Notification.FLAG_AUTO_CANCEL;
        			
        			PendingIntent pIntent=PendingIntent.getActivity(context, 0, null, PendingIntent.FLAG_CANCEL_CURRENT);
                    // deprecated in API 11 (2.3.3. is 10)
        			notification.setLatestEventInfo(context, "SMS Received", messages[0].getMessageBody(), pIntent);
        			
        		
        			notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification);
                    
                }
            }
        }

	
		
   
}