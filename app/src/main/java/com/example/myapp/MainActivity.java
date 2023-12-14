package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView myLabelWelcome;
    TextView myLabelTime;
    public static void actionStart(Context context, String username, String password) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 TextViews
        myLabelWelcome = findViewById(R.id.myLabelWelcome);
        myLabelTime = findViewById(R.id.myLabelTime);

        // 从 LoginActivity 中获取数据
        Intent intent = getIntent();
        if (intent != null) {
            String inputName = intent.getStringExtra("username");
            // 显示欢迎消息和登录时间
            showWelcome(inputName);
            showTime();
        }
    }
    // 显示欢迎消息
    private void showWelcome(String name) {
        myLabelWelcome.setText("\n" + name + " 您好！\n    欢迎光临");
    }

    // 显示登录时间
    private void showTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        myLabelTime.setText("您的登录时间为：" + str);
    }
}