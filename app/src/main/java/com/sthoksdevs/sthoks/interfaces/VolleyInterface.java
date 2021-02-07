package com.sthoksdevs.sthoks.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyInterface {
    void onError(String str) throws Exception;

    void onSuccess(JSONObject jSONObject) throws JSONException;
}
