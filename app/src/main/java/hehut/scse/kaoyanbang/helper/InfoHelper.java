package hehut.scse.kaoyanbang.helper;

import android.content.Context;

import hehut.scse.kaoyanbang.config.Config;

public class InfoHelper {

    private static final String USERNAME = Config.username;
    private static final String PASSWORD = Config.password;

    public static String getUserName(Context context) {
        return (String) SharedPreferencesHelper.get(context, USERNAME, null);
    }

    public static String getPassword(Context context) {
        return (String) SharedPreferencesHelper.get(context, PASSWORD, null);
    }

    public static void saveInfo(Context context, String username, String password) {
        SharedPreferencesHelper.put(context, USERNAME, username);
        SharedPreferencesHelper.put(context, PASSWORD, password);
    }
}
