package org.asterdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsterDroid extends Activity {
	TextView tv;
	Context context;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.my_text);
        context = getApplicationContext();
        
        // Settings button
        Button settings = (Button) this.findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent();
                myIntent.setClassName("org.asterdroid", "org.asterdroid.Settings");
                startActivity(myIntent);    
            }
        });
        
        startService(new Intent().setClassName("org.asterdroid", "org.asterdroid.Notificator"));
    }
    
    public void onDestroy() {
    	super.onDestroy();
    	/* stopService(new Intent().setClassName("org.asterdroid", "org.asterdroid.Notificator")); */
    }
}