package com.sthoksdevs.sthoks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.sthoksdevs.sthoks.R;
import com.sthoksdevs.sthoks.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    private PagerAdapter mAdapter;
    private ViewPager mViewPager;
    int pressed = 0;

    /* access modifiers changed from: protected */
    @Override // android.support.p000v4.app.SupportActivity, android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mAdapter = new PagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(R.id.container);
        this.mViewPager.setAdapter(this.mAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(this.mViewPager));
        tabLayout.setupWithViewPager(this.mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_info_outline_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_school_white_24dp);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        this.drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_close) {
            return super.onOptionsItemSelected(menuItem);
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
        return true;
    }

    @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            case R.id.nav_contact:
                startActivity(new Intent(this, ContactActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            default:
                this.drawer.closeDrawer(GravityCompat.START);
                return false;
        }
    }

    @Override // android.support.p000v4.app.FragmentActivity
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
            return;
        }
        this.pressed++;
        if (this.pressed == 1) {
            Toast.makeText(this, "Press again to exit", 0).show();
            return;
        }
        this.pressed = 0;
        new Handler().postDelayed(new Runnable() {
            /* class com.sthoksdevs.sthoks.activities.MainActivity.RunnableC06371 */

            public void run() {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                MainActivity.this.startActivity(intent);
            }
        }, 300);
    }
}
