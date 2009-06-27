package org.asterdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;


public class AsterDroid extends Activity {
	TextView tv = null;
    XMPPConnection connection;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	String host = "planete.ctrlaltdel.ch";
    	String port = "5222";
    	String service = "planete.ctrlaltdel.ch";
    	String username = "phone1";
    	String password = "phone1";
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.my_text);
                
        // Create a connection
        ConnectionConfiguration connConfig =
            new ConnectionConfiguration(host, Integer.parseInt(port), service);
        connection = new XMPPConnection(connConfig);

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
        } catch (XMPPException ex) {
            Log.e("XMPPClient", "Failed to log in as " + username);
            Log.e("XMPPClient", ex.toString());
            connection = null;
        }
        
        
        // Set a listener to send a chat text message
        Button send = (Button) this.findViewById(R.id.Button01);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String text = "Salut depuis Android";
                String to = "francois@planete.ctrlaltdel.ch";

                Log.i("XMPPClient", "Sending text [" + text + "] to [" + to + "]");
                Message msg = new Message(to, Message.Type.chat);
                msg.setBody(text);
                connection.sendPacket(msg);
            }
        });
        
        /* Display incoming messages */
        if (connection != null) {
            // Add a packet listener to get messages sent to us
            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            connection.addPacketListener(new PacketListener() {
                public void processPacket(Packet packet) {
                    Message message = (Message) packet;
                    if (message.getBody() != null) {
                        String fromName = StringUtils.parseBareAddress(message.getFrom());
                        Log.i("XMPPClient", "Got text [" + message.getBody() + "] from [" + fromName + "]");
                        tv.setText(message.getBody() + "\n");
                    }
                }
            }, filter);
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
    
    public void onDestroy() {
    	super.onDestroy();
    	connection.disconnect();
    }
}