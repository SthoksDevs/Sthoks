package com.sthoksdevs.sthoks.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSessions {
    SharedPreferences.Editor editor = this.prefs.edit();
    Context mContext;
    SharedPreferences prefs = this.mContext.getSharedPreferences("Sthoks", 0);

    public AppSessions(Context context) {
        this.mContext = context;
    }

    public void setIsFirstUse(boolean z) {
        this.editor.putBoolean("isFirstTimeUse", z);
        this.editor.apply();
    }

    public boolean isAppFirstTimeUse() {
        return this.prefs.getBoolean("isFirstTimeUse", true);
    }
}
