package hehut.scse.kaoyanbang.xUtil;

import android.app.Application;
import org.xutils.x;

public class MyxUtil extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化XUtils
        x.Ext.init(this);
        //设置debug模式
        x.Ext.setDebug(true);
    }
}
