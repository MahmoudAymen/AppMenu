package com.example.aymen.appmenu;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.exemple.aymen.adapter;
import com.exemple.aymen.fragments.fragment1;
import com.exemple.aymen.fragments.fragment2;
import com.exemple.aymen.fragments.fragment3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener{
    ViewPager viewPager;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //viewPager= (ViewPager) findViewById(R.id.view_pager);
        initViewPage();
        initTabHost();

    }

   private void initTabHost() {
        tabHost= (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        String[]tabNames={"Tab1","Tab2","Tab3","Tab4","Tab5","Tab6"};
        for(int i=0;i<tabNames.length;i++)
        {
            TabHost.TabSpec tabSpec;
            tabSpec=tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new fakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
       tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int i=tabHost.getCurrentTab();
        viewPager.setCurrentItem(i);
        HorizontalScrollView s= (HorizontalScrollView) findViewById(R.id.h_scroll_view);
        View viewtab=tabHost.getCurrentTabView();
        int ScrollPos=viewtab.getLeft()-(s.getWidth()-viewtab.getWidth())/2;
        s.smoothScrollTo(ScrollPos,0);
    }

    public class fakeContent implements TabHost.TabContentFactory
    {
        Context context;
        public fakeContent(Context context)
        {
            this.context=context;
        }
        @Override
        public View createTabContent(String tag) {
            View v=new View(context);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }

    private void initViewPage()
    {
        viewPager= (ViewPager) findViewById(R.id.view_pager);
        List<Fragment>fragmentList=new ArrayList<Fragment>();
        fragmentList.add(new fragment1());
        fragmentList.add(new fragment2());
        fragmentList.add(new fragment3());
        fragmentList.add(new fragment1());
        fragmentList.add(new fragment2());
        fragmentList.add(new fragment3());
        adapter adapter=new adapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }
}
