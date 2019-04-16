package hehut.scse.kaoyanbang.TabFragment.Player;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.util.ShareUtil;

public class PlayerDetail extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {
    FloatingActionButton fabLike;
    TextView tvDetailBottomShare;

    StandardGSYVideoPlayer detailPlayer;

//    private String url = "http://wdquan-space.b0.upaiyun.com/VIDEO/2018/11/22/ae0645396048_hls_time10.m3u8";
//    private String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    // 张宇鬼畜
    private String url = "http://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/39/42/66504239/66504239-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&deadline=1555410212&gen=playurl&nbs=1&oi=3690160096&os=wcsu&platform=html5&trid=781973517e2f4b83b09a559c92c492b2&uipk=5&upsig=31dc12d669f52ad2ba5ef6c16f6741ff&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_detail);

        fabLike = findViewById(R.id.fab_like);
        tvDetailBottomShare = findViewById(R.id.tv_detail_bottom_share);
        Button.OnClickListener buttonListener = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.fab_like:
                        if (fabLike.isSelected()) {
                            fabLike.setSelected(false);
                        } else {
                            fabLike.setSelected(true);
                        }
                        return;
                    case R.id.tv_detail_bottom_share:
                        ShareUtil.shareText(PlayerDetail.this,"baidu.com","分享一篇文章");
                        return;
                }
            }
        };
        fabLike.setOnClickListener(buttonListener);
        tvDetailBottomShare.setOnClickListener(buttonListener);

        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);

        initVideoBuilderMode();
    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        //loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }


    /**
     * 是否启动旋转横屏，true表示启动
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    private void loadCover(ImageView imageView, String url) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(3000000)
                                .centerCrop()
                                .error(R.mipmap.xxx2)
                                .placeholder(R.mipmap.xxx1))
                .load(url)
                .into(imageView);
    }
}
