package org.asterdroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.settings);
        updateSummaries();
    }

    public void updateSummaries() {
    	getPreferenceScreen().findPreference("username").setSummary(getPreferenceScreen().getSharedPreferences().getString("username", ""));
    	getPreferenceScreen().findPreference("hostname").setSummary(getPreferenceScreen().getSharedPreferences().getString("hostname", ""));
    	getPreferenceScreen().findPreference("port").setSummary(getPreferenceScreen().getSharedPreferences().getString("port", ""));
    }
}
