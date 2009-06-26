package ch.ctrlaltdel.asterdroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AsterDroid extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        TestClient c = null;

        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("SALUT");
        setContentView(tv);
        c = new TestClient();

        try {
          c.main(tv);
        } catch (Exception e) {
          tv.setText("BLahhhhh");
        }
    }
}
