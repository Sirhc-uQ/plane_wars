package com.example.planewars.bullet;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.gameObject.GameObject;

public abstract class Bullets extends GameObject {
    protected Bitmap bullet;

    protected int ATK;
    protected boolean attack;

    public Bullets(Resources res){
        //this.res = res;
        super(res);
        initBitmap();
    }

    public void init(int arg0, int arg1, int arg2){
        currentX = arg1;
        currentY = arg2;
    }

    public abstract void draw(Canvas canvas, Paint paint);
    public abstract void logic();
    protected abstract void initBitmap();

    public int getATK(){
        return ATK;
    }


    public boolean isCollidedWith(GameObject gameObject){
        if(getBorder().intersect(gameObject.getBorder())){
            isDead = true;
            return true;
        }else{
            return false;
        }
    }

    public void release(){
        if(!bullet.isRecycled()){
            bullet.recycle();
        }
    }
}
