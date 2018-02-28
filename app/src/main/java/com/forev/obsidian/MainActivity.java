package com.forev.obsidian;


import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;

import android.graphics.drawable.Drawable;

import android.graphics.drawable.LayerDrawable;

import android.graphics.drawable.TransitionDrawable;

import android.os.Build;

import android.os.Bundle;

import android.os.Handler;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;

import android.util.Log;
import android.util.TypedValue;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;



import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {



    private final Handler handler = new Handler();



    private PagerSlidingTabStrip tabs;

    private ViewPager pager;

    private MyPagerAdapter adapter;



    private Drawable oldBackground = null;

    private int currentColor = 0xFF666666;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        pager = (ViewPager) findViewById(R.id.pager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());


        findViewById(R.id.checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> basket=new ArrayList<>();

                for (int i=0;i<Grains.getArray().size();i++)
                {
                    basket.add(Grains.getArray().get(i));
                }
                for (int i=0;i<Pulses.getArray().size();i++)
                {
                    basket.add(Pulses.getArray().get(i));
                }
                for (int i=0;i<Cosmetics.getArray().size();i++)
                {
                    basket.add(Cosmetics.getArray().get(i));
                }
                for (int i=0;i<Others.getArray().size();i++)
                {
                    basket.add(Others.getArray().get(i));
                }

                for (int i=0;i<basket.size();i++)
                {
                    Log.d("Basket "+i+" :",basket.get(i));
                }
                startActivity(new Intent(MainActivity.this,CartActivity.class).putStringArrayListExtra("basket",basket));


            }
        });
        pager.setAdapter(adapter);



        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()

                .getDisplayMetrics());

        pager.setPageMargin(pageMargin);



        tabs.setViewPager(pager);



    }










    @Override

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt("currentColor", currentColor);

    }



    @Override

    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        currentColor = savedInstanceState.getInt("currentColor");


    }



    private Drawable.Callback drawableCallback = new Drawable.Callback() {

        @Override

        public void invalidateDrawable(Drawable who) {

            getActionBar().setBackgroundDrawable(who);

        }



        @Override

        public void scheduleDrawable(Drawable who, Runnable what, long when) {

            handler.postAtTime(what, when);

        }



        @Override

        public void unscheduleDrawable(Drawable who, Runnable what) {

            handler.removeCallbacks(what);

        }

    };

    public class MyPagerAdapter extends FragmentPagerAdapter {



        private final String[] TITLES = { "Grains", "Pulses", "Beauty & Hygiene", "Others"};



        public MyPagerAdapter(FragmentManager fm) {

            super(fm);

        }



        @Override

        public CharSequence getPageTitle(int position) {

            return TITLES[position];

        }



        @Override

        public int getCount() {

            return TITLES.length;

        }



        @Override

        public Fragment getItem(int position) {

        switch (position)
        {
            case 0: return new Grains();
            case 1: return new Pulses();
            case 2: return new Cosmetics();
            case 3: return new Others();
        }
        return null;
        }



    }



}