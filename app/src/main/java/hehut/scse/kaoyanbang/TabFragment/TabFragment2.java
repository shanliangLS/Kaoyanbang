package hehut.scse.kaoyanbang.TabFragment;

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

import java.util.ArrayList;
import java.util.List;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Player.Fragments.PlayerFragment1;
import hehut.scse.kaoyanbang.TabFragment.Player.PlayerDetail;
import hehut.scse.kaoyanbang.TabFragment.Player.VideoDetail;

public class TabFragment2 extends Fragment {
    private static List<HomeCardItem> homeCardList;
    private ListView homeCardListView;

    static {
        homeCardList = new ArrayList<>();
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
        homeCardList.add(new HomeCardItem(R.drawable.zhangyu1,"如何正确地吐槽"));
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

        return view;
    }
}
