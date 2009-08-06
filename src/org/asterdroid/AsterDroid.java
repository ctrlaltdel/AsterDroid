package org.asterdroid;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsterDroid extends Activity {
	TextView tv;
	Context context;
	Service notificator;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.my_text);
        context = getApplicationContext();
        
        // Set a listener to send a chat text message
        Button send = (Button) this.findViewById(R.id.Button01);
        send.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		String text = "Salut depuis Android";
 
        		Notificator.sendMessage(text);
        	}
        });

        
        // Settings button
        Button settings = (Button) this.findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent();
                myIntent.setClassName("org.asterdroid", "org.asterdroid.Settings");
                startActivity(myIntent);    
            }
        });
        
        // Settings button
        Button exit = (Button) this.findViewById(R.id.Exit);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	stopService(new Intent().setClassName("ord.asterdroid", "org.asterdroid.Notificator"));
            }
        });
        
        startService(new Intent().setClassName("org.asterdroid", "org.asterdroid.Notificator"));
    }
    
    public void onDestroy() {
    	super.onDestroy();
    	/* stopService(new Intent().setClassName("org.asterdroid", "org.asterdroid.Notificator")); */
    }
}