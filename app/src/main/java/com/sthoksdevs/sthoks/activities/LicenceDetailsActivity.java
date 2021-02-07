package com.sthoksdevs.sthoks.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.fragments.LicencesFragment;

public class LicenceDetailsActivity extends AppCompatActivity {
    private static final String DIALOG_TAG = "LicencesFragment";

    /* access modifiers changed from: protected */
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.SupportActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_licences_details);
        LicencesFragment.getInstance().show(getSupportFragmentManager(), DIALOG_TAG);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
