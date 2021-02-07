package com.sthoksdevs.sthoks.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.fragments.SendMessageFragment;
import com.sthoksdevs.sthoks.interfaces.SendMessageInterface;
import java.util.regex.Pattern;

public class ContactActivity extends AppCompatActivity implements SendMessageInterface {
    TextInputLayout companyInputLayout;
    TextInputEditText companyTxt;
    TextInputLayout emailInputLayout;
    TextInputEditText emailTxt;
    ScrollView inputsLayout;
    TextInputLayout messageInputLayout;
    TextInputEditText messageTxt;
    SendMessageFragment msgFragment;
    TextInputLayout nameInputLayout;
    TextInputEditText nameTxt;
    TextInputLayout phoneInputLayout;
    TextInputEditText phoneTxt;
    ProgressBar progressBar;
    Button responseBtn;
    RelativeLayout responseLayout;
    TextView responseTv;
    Button sendBtn;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_contact);
        this.inputsLayout = (ScrollView) findViewById(R.id.inputsLayout);
        this.responseLayout = (RelativeLayout) findViewById(R.id.responseLayout);
        this.responseTv = (TextView) findViewById(R.id.responseTv);
        this.responseBtn = (Button) findViewById(R.id.responseBtn);
        this.nameInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout3);
        this.nameTxt = (TextInputEditText) findViewById(R.id.name);
        this.emailInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        this.emailTxt = (TextInputEditText) findViewById(R.id.email);
        this.phoneInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout2);
        this.phoneTxt = (TextInputEditText) findViewById(R.id.phone);
        this.companyInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout4);
        this.companyTxt = (TextInputEditText) findViewById(R.id.company);
        this.messageInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout5);
        this.messageTxt = (TextInputEditText) findViewById(R.id.message);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.sendBtn = (Button) findViewById(R.id.button);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        validateInputs();
    }

    public void validateInputs() {
        this.emailTxt.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!Patterns.EMAIL_ADDRESS.matcher(ContactActivity.this.emailTxt.getText().toString()).matches()) {
                    ContactActivity.this.emailInputLayout.setError(ContactActivity.this.getResources().getString(R.string.acceptedEmail));
                } else {
                    ContactActivity.this.emailInputLayout.setError(null);
                }
            }

            public void afterTextChanged(Editable editable) {
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        if (!Patterns.EMAIL_ADDRESS.matcher(ContactActivity.this.emailTxt.getText().toString()).matches()) {
                            ContactActivity.this.emailTxt.setError(ContactActivity.this.getResources().getString(R.string.invalidEmail));
                            return;
                        }
                        ContactActivity.this.emailTxt.setError(null);
                        ContactActivity.this.emailInputLayout.setError(null);
                    }
                }, 1500);
            }
        });
        this.phoneTxt.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (ContactActivity.this.phoneTxt.getText().length() == 0) {
                    ContactActivity.this.phoneInputLayout.setError(null);
                } else if (ContactActivity.this.phoneTxt.getText().length() != 10) {
                    ContactActivity.this.phoneInputLayout.setError(ContactActivity.this.getResources().getString(R.string.acceptedPhone));
                } else {
                    ContactActivity.this.phoneInputLayout.setError(null);
                }
            }

            public void afterTextChanged(Editable editable) {
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        if (ContactActivity.this.phoneTxt.getText().length() == 0) {
                            ContactActivity.this.phoneTxt.setError(null);
                        } else if (!Pattern.compile("^0[0-9]+$").matcher(ContactActivity.this.phoneTxt.getText().toString()).matches() || ContactActivity.this.phoneTxt.getText().length() != 10) {
                            ContactActivity.this.phoneTxt.setError(ContactActivity.this.getResources().getString(R.string.invalidPhone));
                        } else {
                            ContactActivity.this.phoneTxt.setError(null);
                        }
                    }
                }, 1500);
            }
        });
        this.messageTxt.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 0) {
                    ContactActivity.this.messageTxt.setError(null);
                }
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextInputEditText textInputEditText = ContactActivity.this.messageTxt;
                textInputEditText.setError((300 - ContactActivity.this.messageTxt.getText().length()) + " characters left.");
            }

            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    ContactActivity.this.messageTxt.setError(null);
                }
                new Handler().postDelayed(new Runnable() {


                    public void run() {
                        ContactActivity.this.messageTxt.setError(null);
                    }
                }, 5000);
            }
        });
    }

    private boolean isError() {
        return (this.phoneTxt.getText().length() <= 0 || (this.phoneTxt.getText().length() == 10 && Pattern.compile("^0[0-9]+$").matcher(this.phoneTxt.getText().toString()).matches())) && Patterns.EMAIL_ADDRESS.matcher(this.emailTxt.getText().toString()).matches();
    }

    private boolean getConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.sendBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.sthoksdevs.sthoks.activities.ContactActivity.View$OnClickListenerC06354 */

            public void onClick(View view) {
                if (ContactActivity.this.isError()) {
                    if (ContactActivity.this.progressBar.getVisibility() == View.GONE) {
                        ContactActivity.this.progressBar.setVisibility(View.VISIBLE);
                    }
                    if (ContactActivity.this.getConnection()) {
                        ContactActivity.this.sendBtn.setEnabled(false);
                        ContactActivity.this.msgFragment = SendMessageFragment.getInstance(ContactActivity.this.getSupportFragmentManager(), new String[]{ContactActivity.this.nameTxt.getText().toString(), ContactActivity.this.phoneTxt.getText().toString(), ContactActivity.this.emailTxt.getText().toString(), ContactActivity.this.messageTxt.getText().toString(), ContactActivity.this.companyTxt.getText().toString()});
                        return;
                    }
                    if (ContactActivity.this.progressBar.getVisibility() == View.VISIBLE) {
                        ContactActivity.this.progressBar.setVisibility(View.GONE);
                    }
                    ContactActivity.this.startActivityForResult(new Intent(ContactActivity.this, NoInternetActivity.class), 50);
                    ContactActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
        this.responseBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.sthoksdevs.sthoks.activities.ContactActivity.View$OnClickListenerC06365 */

            public void onClick(View view) {
                if (ContactActivity.this.responseLayout.getVisibility() == View.VISIBLE) {
                    ContactActivity.this.responseLayout.setVisibility(View.GONE);
                }
                ContactActivity.this.inputsLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p000v4.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.sendBtn.setEnabled(true);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p000v4.app.SupportActivity, android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("name", this.nameTxt.getText().toString());
        bundle.putString("phone", this.phoneTxt.getText().toString());
        bundle.putString("email", this.emailTxt.getText().toString());
        bundle.putString("message", this.messageTxt.getText().toString());
        bundle.putString("company", this.companyTxt.getText().toString());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.nameTxt.setText(bundle.getString("name"));
            this.phoneTxt.setText(bundle.getString("phone"));
            this.emailTxt.setText(bundle.getString("email"));
            this.messageTxt.setText(bundle.getString("message"));
            this.companyTxt.setText(bundle.getString("company"));
        }
        super.onRestoreInstanceState(bundle);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override // com.sthoksdevs.sthoks.interfaces.SendMessageInterface
    public void responseMessage(String str) {
        if (this.progressBar.getVisibility() == View.VISIBLE) {
            this.progressBar.setVisibility(View.GONE);
        }
        if (str.equals("Queued. Thank you.")) {
            this.nameTxt.setText((CharSequence) null);
            this.phoneTxt.setText((CharSequence) null);
            this.emailTxt.setText((CharSequence) null);
            this.messageTxt.setText((CharSequence) null);
            this.companyTxt.setText((CharSequence) null);
            this.responseTv.setText(getResources().getString(R.string.message_sent));
        } else {
            this.responseTv.setText(str);
        }
        this.sendBtn.setEnabled(true);
        if (this.inputsLayout.getVisibility() == View.VISIBLE) {
            this.inputsLayout.setVisibility(View.GONE);
        }
        this.responseLayout.setVisibility(View.VISIBLE);
        this.responseLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_up));
    }
}
