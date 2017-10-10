package com.example.ibrahim.assignment_inteliment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TextView[] dots;
    private LinearLayout colorBackgroundView;
    private TextView tabLabelTV;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next:
                openScenario2();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step 1 using tabs
        setUpTabs();

        //step 2 viewpager
        setUpViewPager();

        //step 5 button's parent cell
        colorBackgroundView = (LinearLayout) findViewById(R.id.parent3);

        //step 4 textview
        tabLabelTV = (TextView) findViewById(R.id.tab_tv);
    }

    private void setUpViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        //Internal Setting
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(MainActivity.this, "Page # "+ (position+1), Toast.LENGTH_SHORT).show();
                setBottomDotsPosition(position);
                //we can do more stuff here but as the requirement was just to display a toast that's what it is
                /**
                 * PagerFragment fragment = (PagerFragment) adapterViewPager.getItem(position);
                 * */
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

        // Add Dots
        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(this, R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        //starting with initial position
        setBottomDotsPosition(0);
    }

    private void setUpTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        TabLayout.Tab tab = tabLayout.newTab();
//        tab.setCustomView(R.layout.spinner_layout);
        TextView tab1TV = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab1TV.setText("Item 1 ");
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab1TV));

        TextView tab2TV = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2TV.setText("Item 2 ");
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab2TV));

        TextView tab3TV = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3TV.setText("Item 3 ");
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab3TV));

        TextView tab4TV = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab4TV.setText("Item 4 ");
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab4TV));

        TextView tab5TV = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab5TV.setText("Item 5 ");
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab5TV));
        //External setting
        tabLayout.addOnTabSelectedListener(this);
    }

    private void setBottomDotsPosition(int currentPage) {
        // we can put animations here and make it look more pretty
        for (int i = 0; i < dots.length; i++) {
            if (i != currentPage)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.dot_inactive));
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.black));
        }
    }

    public void buttonClick(View view) {
        switch (view.getId()){
            case R.id.button_1:
                colorBackgroundView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                break;
            case R.id.button_2:
                colorBackgroundView.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                break;
            case R.id.button_3:
                colorBackgroundView.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
                break;
        }

    }

    private void openScenario2() {
        startActivity(new Intent(this, SecondActivity.class));
    }
    //Tab layout listener methods
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabLabelTV.setText("Selected tab: "+(tab.getPosition()+1));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return PagerFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 1 - This will show PagerFragment different title
                    return PagerFragment.newInstance(1, "Page # 2");
                case 2:
                    return PagerFragment.newInstance(2, "Page # 3");
                case 3:
                    return PagerFragment.newInstance(2, "Page # 4");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
