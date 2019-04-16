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

public class PlayerFragment4 extends Fragment {
    private static List<VideoDetail> videoList;
    private ListView videoListView;

    static {
        videoList = new ArrayList<>();
        videoList.add(new VideoDetail(R.drawable.zhuanye1,
                "2020考研:计算机考研导学02-启航考研","启航",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye2,
                "2020考研专业课【408计算机】","",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye3,
                "2020考研计算机:数据结构复习指南","",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye4,
                "2020考研\"二战\"计算机全科专属vip班","",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye5,
                "天勤数据结构考研书天勤计算机考研系列天勤高分笔记 2018 数据结构高","天勤",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye1,
                "2020考研:计算机考研导学02-启航考研","启航",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye2,
                "2020考研专业课【408计算机】","",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye3,
                "2020考研计算机:数据结构复习指南","",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye4,
                "2020考研\"二战\"计算机全科专属vip班","",(int)(Math.random()*10000)));
        videoList.add(new VideoDetail(R.drawable.zhuanye5,
                "天勤数据结构考研书天勤计算机考研系列天勤高分笔记 2018 数据结构高","天勤",(int)(Math.random()*10000)));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment4, container, false);

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
//                    Log.i("videoListInfo:", "没有缓存，重新生成" + position);
                    LayoutInflater videoInflater = PlayerFragment4.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.player_card, null);
                } else {
//                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
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
                if (video.getVideoTag() == "") {
                    ImageView videoTagImg = (ImageView) itemview.findViewById(R.id.video_tag_img);
                    videoTagImg.setVisibility(View.GONE);
                    videoTag.setVisibility(View.GONE);
                } else {
                    videoTag.setText(video.getVideoTag());
                }
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
