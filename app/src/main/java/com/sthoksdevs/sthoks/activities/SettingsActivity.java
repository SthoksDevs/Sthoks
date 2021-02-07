package com.sthoksdevs.sthoks.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sthoksdevs.sthoks.R;

public class SettingsActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.SupportActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        FragmentPreferences fragmentPreferences = new FragmentPreferences();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (bundle == null) {
            beginTransaction.add(R.id.rLayout, fragmentPreferences, "settings_fragment");
        } else {
            getFragmentManager().findFragmentByTag("settings_fragment");
        }
    }

    public static class FragmentPreferences extends PreferenceFragment {
        public void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            addPreferencesFromResource(R.xml.settings_screen);
        }
    }
}
