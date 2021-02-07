package com.sthoksdevs.sthoks.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sthoksdevs.sthoks.fragments.AboutFragment;
import com.sthoksdevs.sthoks.fragments.EducationFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    @Override // android.support.p000v4.view.PagerAdapter
    public int getCount() {
        return 2;
    }

    @Override // android.support.p000v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "About Me";
            case 1:
                return "Education & Skills";
            default:
                return null;
        }
    }

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override // android.support.p000v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new AboutFragment();
            case 1:
                return new EducationFragment();
            default:
                return null;
        }
    }
}
