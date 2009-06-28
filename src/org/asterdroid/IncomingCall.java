package org.asterdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IncomingCall extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incoming_call);
        
        Intent intent = getIntent();
        String callerid = intent.getStringExtra("callerid");
        
        ((TextView) findViewById(R.id.callerid)).setText(callerid);
        
        // Buttons
        ((Button) this.findViewById(R.id.pickup_gsm)).setOnClickListener(
        		new View.OnClickListener() {
        			public void onClick(View view) {
        				System.err.println("PICKUP GSM");
        				Notificator.sendMessage("pickup_gsm");
        				finish();
        			}
        		});
        
        ((Button) this.findViewById(R.id.pickup_voip)).setOnClickListener(
        		new View.OnClickListener() {
        			public void onClick(View view) {
        				System.err.println("PICKUP VOIP");
        				Notificator.sendMessage("pickup_voip");
        				finish();
        			}
        		});
        		

        ((Button) this.findViewById(R.id.pickup_voip)).setOnClickListener(
        		new View.OnClickListener() {
        			public void onClick(View view) {
        				System.err.println("REJECT");
        				Notificator.sendMessage("reject");
        				finish();
        			}
        		});
    }
}
