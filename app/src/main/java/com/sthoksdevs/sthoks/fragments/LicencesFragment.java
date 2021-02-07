package com.sthoksdevs.sthoks.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.load.Key;
import com.sthoksdevs.sthoks.R;
import java.io.IOException;
import java.io.InputStream;

public class LicencesFragment extends DialogFragment {
    public static LicencesFragment getInstance() {
        return new LicencesFragment();
    }

    @Override // android.support.p000v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        WebView webView = (WebView) LayoutInflater.from(getActivity()).inflate(R.layout.layout_licences_dialog, (ViewGroup) null);
        try {
            InputStream open = getActivity().getAssets().open("open_source_licences.html");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            webView.loadData(new String(bArr), "text/html", Key.STRING_CHARSET_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlertDialog create = new AlertDialog.Builder(getActivity(), 2131689756).setTitle(getResources().getString(R.string.licences)).setView(webView).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            /* class com.sthoksdevs.sthoks.fragments.LicencesFragment.DialogInterface$OnClickListenerC06561 */

            public void onClick(DialogInterface dialogInterface, int i) {
                LicencesFragment.this.dismiss();
            }
        }).create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    @Override // android.support.p000v4.app.DialogFragment
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        getActivity().finish();
    }
}
