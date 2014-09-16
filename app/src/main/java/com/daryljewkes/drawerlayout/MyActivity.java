package com.daryljewkes.drawerlayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
//    private String[] planets;
    private ActionBarDrawerToggle drawerListener;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        planets = getResources().getStringArray(R.array.planets);
        listView = (ListView) findViewById(R.id.drawerListLeft);
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planets));
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MyActivity.this, " Drawer Opened", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MyActivity.this, " Drawer Closed", Toast.LENGTH_SHORT ).show();
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, planets[position] + " was selected", Toast.LENGTH_LONG).show();
        selectItem(position);
    }

    public void selectItem(int position) {
        listView.setItemChecked(position, true);
//        setTitle(planets[position]);
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}

class MyAdapter extends BaseAdapter {
    private Context context;
    String[] socialSites;
    int[] images = {R.drawable.ic_facebook, R.drawable.ic_twitter, R.drawable.ic_google_plus};

    public MyAdapter(Context context){
        this.context = context;
        socialSites = context.getResources().getStringArray(R.array.social);
    }
    @Override
    public int getCount() {
        return socialSites.length;
//        return 0;
    }

    @Override
    public Object getItem(int position) {
        return socialSites[position];
//        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
//        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row, parent, false);
        }
        else {
            row = convertView;
        }

        TextView titleTextView = (TextView) row.findViewById(R.id.textView1);
        ImageView titleImageView = (ImageView) row.findViewById(R.id.imageView1);
        titleTextView.setText(socialSites[position]);
        titleImageView.setImageResource(images[position]);

        return row;
    }
}

