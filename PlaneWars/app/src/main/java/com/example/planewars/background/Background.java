package com.example.planewars.background;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.view.MyView;

public class Background {
    private int backW, backH;
    private int backX_1, backY_1, backX_2, backY_2;

    private final int SPEED = 15;
    private Bitmap back1, back2;

    public Background(Bitmap bitmap) {
        back1 = bitmap;
        back2 = bitmap;
        backW = bitmap.getWidth();
        backH = bitmap.getHeight();

        //先将背景1后半部充满屏幕，将背景2在背景1后放置
        backY_1 = -Math.abs(backH - MyView.screenH);
        backY_2 = backY_1 - backH;
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(back1, backX_1 ,backY_1, paint);
        canvas.drawBitmap(back2, backX_2, backY_2, paint);
    }

    public void logic() {
        backY_1 += SPEED;
        backY_2 += SPEED;
        //使背景1，2交替放置
        if(backY_1 >= MyView.screenH){
            backY_1 = backY_2 - backH;
        }

        if(backY_2 >= MyView.screenH){
            backY_2 = backY_1 - backH;
        }
    }

}
