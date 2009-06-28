package org.asterdroid;

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

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class Notificator extends Service {
    static XMPPConnection connection;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		System.err.println("Service has started");
		return null;
	}
	
	public void onCreate() {
		super.onCreate();
		
	    try {
	    	XMPPSetup();
	    } catch (Exception e) {
	    	System.err.println("XMPPSetup failed");
	    }
	}
	
    public void XMPPSetup() {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	
    	String host = prefs.getString("hostname", "");
    	int port = Integer.parseInt(prefs.getString("port", "5222"));
    	String username = prefs.getString("username", "");
    	String password = prefs.getString("password", "");

        // Create a connection
        ConnectionConfiguration connConfig =
            new ConnectionConfiguration(host, port);
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
        
        /* Display incoming messages */
        if (connection != null) {
            // Add a packet listener to get messages sent to us
            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            connection.addPacketListener(new PacketListener() {
                public void processPacket(Packet packet) {
                    Message message = (Message) packet;
                    if (message.getBody() != null) {
                        String fromName = StringUtils.parseBareAddress(message.getFrom());
                        String text = message.getBody();
                        
                        Log.i("XMPPClient", "Got text [" + text + "] from [" + fromName + "]");
                        
                        /* Handle special ping packets */
                        if (text.contains("ping")) {
                        	Log.i("XMPPClient", "Ping received");
                        	
                        	try {
                        		String timestamp = text.substring(5);
                        		sendMessage("pong:" + timestamp);
                        	} catch (Exception e) {
                        		Log.e("XMPPClient", "Received invalid ping packet: " + text);
                        	}
                        } else {
                        	Intent myIntent = new Intent();
                        	myIntent.setClassName("org.asterdroid", "org.asterdroid.IncomingCall");
                        	myIntent.putExtra("callerid", text);
                        	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        
                        	Log.i("XMPPClient", "Intent is built: " + myIntent);
                        
                        	startActivity(myIntent);
                                                
                        	Log.i("Incoming_call", "Activity should have been started");
                        }
                    }
                }
            }, filter);
        }
    }

    public static void sendMessage(String text) {
		String to = "asterisk@planete.ctrlaltdel.ch";

		Log.i("XMPPClient", "Sending text [" + text + "] to [" + to + "]");
		Message msg = new Message(to, Message.Type.chat);
		msg.setBody(text);
		connection.sendPacket(msg);
    }
    
    public void onDestroy() {
    	super.onDestroy();
   
    	if (connection != null) {
    		connection.disconnect();
    	}
    }
}
