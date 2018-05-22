package com.newdicooker.tempetek.mipushdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static List<String> logList = new CopyOnWriteArrayList<String>();
    @InjectView(R.id.log_view)
    TextView logView;
    @InjectView(R.id.set_ali)
    Button setAli;
    @InjectView(R.id.send)
    Button send;
    @InjectView(R.id.input)
    EditText input;
    private String alias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoApplication.setMainActivity(this);
        ButterKnife.inject(this);
    }

    public void refreshLogInfo() {
        String AllLog = "";
        for (String log : logList) {
            AllLog = AllLog + log + "\n\n";
        }
        logView.setText(AllLog);
    }

    @OnClick({R.id.set_ali, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_ali:
                alias = input.getText().toString();
                MiPushClient.setAlias(this, alias, null);
                MiPushClient.setAcceptTime(this, 00, 00, 23, 59, null);
                break;
            case R.id.send:
                alias = input.getText().toString();
                MiPushClient.subscribe(this, alias, null);
                break;
        }
    }
}
