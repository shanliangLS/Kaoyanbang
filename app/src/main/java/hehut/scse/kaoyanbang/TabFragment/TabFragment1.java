package hehut.scse.kaoyanbang.TabFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Player.Fragments.PlayerFragment1;
import hehut.scse.kaoyanbang.TabFragment.Player.Fragments.PlayerFragment2;
import hehut.scse.kaoyanbang.TabFragment.Player.Fragments.PlayerFragment3;
import hehut.scse.kaoyanbang.TabFragment.Player.Fragments.PlayerFragment4;

public class TabFragment1 extends Fragment {
//    private Unbinder unbinder;
    // ViewPager
    private ViewPager mViewPager;
    // 适配器
    private FragmentPagerAdapter mAdapter;
    // 装载fragment
    private List<Fragment> mFragments;
    // 按钮组
//    private static ArrayList<PlayerToolbarItem> buttonArrayList;

//    @BindViews({R.id.player_toolbar1,R.id.player_toolbar2,R.id.player_toolbar3,R.id.player_toolbar4})
//    List<Button> toolbar;
//    @BindViews({R.id.player_toolbar_line1,R.id.player_toolbar_line2,R.id.player_toolbar_line3,R.id.player_toolbar_line4})
//    List<Button> toolbarLine;
//    @BindViews({R.id.player_toolbar_line_gray1,R.id.player_toolbar_line_gray2,R.id.player_toolbar_line_gray3,R.id.player_toolbar_line_gray4})
//    List<Button> toolbarLineGray;

    private static ArrayList<Button> buttonArrayList = new ArrayList<>();
    private static ArrayList<Button> buttonLineArrayList = new ArrayList<>();
    private static ArrayList<Button> buttonLineGrayArrayList = new ArrayList<>();

    static {
        buttonArrayList.add(null);
        buttonArrayList.add(null);
        buttonArrayList.add(null);
        buttonArrayList.add(null);
        buttonArrayList.add(null);

        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);

        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment1, container, false);
//        unbinder = ButterKnife.bind(this, view);

//        buttonArrayList = new ArrayList<>();
//        buttonArrayList.add(new PlayerToolbarItem((Button) view.findViewById(R.id.player_toolbar1),
//                (Button) view.findViewById(R.id.player_toolbar_line1),
//                (Button) view.findViewById(R.id.player_toolbar_line_gray1)));
//        buttonArrayList.add(new PlayerToolbarItem((Button) view.findViewById(R.id.player_toolbar2),
//                (Button) view.findViewById(R.id.player_toolbar_line2),
//                (Button) view.findViewById(R.id.player_toolbar_line_gray2)));
//        buttonArrayList.add(new PlayerToolbarItem((Button) view.findViewById(R.id.player_toolbar3),
//                (Button) view.findViewById(R.id.player_toolbar_line3),
//                (Button) view.findViewById(R.id.player_toolbar_line_gray3)));
//        buttonArrayList.add(new PlayerToolbarItem((Button) view.findViewById(R.id.player_toolbar4),
//                (Button) view.findViewById(R.id.player_toolbar_line4),
//                (Button) view.findViewById(R.id.player_toolbar_line_gray4)));

        buttonArrayList.set(0, (Button) view.findViewById(R.id.player_toolbar1));
        buttonArrayList.set(1, (Button) view.findViewById(R.id.player_toolbar2));
        buttonArrayList.set(2, (Button) view.findViewById(R.id.player_toolbar3));
        buttonArrayList.set(3, (Button) view.findViewById(R.id.player_toolbar4));

        buttonLineArrayList.set(0, (Button) view.findViewById(R.id.player_toolbar_line1));
        buttonLineArrayList.set(1, (Button) view.findViewById(R.id.player_toolbar_line2));
        buttonLineArrayList.set(2, (Button) view.findViewById(R.id.player_toolbar_line3));
        buttonLineArrayList.set(3, (Button) view.findViewById(R.id.player_toolbar_line4));

        buttonLineGrayArrayList.set(0, (Button) view.findViewById(R.id.player_toolbar_line_gray1));
        buttonLineGrayArrayList.set(1, (Button) view.findViewById(R.id.player_toolbar_line_gray2));
        buttonLineGrayArrayList.set(2, (Button) view.findViewById(R.id.player_toolbar_line_gray3));
        buttonLineGrayArrayList.set(3, (Button) view.findViewById(R.id.player_toolbar_line_gray4));

        mViewPager = view.findViewById(R.id.player_viewpager);
        mFragments = new ArrayList<>();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFragments.add(new PlayerFragment1());
        mFragments.add(new PlayerFragment2());
        mFragments.add(new PlayerFragment3());
        mFragments.add(new PlayerFragment4());

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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
//        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                for (int i = 0; i < mFragments.size(); i++) {
                    if (i == position) {
//                        buttonArrayList.get(i).setColor(true);
                        buttonArrayList.get(i).setTextColor(getResources().getColor(R.color.themeColor));
                        buttonLineArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.themeColor));
                        buttonLineGrayArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.themeColor));
                    } else {
//                        buttonArrayList.get(i).setColor(false);
                        buttonArrayList.get(i).setTextColor(getResources().getColor(R.color.colorPlayerToolbarFalse));
                        buttonLineArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.colorPlayerToolbar));
                        buttonLineGrayArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.colorPlayerToolbarFalseGray));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < mFragments.size(); i++) {
            buttonArrayList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < mFragments.size(); j++) {
                        if (buttonArrayList.get(j).getId() == v.getId()) {
//                            buttonArrayList.get(j).setColor(true);
                            buttonArrayList.get(j).setTextColor(getResources().getColor(R.color.themeColor));
                            buttonLineArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.themeColor));
                            buttonLineGrayArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.themeColor));
                            mViewPager.setCurrentItem(j);
                        } else {
//                            buttonArrayList.get(j).setColor(false);
                            buttonArrayList.get(j).setTextColor(getResources().getColor(R.color.colorPlayerToolbarFalse));
                            buttonLineArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.colorPlayerToolbar));
                            buttonLineGrayArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.colorPlayerToolbarFalseGray));
                        }
                    }
                }
            });
        }
    }
}
