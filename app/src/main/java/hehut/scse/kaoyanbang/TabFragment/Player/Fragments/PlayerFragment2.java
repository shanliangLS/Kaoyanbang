package hehut.scse.kaoyanbang.TabFragment.Player.Fragments;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Player.PlayerDetail;
import hehut.scse.kaoyanbang.TabFragment.Player.VideoDetail;

public class PlayerFragment2 extends Fragment {
    private static List<VideoDetail> videoList;
    private ListView videoListView;

    static {
        videoList = new ArrayList<>();
        videoList.add(new VideoDetail(R.drawable.zhuwei1,
                "跪求朱伟老师 \"词\"\"作\"之和--2014考研英语暑期助力班","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei2,
                "2019考研英语词汇精课—朱伟恋练有词","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei3,
                "朱伟老师考研英语词汇网络课程视频讲得好不好","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei4,
                "考研英语 朱伟 恋恋有词 unit 1初识词汇 精简版 2倍速 新东方在线","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei5,
                "2019考研英语朱伟恋练有词考研词汇(恋恋有词)","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei1,
                "跪求朱伟老师 \"词\"\"作\"之和--2014考研英语暑期助力班","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei2,
                "2019考研英语词汇精课—朱伟恋练有词","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei3,
                "朱伟老师考研英语词汇网络课程视频讲得好不好","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei4,
                "考研英语 朱伟 恋恋有词 unit 1初识词汇 精简版 2倍速 新东方在线","朱伟",(int)(Math.random()*1000)));
        videoList.add(new VideoDetail(R.drawable.zhuwei5,
                "2019考研英语朱伟恋练有词考研词汇(恋恋有词)","朱伟",(int)(Math.random()*1000)));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment2, container, false);

        videoListView = view.findViewById(R.id.player_math_list);
        videoListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return videoList.size();
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
                    Log.i("videoListInfo:", "没有缓存，重新生成" + position);
                    LayoutInflater videoInflater = PlayerFragment2.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.player_card, null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final VideoDetail video = videoList.get(position);
                // 图片
                ImageView videoCover = (ImageView) itemview.findViewById(R.id.video_cover);
                videoCover.setImageResource(video.getCoverPath());
                // 标题
                TextView videoTitle = (TextView) itemview.findViewById(R.id.video_title);
                videoTitle.setText(video.getVideoTitle());
                // 标签
                TextView videoTag = (TextView) itemview.findViewById(R.id.video_tag);
                videoTag.setText(video.getVideoTag());
                // 观看人数
                TextView VideoWatchNum = (TextView) itemview.findViewById(R.id.video_watch_num);
                VideoWatchNum.setText(video.getWatchNum());

                // 点击事件
                CardView cardView = itemview.findViewById(R.id.video_card);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), PlayerDetail.class));
                    }
                });

                return itemview;
            }
        });

        return view;
    }
}
