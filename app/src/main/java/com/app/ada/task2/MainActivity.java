package com.app.ada.task2;

import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


public class MainActivity extends ActionBarActivity implements BaseFragment.OnFragmentInteractionListener,
        NestedListFragment.OnFragmentInteractionListener, NestedGridFragment.OnFragmentInteractionListener {
    private String TAG = MainActivity.class.getCanonicalName();
    private String[] actions = new String[]{ "Sort by A-Z"," Sort by time"};

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    private Fragment mFragment;
    /*
    Options to display the fragment
         */
    private int currentDisplayStyle = Constants.DISPLAY_BY_LIST;//Either display by grid or list
    private int currentSortStyle = Constants.SORT_BY_ALPHABET;//Either sort by alphabet or time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        /*
        Action bar
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, actions);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                int selectedStyle = Constants.SORT_OPTION_ARRAY[itemPosition];
                if (selectedStyle != currentSortStyle)
                {
                    //If the selected style is different than the current one, refresh the fragment
                    currentSortStyle = selectedStyle;
                    mSectionsPagerAdapter.notifyDataSetChanged();
                }
                return false;
            }
        };
        getSupportActionBar().setListNavigationCallbacks(adapter, navigationListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        int selectedStyle= currentDisplayStyle;
        switch (id) {
            case (R.id.icon_view_by_list):
                selectedStyle = Constants.DISPLAY_BY_LIST;
                break;
            case (R.id.icon_view_by_grid):
                selectedStyle = Constants.DISPLAY_BY_GRID;
                break;
        }

        if(selectedStyle!= currentDisplayStyle)
        {
            currentDisplayStyle = selectedStyle;
            mSectionsPagerAdapter.notifyDataSetChanged();
        }
     return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            mFragment = BaseFragment.newInstance(position, currentDisplayStyle, currentSortStyle);
            return mFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }

            return null;
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onFragmentInteraction(String id) {
    }

    @Override
    public void onFragmentInteraction(MyListItem item) {
        //When a thumbnail item is clicked in the grid, go to an activity which displays the full image
        Intent intent = new Intent(this,ShowImageActivity.class);
        intent.putExtra("imageResId",item.getIconResId());
        startActivity(intent);
    }
}
