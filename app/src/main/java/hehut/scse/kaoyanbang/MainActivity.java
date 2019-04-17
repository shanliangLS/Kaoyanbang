package hehut.scse.kaoyanbang;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import hehut.scse.kaoyanbang.config.Config;
import hehut.scse.kaoyanbang.util.CustomScrollViewPager;
import hehut.scse.kaoyanbang.TabFragment.TabFragment1;
import hehut.scse.kaoyanbang.TabFragment.TabFragment2;
import hehut.scse.kaoyanbang.TabFragment.TabFragment3;
import hehut.scse.kaoyanbang.other.AboutActivity;
import hehut.scse.kaoyanbang.other.SettingActivity;

public class MainActivity extends AppCompatActivity {


    NavigationView mNavigationView;

    // ViewPager
    private CustomScrollViewPager mViewPager;
    // 适配器
    private FragmentPagerAdapter mAdapter;
    // 装载fragment
    private List<Fragment> mFragments;
    // 底部导航栏
    private BottomNavigationView navigation;


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    // 底部导航栏监听
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String TAG = "MainActivity";

    // 清除用户信息
    private void cleanUserInfo() {
        SharedPreferences pref = getApplication().getSharedPreferences(Config.Xml, Config.XmlModel);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        boolean success = editor.commit();
        if (success) {
            Log.e(TAG, "清除用户信息成功");
        } else {
            Log.e(TAG, "清除用户信息失败");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        verifyStoragePermissions(MainActivity.this);

        mNavigationView = findViewById(R.id.navigation);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_setting: {
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.drawer_about: {
                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.drawer_logout: {
                        cleanUserInfo();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    }
                }
                return false;
            }
        });


        mViewPager = findViewById(R.id.viewpager);

        navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initTabFragment();
        mViewPager.setCurrentItem(0); // 设置默认页
    }

    // 初始化Fragment适配器
    private void initTabFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new TabFragment2());
        mFragments.add(new TabFragment1());
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


    // 设置退出登录
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        String username = getUserName();
        Log.e(TAG, "用户名为: " + username);
        if (username == null) {
            Log.e(TAG, "用户名为null");
            finish();
        }
    }

    // 得到用户名
    private String getUserName() {
        SharedPreferences pref = getApplication().getSharedPreferences(Config.Xml, Config.XmlModel);
        return pref.getString(Config.username, null);
    }

    // 设置双击退出
    private static boolean mBackKeyPressed = false;

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
