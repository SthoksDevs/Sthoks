package com.sthoksdevs.sthoks.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sthoksdevs.sthoks.R;

public class NoInternetActivity extends AppCompatActivity {
    Button btnRetry;

    /* access modifiers changed from: protected */
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.SupportActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_no_internet);
        this.btnRetry = (Button) findViewById(R.id.retryBtn);
        this.btnRetry.setOnClickListener(new View.OnClickListener() {
            /* class com.sthoksdevs.sthoks.activities.NoInternetActivity.View$OnClickListenerC06381 */

            public void onClick(View view) {
                if (NoInternetActivity.this.connected()) {
                    NoInternetActivity.this.setResult(-1);
                    NoInternetActivity.this.finish();
                    return;
                }
                NoInternetActivity.this.setResult(0);
                NoInternetActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean connected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        return false;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
