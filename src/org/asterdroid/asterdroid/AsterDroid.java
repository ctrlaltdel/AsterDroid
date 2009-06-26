package org.asterdroid.asterdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;

public class AsterDroid extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = (TextView) findViewById(R.layout.main.text);
        tv.setText("BLAH");
    }
}