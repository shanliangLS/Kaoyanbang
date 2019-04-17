package hehut.scse.kaoyanbang.helper;


import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class NetworkHelper {

    public static OkHttpClient client = new OkHttpClient();
    public static final Gson gson = new Gson();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


}
