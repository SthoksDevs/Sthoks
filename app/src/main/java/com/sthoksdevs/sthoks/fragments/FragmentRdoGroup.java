package com.sthoksdevs.sthoks.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.activities.QuizActivity;
import com.sthoksdevs.sthoks.interfaces.RdoGroupInterface;

public class FragmentRdoGroup extends Fragment {
    private static final String SKILL_KEY = "SKILLKEY";
    public static final String TAG = "RadioGroupFragment";
    String[] answersArray;
    private RdoGroupInterface mCallback;
    private int pos;
    RadioGroup radioGroup;
    RadioButton rdoBtn;
    private String skill;

    @Override // android.support.p000v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // android.support.p000v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.rdo_fragment, viewGroup, false);
        this.radioGroup = (RadioGroup) inflate.findViewById(R.id.rdoGroup);
        return inflate;
    }

    @Override // android.support.p000v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        QuizActivity quizActivity = (QuizActivity) context;
        this.mCallback = quizActivity;
        this.skill = quizActivity.getIntent().getStringExtra("skill");
        this.pos = quizActivity.questionPos;
    }

    @Override // android.support.p000v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.answersArray = getResources().getStringArray(getResources().getIdentifier(this.skill + "_" + this.pos, "array", getActivity().getPackageName()));
        String[] strArr = this.answersArray;
        for (String str : strArr) {
            this.rdoBtn = new RadioButton(getActivity());
            this.rdoBtn.setId(View.generateViewId());
            this.rdoBtn.setText(str);
            this.rdoBtn.setTextColor(getResources().getColor(R.color.white));
            this.radioGroup.addView(this.rdoBtn);
        }
        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /* class com.sthoksdevs.sthoks.fragments.FragmentRdoGroup.C06551 */

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentRdoGroup.this.rdoBtn = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                if (FragmentRdoGroup.this.rdoBtn.isChecked()) {
                    FragmentRdoGroup.this.mCallback.updateAnswer(FragmentRdoGroup.this.rdoBtn.getText().toString());
                }
            }
        });
    }

    @Override // android.support.p000v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mCallback = null;
    }
}
