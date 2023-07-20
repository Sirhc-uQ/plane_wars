package com.example.planewars.gameObject;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class GameObject {
    protected int currentX, currentY;
    protected int objectW, objectH;
    protected int SPEED;
    protected boolean isDead;
    protected Resources res;

    public GameObject(Resources res){
        this.res = res;
    }

    public abstract void draw(Canvas canvas, Paint paint);
    public abstract void logic();
    protected abstract void initBitmap();

    public void init(int arg0, int arg1, int arg2){
    }

    public boolean isCollidedWith(GameObject gameObject){
        return true;
    };

    public RectF getBorder(){
        return new RectF(currentX, currentY, currentX+objectW, currentY+objectH);
    }

    public int getCurrentX(){
        return currentX;
    }

    public int getCurrentY(){
        return currentY;
    }

    public int getObjectW(){
        return objectW;
    }

    public int getObjectH() {
        return objectH;
    }

    public boolean getState(){
        return isDead;
    }

    public void setCurrentX(int currentX){
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY){
        this.currentY = currentY;
    }

    public void setState(boolean isDead){
        this.isDead = isDead;
    }

    public void release(){
    }

}
