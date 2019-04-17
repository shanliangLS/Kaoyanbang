package hehut.scse.kaoyanbang;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import hehut.scse.kaoyanbang.bean.MyResponse;
import hehut.scse.kaoyanbang.bean.MyUser;
import hehut.scse.kaoyanbang.config.Config;
import hehut.scse.kaoyanbang.helper.NetworkHelper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity {

    public static String userName = "";
    public static String passWord = "";

    // 设置双击退出
    private static boolean mBackKeyPressed = false;
    private EditText textName;
    private EditText textPassword;
    private Button btnLogin;
    private static boolean isLoginButtonPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textName = findViewById(R.id.et_userName);
        textPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginButtonPressed) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isLoginButtonPressed = false;
                        }
                    }, 1 * 1000);
                    Toast.makeText(getApplicationContext(), "请勿重复点击", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = textName.getText().toString();
                String password = textPassword.getText().toString();
                if (name == null || name.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password == null || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                isLoginButtonPressed = true;
                userName = name;
                passWord = password;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = NetworkHelper.gson.toJson(new MyUser(name, password));
                        System.err.println(json);
                        RequestBody body = RequestBody.create(NetworkHelper.JSON, json);
                        Request request = new Request.Builder()
                                .url("http://139.199.21.231:8080/auth/login?email=" + name + "&password="
                                        + password)
                                .post(body)
                                .build();
                        try {
                            Response response = NetworkHelper.client.newCall(request).execute();
                            String result = response.body().string();
                            mHandler.obtainMessage(1, result).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("json------", e.getMessage() + "/" + e.getCause());
                            mHandler.obtainMessage(1, "发生了异常").sendToTarget();
                        }
                    }
                }).start();
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.err.println("MSG" + msg.what);
            if (msg.what == 1) {
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息", ReturnMessage);
                final MyResponse myResponse = NetworkHelper.gson.fromJson(ReturnMessage, MyResponse.class);
                final String status = myResponse.getStatus();
                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 */
                if (status.equals(Config.OK)) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    saveUserInfo(userName, passWord);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), myResponse.getReason(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    private static final String TAG = "LoginActivity";

    // 保存用户信息
    private void saveUserInfo(String name, String password) {
        SharedPreferences pref = getApplication().getSharedPreferences(Config.Xml, Config.XmlModel);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Config.username, name);
        editor.putString(Config.password, password);
        boolean success = editor.commit();
        if (success) {
            Log.e(TAG, "保存用户信息成功");
        } else {
            Log.e(TAG, "保存用户信息失败");
        }
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(LoginActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }
}
