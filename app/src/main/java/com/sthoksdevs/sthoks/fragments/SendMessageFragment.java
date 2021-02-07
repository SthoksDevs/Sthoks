package com.sthoksdevs.sthoks.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sthoksdevs.sthoks.activities.ContactActivity;
import com.sthoksdevs.sthoks.interfaces.SendMessageInterface;
import com.sthoksdevs.sthoks.network.RetrofitClient;
import java.io.IOException;
import java.util.Arrays;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageFragment extends Fragment {
    private static final String MESSAGEINPUTS_KEY = "MESSAGEINPUTS";
    private static final String TAG = "SendMessageFragment";
    private String[] inputs;
    private SendMessageInterface mCallback;
    private String[] sent;

    public static SendMessageFragment getInstance(FragmentManager fragmentManager, String[] strArr) {
        SendMessageFragment sendMessageFragment = (SendMessageFragment) fragmentManager.findFragmentByTag(TAG);
        if (sendMessageFragment != null) {
            return sendMessageFragment;
        }
        SendMessageFragment sendMessageFragment2 = new SendMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(MESSAGEINPUTS_KEY, strArr);
        sendMessageFragment2.setArguments(bundle);
        fragmentManager.beginTransaction().add(sendMessageFragment2, TAG).commit();
        return sendMessageFragment2;
    }

    @Override // android.support.p000v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCallback = (ContactActivity) context;
    }

    @Override // android.support.p000v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        if (getArguments() != null) {
            this.inputs = getArguments().getStringArray(MESSAGEINPUTS_KEY);
        }
    }

    @Override // android.support.p000v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (!Arrays.equals(this.inputs, this.sent)) {
            sendMessage();
        }
    }

    private void sendMessage() {
        RetrofitClient.getInstance().getApi().sendEmail(this.inputs[2], "tcnhlangulela@gmail.com", "Sthoks Android Profile App Message From " + this.inputs[0], this.inputs[3] + " \r\n\nFrom Company: " + this.inputs[4] + " \r\n\nPhone: " + this.inputs[1]).enqueue(new Callback<ResponseBody>() {
            /* class com.sthoksdevs.sthoks.fragments.SendMessageFragment.C06581 */

            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        SendMessageFragment.this.mCallback.responseMessage(new JSONObject(response.body().string()).getString("message"));
                        SendMessageFragment.this.sent = SendMessageFragment.this.inputs;
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                SendMessageFragment.this.mCallback.responseMessage(th.getMessage());
            }
        });
    }
}
