package com.dashboard.orange.orangedashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dashboard.orange.orangedashboard.Adapters.NavigationDrawerAdapter;
import com.dashboard.orange.orangedashboard.Fragments.AnalyticsFragment;
import com.dashboard.orange.orangedashboard.Fragments.CouponFragment;
import com.dashboard.orange.orangedashboard.Fragments.InfoFragment;
import com.dashboard.orange.orangedashboard.Fragments.Info_SubcategorySelectionFragment;

public class MainActivity extends ActionBarActivity
{

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    public FragmentManager.BackStackEntry backEntry;

    String TITLES[] = {"Analytics", "Info", "Showcase", "Posts", "Gallery", "Reviews", "Coupons", "Buy Credits"};
    int ICONS[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String STORE_NAME = "I LOVE KAPDA";
    String OWNER_EMAIL = "akash.bangad@android4devs.com";
    int PROFILE_IMAGE = R.mipmap.ic_launcher;
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    private Toolbar toolbar;                              // Declaring the Toolbar Object
    public AppManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new NavigationDrawerAdapter(TITLES, ICONS, STORE_NAME, OWNER_EMAIL, PROFILE_IMAGE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        {

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        recyclerViewClickEvent();

        manager = (AppManager) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void recyclerViewClickEvent()
    {
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {

                        Log.d("CLICKED", "Clicked on item" + position);
                        switch (position)
                        {
                            case 1:
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.container, AnalyticsFragment.newInstance(), "AnalyticsFragment")
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                        .addToBackStack("AnalyticsFragment")
                                        .commit();
                                Drawer.closeDrawers();
                                break;
                            case 2:
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.container, InfoFragment.newInstance(), "InfoFragment")
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                        .addToBackStack("InfoFragment")
                                        .commit();
                                Drawer.closeDrawers();
                                break;
                            case 7:
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.container, CouponFragment.newInstance(), "CouponFragment")
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                        .addToBackStack("CouponFragment")
                                        .commit();
                                Drawer.closeDrawers();
                                break;

                        }
                    }
                })
        );
    }

    @Override
    public void onBackPressed()
    {
        // super.onBackPressed();
        if(getFragmentManager().getBackStackEntryCount() > 0)
        {
            Fragment currentFragment = getFragmentManager().findFragmentById(R.id.container);
            if (currentFragment instanceof Info_SubcategorySelectionFragment)
            {
                ((Info_SubcategorySelectionFragment) currentFragment).goBackToPreviousFragment();
            }
            backEntry = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1);
            Fragment myFrag = getFragmentManager().findFragmentByTag(backEntry.getName());
            Log.d("BackStack", "" + backEntry.getName());
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, myFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
/*            if(!getSupportActionBar().isShowing())
                getSupportActionBar().show();*/

        }
        /*FragmentManager.BackStackEntry backEntry= getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount()-1);
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(getApplicationContext(), backEntry);*/


    }

}