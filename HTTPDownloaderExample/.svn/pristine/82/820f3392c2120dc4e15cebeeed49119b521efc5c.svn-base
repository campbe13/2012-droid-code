package cs534.sample.httpdownloader;

import java.net.URI;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HttpDownloaderExampleActivity extends Activity {
    /** Called when the activity is first created. */
	private ProgressBar pBar;
	private String address="http://static.googleusercontent.com/external_content/untrusted_dlcp/source.android.com/en//compatibility/4.1/android-4.1-cdd.pdf";
	private DownloadManager dManager;
	private TextView textView;
	private Button downloadButton;
	private Button showButton;
	private long code;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pBar=(ProgressBar)findViewById(R.id.progressBar1);
        textView=(TextView)findViewById(R.id.textView1);
        downloadButton=(Button)findViewById(R.id.button1);
        showButton=(Button)findViewById(R.id.button2);
        
        dManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        pBar.setVisibility(View.INVISIBLE);
        showButton.setEnabled(false);
     
        BroadcastReceiver complete=new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				pBar.setVisibility(View.INVISIBLE);
				textView.setText("Status: Download completed!");
				showButton.setEnabled(true);
			}        	
        };
        
        registerReceiver(complete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    
    public void downloadAction(View v) {
    	pBar.setVisibility(View.VISIBLE);
    	downloadButton.setEnabled(false);
    	
    	textView.setText("Status: Download started!");
    	DownloadManager.Request request=new DownloadManager.Request(Uri.parse(address));
    	code=dManager.enqueue(request);
    	
    }
    
    public void showDownload(View v) {
    	Intent intent=new Intent();
    	intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
    	startActivity(intent);
    }
    
}