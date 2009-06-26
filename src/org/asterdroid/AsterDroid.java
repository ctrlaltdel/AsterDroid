package org.asterdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class AsterDroid extends Activity {
	TextView tv = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.my_text);
    }
    
    public void onStart() {
    	super.onStart();
    	tv.append("onStart()\n");
    }
    
    public void onStop() {
    	super.onStop();
    	tv.append("onStop()\n");
    }
    
    public void onResume() {
    	super.onResume();
    	tv.append("onResume()\n");
    }
    
    public void onPause() {
    	super.onPause();
    	tv.append("onPause()\n");
    }
    
    static final int PICK_CONTACT_REQUEST = 0;

    /*
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        tv.append("Press\n");
        return true;
    }
    */
}