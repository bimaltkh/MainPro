package com.example.wifi.TabActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wifi.DeviceList;
import com.example.wifi.R;
import com.google.android.material.tabs.TabLayout;

import java.text.BreakIterator;

public class TabMainActivity extends AppCompatActivity {
    private static final int ARG_SECTION_NUMBER = 1;


    private ViewPager mViewPager;
    String itemValue;
    Bundle bundle;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);



       /* Bundle bundle = getIntent().getExtras();


            String title = bundle.getString("key_1");
*/

//      Toolbar toolbar=findViewById(R.id.toolbar2);
//      setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.viewpage);
        mViewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
         bundle= getIntent().getExtras();
        tabLayout.setupWithViewPager(mViewPager);






    /*    //get the current intent

        Intent intent = getIntent();
//get the attached extras from the intent
//we should use the same key as we used to attach the data.
        String user_name = intent.getStringExtra("USER_NAME");
*/


//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.tabs);
//        if (fragment == null) {
//            fragment = new Tab1();
//            ;
//            fm.beginTransaction()
//                    .add(R.id.tabs, fragment)
//                    .commit();
//        }


    }




    class SectionsPagerAdapter extends FragmentPagerAdapter {
        //@StringRes
        private final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4};
        Context mContext;




        public SectionsPagerAdapter(Context context, FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
            mContext= context;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    Tab1 tab1=new Tab1();
                    return tab1;
                case 1:

                    Tab2 tab2=new Tab2();
                    return tab2;
                case 2:
                    Tab3 tab3=new Tab3();
                    return tab3;


            }
            return null;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getResources().getString(TAB_TITLES[position]);
        }



        @Override
        public int getCount() {
            return 3;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

    }

}
