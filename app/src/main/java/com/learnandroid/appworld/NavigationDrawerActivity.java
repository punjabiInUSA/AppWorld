package com.learnandroid.appworld;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NavigationDrawerActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        addDrawerListItems();
        onDrawerItemClickHandler();
        enableHomeButton();
        setupHamburgerIcon();

    }


    public void addDrawerListItems() {
        String[] osArray = {"Android", "iOS", "Windows", "OSX", "Linux"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }


    public void onDrawerItemClickHandler() {
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(NavigationDrawerActivity.this, "Time for iOS",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 1:
                        Toast.makeText(NavigationDrawerActivity.this, "Time for Android",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 2:
                        Toast.makeText(NavigationDrawerActivity.this, "Time for MAC OS",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 3:
                        Toast.makeText(NavigationDrawerActivity.this, "Linux is best",
                                Toast.LENGTH_SHORT).show();

                    case 4:
                        Toast.makeText(NavigationDrawerActivity.this, "You've reached the best",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawer(mDrawerList);
                }
            }
        });
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
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_delete_note).setVisible(!drawerOpen);
        menu.findItem(R.id.action_menu_logout).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
}