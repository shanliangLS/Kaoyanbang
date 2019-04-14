package hehut.scse.kaoyanbang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import hehut.scse.kaoyanbang.TabFragment.CustomScrollViewPager;
import hehut.scse.kaoyanbang.TabFragment.TabFragment1;
import hehut.scse.kaoyanbang.TabFragment.TabFragment2;
import hehut.scse.kaoyanbang.TabFragment.TabFragment3;

public class MainActivity extends AppCompatActivity {

    // ViewPager
    private CustomScrollViewPager mViewPager;
    // 适配器
    private FragmentPagerAdapter mAdapter;
    // 装载fragment
    private List<Fragment> mFragments;
    // 底部导航栏
    private BottomNavigationView navigation;

    // 底部导航栏监听
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewpager);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initTabFragment();
        mViewPager.setCurrentItem(1); // 设置默认页
    }

    // 初始化Fragment适配器
    private void initTabFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new TabFragment1());
        mFragments.add(new TabFragment2());
        mFragments.add(new TabFragment3());

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                navigation.setSelectedItemId(navigation.getMenu().getItem(position).getItemId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
