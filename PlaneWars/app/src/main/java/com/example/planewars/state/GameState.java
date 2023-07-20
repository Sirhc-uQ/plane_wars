package com.example.planewars.state;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

public abstract class GameState {
    protected Process process;
    protected Resources res;

    public GameState(){}

    public GameState(Resources res){
        this.res = res;
        initBitmap();
        initPos();
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void init(Resources res) {
        this.res = res;
        initBitmap();
        initPos();
    }

    public void initPos(){}

    public void initBitmap(){}

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract void logic();

    public abstract void onTouchEvent(MotionEvent event);

    protected RectF getBorder(Bitmap bitmap, int x, int y){
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        return new RectF(x, y, x+w, y+h);
    }
}
