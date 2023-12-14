package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView textView1, textView2, textView3, textView4;
    Button login, cancel;
    CheckBox showPass, remember_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 获取用户名、密码、显示密码的 CheckBox、记住密码的 CheckBox 以及登录和取消按钮的实例
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        showPass = findViewById(R.id.checkBox1);
        remember_pwd = findViewById(R.id.checkBox2);
        login = findViewById(R.id.button_login); // 假设 loginButton 是登录按钮的 ID
        cancel = findViewById(R.id.button_quit); // 假设 cancelButton 是取消按钮的 ID

        // CheckBox 按钮用于显示/隐藏密码
        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 如果选中，显示密码
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // 否则隐藏密码
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        // CheckBox 按钮用于获取记住密码的参数
        final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = preference.getBoolean("remember_pwd", false);

        // 将账号和密码设置到文本框，并勾选记住密码
        if (isRemember) {
            username.setText(preference.getString("name", ""));
            password.setText(preference.getString("password", ""));
            remember_pwd.setChecked(true);
        }

        // 登录按钮点击监听器
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = username.getText().toString();
                String pwd = password.getText().toString();

                // 执行登录用户名和密码校验
                if (inputName.equals("Cui") && pwd.equals("123456789")) {
                    SharedPreferences.Editor editor = preference.edit();
                    if (remember_pwd.isChecked()) {
                        // 记住账号和密码
                        editor.putBoolean("remember_pwd", true);
                        editor.putString("name", inputName);
                        editor.putString("password", pwd);
                    } else {// 清空数据
                        editor.clear();
                    }
                    editor.apply();
                    // 启动 MainActivity
                    MainActivity.actionStart(LoginActivity.this, inputName, pwd);
                } else {
                    // 登录失败，显示提示信息
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 取消按钮点击监听器
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
