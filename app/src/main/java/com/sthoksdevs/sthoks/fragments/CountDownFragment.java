package com.sthoksdevs.sthoks.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sthoksdevs.sthoks.activities.QuizActivity;
import com.sthoksdevs.sthoks.interfaces.TimerInterface;

public class CountDownFragment extends Fragment {
    public static final String TAG = "CountDownFragment";
    private static final String TIME_LEFT_KEY = "TimeLeft";
    private TimerInterface mCallback;
    private long mTime;
    private MyTimer timer;

    public static CountDownFragment getInstance(FragmentManager fragmentManager, long j) {
        CountDownFragment countDownFragment = (CountDownFragment) fragmentManager.findFragmentByTag(TAG);
        if (countDownFragment != null) {
            return countDownFragment;
        }
        CountDownFragment countDownFragment2 = new CountDownFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(TIME_LEFT_KEY, j);
        countDownFragment2.setArguments(bundle);
        fragmentManager.beginTransaction().add(countDownFragment2, TAG).commit();
        return countDownFragment2;
    }

    @Override // android.support.p000v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCallback = (QuizActivity) context;
    }

    @Override // android.support.p000v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        if (getArguments() != null) {
            this.mTime = getArguments().getLong(TIME_LEFT_KEY);
        }
    }

    @Override // android.support.p000v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        cancelTimer();
    }

    @Override // android.support.p000v4.app.Fragment
    public void onDestroy() {
        cancelTimer();
        super.onDestroy();
    }

    public void startTimer(long j) {
        cancelTimer();
        this.timer = new MyTimer(j, 1000);
        this.timer.start();
    }

    public void cancelTimer() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    /* access modifiers changed from: private */
    public class MyTimer extends CountDownTimer {
        MyTimer(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long j) {
            int i = (((int) j) / 1000) % 60;
            if (CountDownFragment.this.mCallback != null) {
                CountDownFragment.this.mCallback.updateUI(String.valueOf(i));
            }
        }

        public void onFinish() {
            if (CountDownFragment.this.mCallback != null) {
                CountDownFragment.this.mCallback.updateUI("0");
            }
        }
    }
}
