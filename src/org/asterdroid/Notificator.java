package org.asterdroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Notificator extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		System.err.println("Service has started");
		return null;
	}
	
	public void onCreate() {
		super.onCreate();
	}

}
