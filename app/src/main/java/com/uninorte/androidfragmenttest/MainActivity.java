
package com.uninorte.androidfragmenttest;


import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private FragmentGps fragment_Map;
    private FragmentAcc fragment_Acc;
    private FragmentOth fragment_Oth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTabs();
    }

    private void setupTabs(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("GPS")
                .setTabListener(new SupportFragmentTabListener<FragmentGps>(R.id.container, this, "GPS", FragmentGps.class));
        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Acelerometro")
                .setTabListener(new SupportFragmentTabListener<FragmentAcc>(R.id.container, this, "Acelerometro", FragmentAcc.class));
        actionBar.addTab(tab2);

        ActionBar.Tab tab3 = actionBar
                .newTab()
                .setText("Otros")
                .setTabListener(new SupportFragmentTabListener<FragmentOth>(R.id.container, this, "Otros", FragmentOth.class));
        actionBar.addTab(tab3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
