package com.nutra_o.nutra_o.activitys;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import com.nutra_o.nutra_o.fragments.IndkoebslisterFragment;
import com.nutra_o.nutra_o.fragments.NavigationDrawerFragment;
import com.nutra_o.nutra_o.fragments.StartFragment;
import com.nutra_o.nutra_o.fragments.SundhedFragment;
import com.nutra_o.nutra_o.models.ApplicationImpl;
import com.nutra_o.nutra_o.models.ApplicationModel;
import com.nutra_o.nutra_o.R;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends ActionBarActivity implements Observer{


    private Toolbar toolbar;

    NavigationDrawerFragment drawerFragment;
    DrawerLayout Drawer;

    ApplicationImpl application;
    ApplicationModel model;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        application = (ApplicationImpl) getApplicationContext();
        model = application.getModel();
        model.addObserver(this);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view

        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUpDrawerFragment(toolbar, Drawer);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.root_frag,new StartFragment());
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.navigate){

            startActivity(new Intent(this,test.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // called when model is changed
    @Override
    public void update(Observable observable, Object data) {

    }

    // called back from recycler view adapter
    public void onMenuItemSelected(int menuItem){

        System.out.println("Main activity catched event: "+menuItem);


        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;

        if(menuItem == 1){ // sundheds fragment

            fragment = new SundhedFragment();

        }else if (menuItem == 2) { // indkøbslister

            fragment = new IndkoebslisterFragment();

        } else if (menuItem == 3){ // opskrifter

        } else if (menuItem == 4){ // mad lager

        } else if (menuItem == 5){ // madplaner

        } else if (menuItem == 6) { // indstillinger

        } else if (menuItem == 7){ // logud

        }

        if (fragment != null){
            fragmentTransaction.replace(R.id.root_frag, fragment);
            fragmentTransaction.commit();
        }

        Drawer.closeDrawer(Gravity.START);

    }

}
