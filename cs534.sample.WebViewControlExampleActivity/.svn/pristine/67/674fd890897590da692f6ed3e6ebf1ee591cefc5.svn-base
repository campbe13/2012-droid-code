package cs534.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewControlExampleActivity extends Activity {
    /** Called when the activity is first created. */
	private WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        webView=(WebView)findViewById(R.id.webView);
        webView.loadUrl("http://xkcd.com/662/");
        webView.setWebViewClient(new WebViewClient());
        // set to fit screen
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }
    
    public void backAction(View v) {
    	if (webView.canGoBack())
    		webView.goBack();
    }
    
    public void forwardAction(View v) {
    	if (webView.canGoForward())
    		webView.goForward();
    }
    
    public void reloadAction(View v) {
    	webView.reload();
    }
    public void loadNew(View v) {
    	webView.loadUrl("http://gimpchat.com/files/2_G2_Tux_Final.png");
        webView.setWebViewClient(new WebViewClient()); 
    }
    
}