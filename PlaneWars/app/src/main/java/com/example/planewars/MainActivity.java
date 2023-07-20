package com.example.planewars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.planewars.view.MyView;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    public static String username;
    public static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getIntance().addActivity_(this);
        //设置全屏
        //隐去标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐去状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sp = getSharedPreferences("record", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        username = sp.getString("ID", "");

        setContentView(new MyView(this));
    }

    public static void setScore(int score){
        editor = sp.edit();
        if(MainActivity.score < score) {
            editor.putInt("score", score);
            MainActivity.score = sp.getInt("score", 0);
            editor.commit();
        }
    }
}
