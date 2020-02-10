package com.example.whatsapptutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    Button mLogOut;
    private Toolbar mToolBar;
    private TabAdapter mTabAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    AppBarLayout appBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        AppCompatActivity appCompatActivity;

        mToolBar=findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar);
        mViewPager=findViewById(R.id.viewPager);
        mTabLayout=findViewById(R.id.tab_layout);
        mTabAdapter=new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.cam);
        LinearLayout layout = ((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 0.5f; // e.g. 0.5f
        layout.setLayoutParams(layoutParams);
        mTabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#9ECAC9"), PorterDuff.Mode.SRC_IN);
        mViewPager.setCurrentItem(1);

        appBar=findViewById(R.id.AppBarLayout);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    tab.getIcon().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
                  //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                }
                //  else
                    //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);




            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0)
                    tab.getIcon().setColorFilter(Color.parseColor("#9ECAC9"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int hideAppBarAtPosition = 0;

                if (appBar != null) {

                    // get an instance of the bottom value of the appBar
                    float mBottom = (float) appBar.getBottom();

                    // going right offset changes from zero to one
                    if (position == hideAppBarAtPosition) {

                        // Translate the appBar up as the resideReveal slides into view
                        float y = (positionOffset * mBottom) - mBottom;

                        // Raise the appBar a little bit higher when it is no longer visible
                        if (y == -mBottom) {
                            float h = (float) appBar.getHeight();
                            if (mBottom < h) mBottom = h;
                            y = -mBottom - mBottom / 8f;
                        }

                        appBar.setTranslationY(y);

                    } else if (position == hideAppBarAtPosition - 1) {

                        // Translate the appBar up as the resideReveal slides into view
                        appBar.setTranslationY(-(positionOffset * mBottom));

                    } else if (appBar.getTranslationY() != 0f) {

                        // Reset translation of the appBar
                        appBar.setTranslationY(0f);

                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        /*mLogOut=findViewById(R.id.logout);
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(HomePage.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });*/
    }
}
