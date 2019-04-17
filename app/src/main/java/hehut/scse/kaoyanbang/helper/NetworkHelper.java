package hehut.scse.kaoyanbang.helper;

import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import hehut.scse.kaoyanbang.bean.MyResponse;
import hehut.scse.kaoyanbang.bean.MyUser;
import hehut.scse.kaoyanbang.config.Config;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkHelper {

    public static OkHttpClient client = new OkHttpClient();
    public static final Gson gson = new Gson();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");



}
