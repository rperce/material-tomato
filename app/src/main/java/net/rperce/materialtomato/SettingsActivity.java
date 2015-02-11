package net.rperce.materialtomato;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by Robert on 2/10/2015.
 */
public class SettingsActivity extends PreferenceActivity {
    private static final int prefs = R.layout.preferences;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            onCreatePreferenceActivity();
        } else {
            onCreatePreferenceFragment();
        }
    }

    @SuppressWarnings("deprecation")
    protected void onCreatePreferenceActivity() {
        addPreferencesFromResource(prefs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreatePreferenceFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override public void onCreate(final Bundle savedInstance) {
            super.onCreate(savedInstance);
            addPreferencesFromResource(prefs);
        }
    }
}
