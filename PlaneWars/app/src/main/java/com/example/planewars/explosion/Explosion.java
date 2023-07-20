package com.example.planewars.explosion;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.gameObject.GameObject;

public class Explosion extends GameObject {
    protected Bitmap boom1, boom2, boom3, boom4, boom5;
    protected int boomCount;
    protected int SPEED;

    public Explosion(Resources res){
        super(res);
        initBitmap();
        boomCount = 0;
        objectW = boom1.getWidth();
        objectH = boom1.getHeight();
    }

    public void init(int arg0, int arg1, int arg2){
        currentX = arg1;
        currentY = arg2;
    }

    protected void initBitmap(){
    }

    public void draw(Canvas canvas, Paint paint){
        switch (boomCount){
            case 1:
            case 2:
                canvas.drawBitmap(boom1, currentX, currentY, paint);
                break;
            case 3:
            case 4:
                canvas.drawBitmap(boom2, currentX, currentY, paint);
                break;
            case 5:
            case 6:
                canvas.drawBitmap(boom3, currentX, currentY, paint);
                break;
            case 7:
            case 8:
                canvas.drawBitmap(boom4, currentX, currentY, paint);
                break;
            case 9:
            case 10:
                canvas.drawBitmap(boom5, currentX, currentY, paint);
                break;
        }

        boomCount++;

    }

    public void logic(){
        currentY += SPEED;
        if(boomCount >= 10){
            isDead = true;
        }
    }

    public void release(){
        if(!boom1.isRecycled()){
            boom1.recycle();
        }
        if(!boom2.isRecycled()){
            boom2.recycle();
        }
        if(!boom3.isRecycled()){
            boom3.recycle();
        }
        if(!boom4.isRecycled()){
            boom4.recycle();
        }
        if(!boom5.isRecycled()){
            boom5.recycle();
        }
    }
}
