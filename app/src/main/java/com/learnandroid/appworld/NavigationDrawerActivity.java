package com.learnandroid.appworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NavigationDrawerActivity extends AppCompatActivity{

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        mDrawerList = (ListView) findViewById(R.id.navList);
        addDrawerListItems();
        onDrawerItemClickHandler();

    }


    public void addDrawerListItems(){
        String[] osArray = {"Android", "iOS", "Windows", "OSX" , "Linux"};
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,osArray);
        mDrawerList.setAdapter(mAdapter);
    }


   public void onDrawerItemClickHandler(){
       mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               switch (i) {
                   case 0:
                       Toast.makeText(NavigationDrawerActivity.this, "Time for iOS", Toast.LENGTH_SHORT).show();
                       break;
                   case 1:
                       Toast.makeText(NavigationDrawerActivity.this, "Time for Android", Toast.LENGTH_SHORT).show();
                       break;
                   case 2:
                       Toast.makeText(NavigationDrawerActivity.this, "Time for MAC OS", Toast.LENGTH_SHORT).show();
                       break;
                   case 3:
                       Toast.makeText(NavigationDrawerActivity.this, "Linux is best", Toast.LENGTH_SHORT).show();
               }
           }
       });
   }

    
}
