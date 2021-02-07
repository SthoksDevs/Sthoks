package com.sthoksdevs.sthoks.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.database.MyDb;
import com.sthoksdevs.sthoks.utils.AppSessions;

public class SplashActivity extends AppCompatActivity {
    AppSessions mSession;
    MyDb myDb;
    Animation slideTextRightAnimation;
    Animation slideTextUpAnimation;
    TextView titleTv;
    TextView waitTv;

    /* access modifiers changed from: protected */
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.SupportActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        this.mSession = new AppSessions(this);
        this.myDb = new MyDb(this);
        this.titleTv = (TextView) findViewById(R.id.title_tv);
        this.waitTv = (TextView) findViewById(R.id.wait_tv);
        this.slideTextUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_up);
        this.slideTextRightAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
        this.titleTv.setAnimation(this.slideTextUpAnimation);
        this.slideTextUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class com.sthoksdevs.sthoks.activities.SplashActivity.animationAnimation$AnimationListenerC06521 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                SplashActivity.this.waitTv.setAnimation(SplashActivity.this.slideTextRightAnimation);
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class com.sthoksdevs.sthoks.activities.SplashActivity.animationAnimation$AnimationListenerC06521.RunnableC06531 */

                    public void run() {
                        if (SplashActivity.this.mSession.isAppFirstTimeUse()) {
                            new CheckTask().execute(new Void[0]);
                        }
                        SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }, 1000);
            }
        });
    }

    private class CheckTask extends AsyncTask<Void, Void, Void> {
        private CheckTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            SplashActivity.this.myDb.addToMemo(SplashActivity.this.getResources().getStringArray(R.array.questions), SplashActivity.this.getResources().getStringArray(R.array.answers));
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void r2) {
            super.onPostExecute((Void) r2);
            SplashActivity.this.mSession.setIsFirstUse(false);
        }
    }
}
