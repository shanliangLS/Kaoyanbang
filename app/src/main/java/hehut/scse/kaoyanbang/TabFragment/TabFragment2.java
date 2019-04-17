package hehut.scse.kaoyanbang.TabFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
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
import hehut.scse.kaoyanbang.bean.MyNews;
import hehut.scse.kaoyanbang.bean.MyNewsResponse;
import hehut.scse.kaoyanbang.config.Config;
import hehut.scse.kaoyanbang.helper.NetworkHelper;
import hehut.scse.kaoyanbang.home.Recommend0Activity;
import hehut.scse.kaoyanbang.home.Recommend1Activity;
import hehut.scse.kaoyanbang.home.Recommend2Activity;
import hehut.scse.kaoyanbang.util.ExpandListView;
import okhttp3.Request;
import okhttp3.Response;


public class TabFragment2 extends Fragment implements OnBannerListener {
    private static List<MyHomeCardItem> homeCardList;
    private ExpandListView homeCardListView;

    private RefreshLayout mRefreshLayout;

    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;

    // 轮播图
    private Banner mBanner;

    static {
        homeCardList = new ArrayList<>();
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
        homeCardList.add(new MyHomeCardItem(R.mipmap.kyyy, "考研英语", "http://139.199.21.231/news/1.html"));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment2, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://139.199.21.231/PEE/control/TitleControl.php?moudle=title")
                        .get()
                        .build();
                try {
                    Response response = NetworkHelper.client.newCall(request).execute();
                    String result = response.body().string();
                    mHandler.obtainMessage(2, result).sendToTarget();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("json------", e.getMessage() + "/" + e.getCause());
                    mHandler.obtainMessage(3, "发生了异常").sendToTarget();
                }
            }
        }).start();

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

                final MyHomeCardItem homeCard = homeCardList.get(position);
                // 图片
                ImageView cardCover = (ImageView) itemview.findViewById(R.id.home_card_pic);
                cardCover.setImageResource(homeCard.getPicPath());
                // 标题
                TextView videoTitle = (TextView) itemview.findViewById(R.id.home_card_title);
                videoTitle.setText(homeCard.getTitle());

                // 点击事件
                // 点击事件
                CardView cardView = itemview.findViewById(R.id.home_card);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", homeCard.getUrl());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                return itemview;
            }
        });

        mRefreshLayout = view.findViewById(R.id.refreshLayout);


        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder()
                                .url("http://139.199.21.231/PEE/control/TitleControl.php?moudle=title")
                                .get()
                                .build();
                        try {
                            Response response = NetworkHelper.client.newCall(request).execute();
                            String result = response.body().string();
                            mHandler.obtainMessage(2, result).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("json------", e.getMessage() + "/" + e.getCause());
                            mHandler.obtainMessage(3, "发生了异常").sendToTarget();
                        }
                    }
                }).start();
                refreshlayout.finishRefresh(1500/*,false*/);//传入false表示刷新失败
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


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.err.println("MSG" + msg.what);
            if (msg.what == 2) {
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息", ReturnMessage);
                final MyNewsResponse myNewsResponse = NetworkHelper.gson.fromJson(ReturnMessage, MyNewsResponse.class);
                final String status = myNewsResponse.getStatus();
                final ArrayList<MyNews> myNewsArrayList = myNewsResponse.getData();
                if (status.equals(Config.OK)) {
                    Toast.makeText(getActivity().getApplicationContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                    homeCardList.clear();
                    for (int i = 0; i < myNewsArrayList.size(); i++) {
                        MyNews myNews = myNewsArrayList.get(i);
                        String news_type = myNews.getNews_type();
                        int pp = 0;
                        if (news_type.equals("考研英语")) {
                            pp = R.mipmap.kyyy;
                        } else if (news_type.equals("考研数学")) {
                            pp = R.mipmap.kysx;
                        } else if (news_type.equals("考研政治")) {
                            pp = R.mipmap.kyzz;
                        } else if (news_type.equals("专业课")) {
                            pp = R.mipmap.kyzyk;
                        }
                        homeCardList.add(new MyHomeCardItem(pp, myNews.news_title, "http://139.199.21.231/news/" + myNews.news_url));
                    }
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
                                itemview = videoInflater.inflate(R.layout.home_card, null);
                            } else {
//                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                                itemview = convertView;
                            }

                            final MyHomeCardItem homeCard = homeCardList.get(position);
                            // 图片
                            ImageView cardCover = (ImageView) itemview.findViewById(R.id.home_card_pic);
                            cardCover.setImageResource(homeCard.getPicPath());
                            // 标题
                            TextView videoTitle = (TextView) itemview.findViewById(R.id.home_card_title);
                            videoTitle.setText(homeCard.getTitle());

                            // 点击事件
                            CardView cardView = itemview.findViewById(R.id.home_card);
                            cardView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("URL", homeCard.getUrl());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                            return itemview;
                        }
                    });
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "链接服务器失败", Toast.LENGTH_SHORT).show();
                }
            } else if (msg.what == 3) {
                Toast.makeText(getActivity().getApplicationContext(), "有bug，更新失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

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
