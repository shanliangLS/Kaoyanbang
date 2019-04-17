package hehut.scse.kaoyanbang.TabFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Player.Fragments.PlayerFragment1;
import hehut.scse.kaoyanbang.TabFragment.Player.PlayerDetail;
import hehut.scse.kaoyanbang.TabFragment.Player.VideoDetail;
import hehut.scse.kaoyanbang.home.Recommend0Activity;
import hehut.scse.kaoyanbang.home.Recommend1Activity;
import hehut.scse.kaoyanbang.home.Recommend2Activity;
import hehut.scse.kaoyanbang.util.ExpandListView;

public class TabFragment2 extends Fragment implements OnBannerListener {
    private static List<HomeCardItem> homeCardList;
    private ExpandListView homeCardListView;

    private RefreshLayout mRefreshLayout;

    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;

    // 轮播图
    private Banner mBanner;

    static {
        homeCardList = new ArrayList<>();
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1, "如何正确地吐槽"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment2, container, false);

        homeCardListView = view.findViewById(R.id.home_list_view);
        homeCardListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return homeCardList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemview = null;

                // 如果缓存为空，生成新的布局作为1个item
                if (convertView == null) {
//                    Log.i("videoListInfo:", "没有缓存，重新生成" + position);
                    LayoutInflater videoInflater = TabFragment2.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.home_card, null);
                } else {
//                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final HomeCardItem homeCard = homeCardList.get(position);
                // 图片
                ImageView cardCover = (ImageView) itemview.findViewById(R.id.home_card_pic);
                cardCover.setImageResource(homeCard.getPicPath());
                // 标题
                TextView videoTitle = (TextView) itemview.findViewById(R.id.home_card_title);
                videoTitle.setText(homeCard.getTitle());

                // 点击事件
                /*CardView cardView = itemview.findViewById(R.id.video_card);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), PlayerDetail.class));
                    }
                });*/

                return itemview;
            }
        });

        mRefreshLayout = view.findViewById(R.id.refreshLayout);


        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(3000/*,false*/);//传入false表示刷新失败
            }
        });

        // 轮播图
        mBanner = (Banner) view.findViewById(R.id.banner);
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.carousel1);
        imagePath.add(R.drawable.carousel2);
        imagePath.add(R.drawable.carousel3);
        imageTitle.add("河北工业大学");
        imageTitle.add("北京邮电大学");
        imageTitle.add("天津大学");

        return view;
    }

    // 初始化轮播图
    private void initBannerView() {
        if (mBanner != null) {
            //设置样式，里面有很多种样式可以自己都看看效果
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
            mBanner.setImageLoader(new MyImageLoader());
            //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
            mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
            //轮播图片的文字
            mBanner.setBannerTitles(imageTitle);
            //设置轮播间隔时间
            mBanner.setDelayTime(3000);
            //设置是否为自动轮播，默认是true
            mBanner.isAutoPlay(true);
            //设置指示器的位置，小点点，居中显示
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //设置图片加载地址
            mBanner.setImages(imagePath)
                    //轮播图的监听
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            if (position == 0) {
                                Intent intent = new Intent(getActivity(), Recommend0Activity.class);
                                startActivity(intent);
                            } else if (position == 1) {
                                Intent intent = new Intent(getActivity(), Recommend1Activity.class);
                                startActivity(intent);
                            } else if (position == 2) {
                                Intent intent = new Intent(getActivity(), Recommend2Activity.class);
                                startActivity(intent);
                            }
                        }
                    })
                    //开始调用的方法，启动轮播图。
                    .start();
        } else {
            Toast.makeText(getActivity(), "Banner初始化失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBannerView();
    }

    /**
     * 轮播图的监听
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
}
