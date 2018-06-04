package com.example.asus.project;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class Khoanvay extends AppCompatActivity implements OnFragmentInteractionListener {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    PaperAdapter adapter;
    static themkhoanvay tkv;
    static DS_khoanvay dskv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoanvay);
        tkv = new themkhoanvay();
        dskv = new DS_khoanvay();
        mViewPager = (ViewPager) findViewById(R.id.container4);
        tabLayout = (TabLayout) findViewById(R.id.tabs4);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new PaperAdapter(manager);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(String name) {
        adapter.onFragmentInteraction(name);
    }

    public class PaperAdapter extends FragmentStatePagerAdapter implements OnFragmentInteractionListener {


        PaperAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return tkv;
                case 1:
                    return  dskv;
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position){
                case 0:
                    title = "1- THÊM KHOẢN VAY";
                    break;
                case 1:
                    title = "2- DANH SÁCH KHOẢN VAY";
                    break;

            }
            return title;
        }

        @Override
        public void onFragmentInteraction(String name) {
            dskv.onFragmentInteraction(name);
        }
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(Khoanvay.this,MainActivity.class);
        startActivity(i);
    }
}
