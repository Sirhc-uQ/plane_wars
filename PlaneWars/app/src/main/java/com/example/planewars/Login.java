package com.example.planewars;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.planewars.MyApp;
import com.example.planewars.sounds.GameSounds;

public class Login extends AppCompatActivity {
    private Intent intent;
    private Button btn1,btn2;
    private CheckBox checkBox1;

    SharedPreferences sp;
    TextView username;
    EditText password;

    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyApp.getIntance().addActivity_(this);

        sp = getSharedPreferences("record", Context.MODE_PRIVATE);
        ID = sp.getString("ID", "");

        btn1 = (Button)findViewById(R.id.login);
        btn2 = (Button)findViewById(R.id.quit);
        username = (TextView)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password_input);
        //显示输入的密码
        checkBox1 = (CheckBox)findViewById(R.id.checkbox1);

        if(ID != ""){
            username.setText(ID);
        }

        password.setText("123");

        View.OnClickListener login = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID = username.getText().toString().trim();
                //et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                String word = password.getText().toString();//获取编辑框里面的内容
                if (word.equals("123")) {
                    Toast.makeText(Login.this, ID+" logged in successfully", Toast.LENGTH_SHORT).show();
                    //登陆成功启动新的activity，并传输用户名信息
                    intent = new Intent();
                    //Intent intent = new Intent(Login.this, MainActivity.class);
                    //隐式调用act
                    intent.setAction("StartGame");
                    intent.putExtra("name",ID);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Login failed, Please re-enter the password", Toast.LENGTH_SHORT).show();
                    password.setText(null);
                }
            }
        };

        View.OnClickListener quit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText(null);
                password.setText(null);
            }
        };

        //设置监听器，选中checkbox，显示密码
        CompoundButton.OnCheckedChangeListener display = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        };

        btn1.setOnClickListener(login);
        btn2.setOnClickListener(quit);
        checkBox1.setOnCheckedChangeListener(display);
    }

    protected void onStop(){
        super.onStop();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ID", ID);
        editor.commit();
    }
}


