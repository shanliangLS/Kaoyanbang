package hehut.scse.kaoyanbang.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.util.ShareUtil;

public class SettingActivity extends Activity {

    LinearLayout llSettingUpdate;
    LinearLayout ll_setting_feedback;
    LinearLayout llSettingClear;

    TextView tvSettingClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        tvSettingClear = findViewById(R.id.tv_setting_clear);
        int cache = (int) (Math.random() * 500) + 100;
        tvSettingClear.setText("缓存大小：" + cache + "kb");

        llSettingUpdate = findViewById(R.id.ll_setting_update);
        llSettingUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
            }
        });


        ll_setting_feedback = findViewById(R.id.ll_setting_feedback);
        ll_setting_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.sendEmail(SettingActivity.this, "选择邮件客户端:");
            }
        });

        llSettingClear = findViewById(R.id.ll_setting_clear);
        llSettingClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSettingClear.setText("缓存大小：0kb");
                Toast.makeText(SettingActivity.this, "清除缓存成功", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
