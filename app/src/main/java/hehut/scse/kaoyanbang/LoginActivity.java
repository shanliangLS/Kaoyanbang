package hehut.scse.kaoyanbang;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import hehut.scse.kaoyanbang.config.Config;
import hehut.scse.kaoyanbang.helper.InfoHelper;

public class LoginActivity extends Activity {


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
                if (isLogin(name, password)) {
                    isLoginButtonPressed = true;
                    InfoHelper.saveInfo(LoginActivity.this, name, password);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private boolean isLogin(String name, String password) {
        if (name.equals("guis") && password.equals("guis")) {
            return true;
        }
        return false;
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
