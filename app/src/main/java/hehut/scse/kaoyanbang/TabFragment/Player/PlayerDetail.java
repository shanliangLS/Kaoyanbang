package hehut.scse.kaoyanbang.TabFragment.Player;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.util.ExpandListView;
import hehut.scse.kaoyanbang.util.ShareUtil;
import hehut.scse.kaoyanbang.xUtil.VideoUrl;

import org.xutils.x;

public class PlayerDetail extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {
    private boolean isBottomShow = true;
    private String mid = "6159308";

    FloatingActionButton fabLike;
    TextView tvDetailBottomShare;

    StandardGSYVideoPlayer detailPlayer;

    // private String url = "http://wdquan-space.b0.upaiyun.com/VIDEO/2018/11/22/ae0645396048_hls_time10.m3u8";
    // private String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    // 张宇鬼畜
    // private String url = "http://upos-hz-mirrorwcsu.acgvideo.com/upgcxcode/39/42/66504239/66504239-1-6.mp4?e=ig8euxZM2rNcNbug7WdVtWug7WdVNEVEuCIv29hEn0l5QK==&deadline=1555507596&gen=playurl&nbs=1&oi=3723376653&os=wcsu&platform=html5&trid=49a1a5115a5545fdab72a18beeec9700&uipk=5&upsig=0f120f4f26586a42ed285e4d48759207&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk";
    private String url = "http://139.199.21.231/video/1.mp4";

    // 评论
    private static List<PlayerDetailComment> commentList;
    private ExpandListView commentListView;

    static {
        commentList = new ArrayList<>();
        commentList.add(new PlayerDetailComment("6159308","http://i2.hdslb.com/bfs/face/c6be2bdf237d09812e233e11a152415690f3d293.jpg","叫我翔哥吧",146,"2018-12-17",
                "哇~感谢大家的硬币和收藏！希望我们都能考进理想的学校！\n谢谢大家能够喜欢我的第一个鬼畜视频~",1551));
        commentList.add(new PlayerDetailComment("90418629","http://i2.hdslb.com/bfs/face/2b92c14a1203e3bf7c3bc77ebb6d93c53d0b3fab.jpg","村口dj吴彦祖",441,"2018-12-18",
                "函数界限 精确刻画 ↵高阶低阶 数学归纳↵三大性质 运算方法 ↵极限计算 判别类型↵泰勒洛法 化简先行↵八个展开↵七种未定式↵加加减减送分儿题↵易于连续 归结原则 不易连续 夹逼准则↵f'关系    中值定理 拉格朗日 可导就行↵递推通项 有界准则 跳跃可去 无穷振荡↵连续间断 达到顶峰↵导微不定 反常变限 六个概念↵可导存在 三种说法 完全等价↵导数定义 几何意义 积分夹逼↵超级简单基本题↵左导右极 精确定义↵间断连续 通项关系↵积分定义 好好复习↵变上变下 有界才叫 变限积分↵广义黎曼 瑕点无穷 熟记于心↵三角指幂 方差开根 基本公式↵分部微凑 换元有理 思考程序↵最值拐点 单调增减 凹凸判别↵铅锤 水平 渐近线↵逻辑证明 三大方面 压轴考题 ↵倒背如流 十大基本定理↵有界定理 最值定理 介值定理↵零点定理 罗尔定理 费马定理↵拉格朗日 中值定理 柯西定理 ↵泰勒公式 积分定理↵主部误差 全微分值↵链式求导 结构不变↵拉格朗日 辅助乘数↵五个方程 五个函数↵曲边梯形 曲顶柱体↵把土豆切成萝卜丁↵后积定限 限内画线↵先交后交 计算上下限↵线性可分 齐次可降 六大方程↵判别散敛 展开求和 三大考点↵比较极限 达朗柯西 五种判别↵六个展开记下来↵点法连等 平面方程 曲面切面 显隐方程↵投影曲面 连立消元 方向导数 梯散旋度↵三重积分 三种计算 锥椭旋转 作为基础↵直角柱面 投影穿线 球面坐标 背下公式↵两型积分 计算公式 格林高斯 斯托克斯",2393));
        commentList.add(new PlayerDetailComment("23246864","http://i0.hdslb.com/bfs/face/faf2e17d0f8ee07dc49a127e21fce09810567d1c.jpg","Cc橘子Soda",13,"2018-12-15",
                "我该收藏到学习文件夹还是鬼畜文件夹里呢（#-_-)",1272));
        commentList.add(new PlayerDetailComment("255949824","http://i1.hdslb.com/bfs/face/7b5b4cc869a0ead9903698c2a958c1e6aac23b96.jpg","天堂劫你来辣",69,"2018-12-17",
                "宇哥牛批，明年我还跟着你学",789));
        commentList.add(new PlayerDetailComment("21774199","http://i1.hdslb.com/bfs/face/46b42ada91e3afd0c4ddea11a3ca0abcf333e0da.jpg","00Fuego00",26,"2018-12-15",
                "今年数一考不到一百四就从十楼滚下去",513));
        commentList.add(new PlayerDetailComment("11972195","http://i0.hdslb.com/bfs/face/88c805b34b50a2f018c364d904a1dee6c30f7413.jpg","山竹酸酸hhh",37,"2018-12-15",
                "还有六天!视频里基础知识的体系结构挺完整(=・ω・=)还有宇哥最后胖嘟嘟",201));
        commentList.add(new PlayerDetailComment("27891168","http://i2.hdslb.com/bfs/face/757095a651a6b557dab02ba9053afdb3f04eda34.jpg","卢登的回声o",137,"2018-12-17",
                "高数一直听宇哥的课，感觉高数似乎也没有那么难，说到难，令我想起了西游记中的九九八十一难。今年下半年，中美合拍的西游记即将正式开机，我继续扮演美猴王孙悟空，我会用美猴王艺术形象努力创造一个正能量的形象，弘扬中华文化，文体两开花，希望大家能多多关注。",392));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_detail);

//        getUrl();

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

        initCommentList();
        initVideoBuilderMode();
        initNsvScroller();
    }

    private void getUrl() {
        // GET请求
        RequestParams params = new RequestParams("https://api.bilibili.com/playurl");
        params.addQueryStringParameter("callback","callbackfunction");
        params.addQueryStringParameter("aid","37830242");
        params.addQueryStringParameter("page","1");
        params.addQueryStringParameter("platform","html5");
        params.addQueryStringParameter("quality","1");
        params.addQueryStringParameter("vtype","mp4");
        params.addQueryStringParameter("type","jsonp");
        params.addQueryStringParameter("_","1555500596037");
        params.addHeader("Referer","https://www.ibilibili.com/video/av37830242");
        params.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Toast.makeText(PlayerDetail.this,"请求成功",Toast.LENGTH_SHORT).show();
                Log.i("gson",result);
                Gson gson =new Gson();
                VideoUrl videoUrl = gson.fromJson(result, VideoUrl.class);
                url = videoUrl.getDurl().get(0).getUrl();
                Toast.makeText(PlayerDetail.this,url,Toast.LENGTH_SHORT).show();
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(PlayerDetail.this,"解析视频url异常",Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    private void initNsvScroller() {
        NestedScrollView nsvScroller = findViewById(R.id.nsv_scroller);
        FrameLayout llDetailBottom = findViewById(R.id.ll_detail_bottom);
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    llDetailBottom.animate().translationY(llDetailBottom.getHeight());
                } else if(scrollY - oldScrollY < 0 && !isBottomShow){    //上移出现
                    isBottomShow = true;
                    llDetailBottom.animate().translationY(0);
                }
            }
        });
    }

    private void initCommentList() {
        commentListView = findViewById(R.id.player_comment_list);
        commentListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return commentList.size();
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
                    LayoutInflater commentInflater = PlayerDetail.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = View.inflate(PlayerDetail.this,R.layout.player_comment, null);
                } else {
//                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final PlayerDetailComment comment = commentList.get(position);
                // 头像
                ImageView userCover = (ImageView) itemview.findViewById(R.id.comment_user_cover);
                Glide.with(PlayerDetail.this).load(comment.getUserCover()).into(userCover);
                //userCover.setImageResource(comment.getUserCover());
                // UP标识
                /*if (comment.mIdEqual(mid)) {
                    TextView isUP = findViewById(R.id.is_up);
                    isUP.setVisibility(View.VISIBLE);
                }*/
                // 用户名
                TextView userName = (TextView) itemview.findViewById(R.id.comment_user_name);
                userName.setText(comment.getUserName());
                // 楼层数
                TextView floor = (TextView) itemview.findViewById(R.id.comment_floor);
                floor.setText(comment.getFloor());
                // 日期
                TextView date = (TextView) itemview.findViewById(R.id.comment_date);
                date.setText(comment.getDate());
                // 评论内容
                TextView content = (TextView) itemview.findViewById(R.id.comment_content);
                content.setText(comment.getContent().replace("↵","\n"));
                // 点赞人数
                TextView likeNum = (TextView) itemview.findViewById(R.id.comment_like_num);
                likeNum.setText(comment.getLikeNum());

                return itemview;
            }
        });
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