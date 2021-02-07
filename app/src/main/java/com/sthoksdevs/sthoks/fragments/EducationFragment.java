package com.sthoksdevs.sthoks.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.activities.QuizActivity;

public class EducationFragment extends Fragment {
    ImageView angularImv;
    LinearLayout angularL;
    ImageView bootstrapImv;
    LinearLayout bootstrapL;
    ImageView cImv;

    /* renamed from: cL */
    LinearLayout f63cL;
    private View.OnClickListener clicks = new View.OnClickListener() {
        /* class com.sthoksdevs.sthoks.fragments.EducationFragment.View$OnClickListenerC06541 */

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.angular:
                    Intent intent = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent.putExtra("skill", "angular");
                    EducationFragment.this.startActivity(intent);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.bootstrap:
                    Intent intent2 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent2.putExtra("skill", "bootstrap");
                    EducationFragment.this.startActivity(intent2);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.c_prog:
                    Intent intent3 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent3.putExtra("skill", "c");
                    EducationFragment.this.startActivity(intent3);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.c_sharp:
                    Intent intent4 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent4.putExtra("skill", "csharp");
                    EducationFragment.this.startActivity(intent4);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.css3:
                    Intent intent5 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent5.putExtra("skill", "css");
                    EducationFragment.this.startActivity(intent5);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.github:
                    Intent intent6 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent6.putExtra("skill", "github");
                    EducationFragment.this.startActivity(intent6);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.html5:
                    Intent intent7 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent7.putExtra("skill", "html");
                    EducationFragment.this.startActivity(intent7);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.jquery:
                    Intent intent8 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent8.putExtra("skill", "jquery");
                    EducationFragment.this.startActivity(intent8);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.js:
                    Intent intent9 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent9.putExtra("skill", "javascript");
                    EducationFragment.this.startActivity(intent9);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.laravel:
                    Intent intent10 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent10.putExtra("skill", "laravel");
                    EducationFragment.this.startActivity(intent10);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.mysql:
                    Intent intent11 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent11.putExtra("skill", "mysql");
                    EducationFragment.this.startActivity(intent11);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.photoshop:
                    Intent intent12 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent12.putExtra("skill", "photoshop");
                    EducationFragment.this.startActivity(intent12);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.php:
                    Intent intent13 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent13.putExtra("skill", "php");
                    EducationFragment.this.startActivity(intent13);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.wordpress:
                    Intent intent14 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent14.putExtra("skill", "wordpress");
                    EducationFragment.this.startActivity(intent14);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                case R.id.xd:
                    Intent intent15 = new Intent(EducationFragment.this.getActivity(), QuizActivity.class);
                    intent15.putExtra("skill", "xd");
                    EducationFragment.this.startActivity(intent15);
                    EducationFragment.this.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return;
                default:
                    return;
            }
        }
    };
    ImageView csImv;
    LinearLayout csL;
    ImageView css3Imv;
    LinearLayout cssL;
    ImageView githubImv;
    LinearLayout githubL;
    ImageView html5Imv;
    LinearLayout htmlL;
    ImageView jqueryImv;
    LinearLayout jqueryL;
    ImageView jsImv;
    LinearLayout jsL;
    ImageView laravelImv;
    LinearLayout laravelL;
    ImageView mysqlImv;
    LinearLayout mysqlL;
    ImageView phpImv;
    LinearLayout phpL;
    ImageView psImv;
    LinearLayout psL;
    ImageView wordpressImv;
    LinearLayout wpL;
    ImageView xdImv;
    LinearLayout xdL;

    @Override // android.support.p000v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_education, viewGroup, false);
    }

    @Override // android.support.p000v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.phpImv = (ImageView) getActivity().findViewById(R.id.php_logo);
        this.phpL = (LinearLayout) getActivity().findViewById(R.id.php);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.php)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.phpImv);
        this.phpL.setOnClickListener(this.clicks);
        this.html5Imv = (ImageView) getActivity().findViewById(R.id.html5_logo);
        this.htmlL = (LinearLayout) getActivity().findViewById(R.id.html5);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.html5)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.html5Imv);
        this.htmlL.setOnClickListener(this.clicks);
        this.css3Imv = (ImageView) getActivity().findViewById(R.id.css3_logo);
        this.cssL = (LinearLayout) getActivity().findViewById(R.id.css3);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.css_3)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.css3Imv);
        this.cssL.setOnClickListener(this.clicks);
        this.csImv = (ImageView) getActivity().findViewById(R.id.cs_logo);
        this.csL = (LinearLayout) getActivity().findViewById(R.id.c_sharp);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.c_sharp)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.csImv);
        this.csL.setOnClickListener(this.clicks);
        this.cImv = (ImageView) getActivity().findViewById(R.id.c_logo);
        this.f63cL = (LinearLayout) getActivity().findViewById(R.id.c_prog);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.c_lang)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.cImv);
        this.f63cL.setOnClickListener(this.clicks);
        this.wordpressImv = (ImageView) getActivity().findViewById(R.id.wp_logo);
        this.wpL = (LinearLayout) getActivity().findViewById(R.id.wordpress);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.wordpress)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.wordpressImv);
        this.wpL.setOnClickListener(this.clicks);
        this.mysqlImv = (ImageView) getActivity().findViewById(R.id.mysql_logo);
        this.mysqlL = (LinearLayout) getActivity().findViewById(R.id.mysql);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.mysql)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.mysqlImv);
        this.mysqlL.setOnClickListener(this.clicks);
        this.angularImv = (ImageView) getActivity().findViewById(R.id.angular_logo);
        this.angularL = (LinearLayout) getActivity().findViewById(R.id.angular);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.angular)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.angularImv);
        this.angularL.setOnClickListener(this.clicks);
        this.jsImv = (ImageView) getActivity().findViewById(R.id.js_logo);
        this.jsL = (LinearLayout) getActivity().findViewById(R.id.js);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.js)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.jsImv);
        this.jsL.setOnClickListener(this.clicks);
        this.jqueryImv = (ImageView) getActivity().findViewById(R.id.jquery_logo);
        this.jqueryL = (LinearLayout) getActivity().findViewById(R.id.jquery);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.jquery)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.jqueryImv);
        this.jqueryL.setOnClickListener(this.clicks);
        this.laravelImv = (ImageView) getActivity().findViewById(R.id.laravel_logo);
        this.laravelL = (LinearLayout) getActivity().findViewById(R.id.laravel);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.laravel)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.laravelImv);
        this.laravelL.setOnClickListener(this.clicks);
        this.xdImv = (ImageView) getActivity().findViewById(R.id.xd_logo);
        this.xdL = (LinearLayout) getActivity().findViewById(R.id.xd);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.adobe_xd)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.xdImv);
        this.xdL.setOnClickListener(this.clicks);
        this.bootstrapImv = (ImageView) getActivity().findViewById(R.id.bootstrap_logo);
        this.bootstrapL = (LinearLayout) getActivity().findViewById(R.id.bootstrap);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.bootstrap)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.bootstrapImv);
        this.bootstrapL.setOnClickListener(this.clicks);
        this.githubImv = (ImageView) getActivity().findViewById(R.id.github_logo);
        this.githubL = (LinearLayout) getActivity().findViewById(R.id.github);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.github)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.githubImv);
        this.githubL.setOnClickListener(this.clicks);
        this.psImv = (ImageView) getActivity().findViewById(R.id.ps_logo);
        this.psL = (LinearLayout) getActivity().findViewById(R.id.photoshop);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.drawable.photoshop)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).override(50, 50).centerCrop()).into(this.psImv);
        this.psL.setOnClickListener(this.clicks);
    }
}
