package hehut.scse.kaoyanbang.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hehut.scse.kaoyanbang.R;

public class AboutActivity extends Activity {

    Button btnBack;

    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("关于");
    }
}
