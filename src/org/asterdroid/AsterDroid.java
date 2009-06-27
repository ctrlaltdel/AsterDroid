package org.asterdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;


public class AsterDroid extends Activity {
	TextView tv = null;
    XMPPConnection connection;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	String host = "planete.ctrlaltdel.ch";
    	String username = "phone1";
    	String password = "phone1";

    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.my_text);
                
        // Create a connection
        connection = new XMPPConnection(host);

        try {
            connection.connect();
            Log.i("XMPPClient", "Connected to " + connection.getHost());
        } catch (XMPPException ex) {
            Log.e("XMPPClient", "Failed to connect to " + connection.getHost());
            Log.e("XMPPClient", ex.toString());
            connection = null;
        }
        try {
            connection.login(username, password);
            Log.i("XMPPClient", "Logged in as " + connection.getUser());

            // Set the status to available
            Presence presence = new Presence(Presence.Type.available);
            connection.sendPacket(presence);
            connection = null;
        } catch (XMPPException ex) {
            Log.e("XMPPClient", "Failed to log in as " + username);
            Log.e("XMPPClient", ex.toString());
            connection = null;
        }
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