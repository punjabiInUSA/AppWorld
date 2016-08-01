package com.learnandroid.appworld;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NavigationDrawerActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {


    private ArrayAdapter<String> mAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        enableHomeButton();
        setupHamburgerIcon();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enableHomeButton();
        setupHamburgerIcon();
        NavigationView navView = (NavigationView) findViewById(R.id.navigationViewLayout);
        navView.setNavigationItemSelectedListener(this);

    }




    /**
     * Enables Android Home Button, otherwise known as UP button.
     */
    public void enableHomeButton() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    /**
     * Replace <- up button icon, to = for navigation drawer
     */

    public void setupHamburgerIcon() {
        //String resources are required to be created before correct functionality can be used
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Open");
                    invalidateOptionsMenu();
                }
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
                    invalidateOptionsMenu();
                }
            }
        };

        //Enables actual icon visibility for drawer navigation
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        //Enables listener functionality for open and close nav drawer
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        //Sync everything together with Nav drawer toggle
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_delete_note:
                Toast.makeText(NavigationDrawerActivity.this, "Delete Requested",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_menu_logout:
                Toast.makeText(NavigationDrawerActivity.this, "Logout Requested",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                mDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_new_note:
                Intent i = new Intent(this, UserActivity.class);
                startActivity(i);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_help:
                Toast.makeText(NavigationDrawerActivity.this,
                        "help is on the way", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }
}