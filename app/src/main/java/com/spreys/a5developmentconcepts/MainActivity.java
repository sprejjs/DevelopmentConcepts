package com.spreys.a5developmentconcepts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.dot_indicator)
    DotIndicator dotIndicator;

    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.btn_previous)
    Button btnPrevious;

    /**
     * Number of concepts to be displayed in the application
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget which displays the content of the concepts.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //This method has been intentionally left empty
            }

            @Override
            public void onPageSelected(int position) {
                dotIndicator.setSelectedItem(position, true);

                btnNext.setEnabled(position != NUM_PAGES - 1);
                btnPrevious.setEnabled(position != 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //This method has been intentionally left empty
            }
        });
    }

    @OnClick(R.id.btn_previous)
    public void openPreviousPage() {
        mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
    }

    @OnClick(R.id.btn_next)
    public void openNextPage() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }

    @OnClick(R.id.btn_java)
    public void selectJava() {
        mPagerAdapter.setProgrammingLanguage(ConceptFrameFragment.ProgrammingLanguage.Java);
    }

    @OnClick(R.id.btn_swift)
    public void setSwift() {
        mPagerAdapter.setProgrammingLanguage(ConceptFrameFragment.ProgrammingLanguage.Swift);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private List<ConceptFrameFragment> conceptFragments;

        public void setProgrammingLanguage(ConceptFrameFragment.ProgrammingLanguage language) {
            for (ConceptFrameFragment fragment : conceptFragments) {
                fragment.setProgrammingLanguage(language);
            }
        }

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            conceptFragments = new ArrayList<>();

            //Conditional Statement
            ConceptFrameFragment conditionalStatementFragment = new ConceptFrameFragment();
            conditionalStatementFragment.setConcept(ConceptFrameFragment.Concept.CONDITIONAL_STATEMENT);
            conceptFragments.add(conditionalStatementFragment);

            //Switch statement
            ConceptFrameFragment switchStatementFragment = new ConceptFrameFragment();
            switchStatementFragment.setConcept(ConceptFrameFragment.Concept.SWITCH_STATEMENT);
            conceptFragments.add(switchStatementFragment);

            //For loop
            ConceptFrameFragment forStatementFragment = new ConceptFrameFragment();
            forStatementFragment.setConcept(ConceptFrameFragment.Concept.FOR_LOOP);
            conceptFragments.add(forStatementFragment);

            //While loop
            ConceptFrameFragment whileStatementFragment = new ConceptFrameFragment();
            whileStatementFragment.setConcept(ConceptFrameFragment.Concept.WHILE_LOOP);
            conceptFragments.add(whileStatementFragment);

            //Enum
            ConceptFrameFragment enumStatementFragment = new ConceptFrameFragment();
            enumStatementFragment.setConcept(ConceptFrameFragment.Concept.ENUM);
            conceptFragments.add(enumStatementFragment);
        }

        @Override
        public Fragment getItem(int position) {
            return conceptFragments.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
