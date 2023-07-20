package com.example.planewars.enemys;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.view.MyView;
import com.example.planewars.gameObject.GameObject;

import java.util.Random;

public abstract class Enemy extends GameObject {
    protected Bitmap enemy, enemy_hit;
    protected boolean isHit;
    protected Random random;
    protected int score;

    protected int HP;
    protected int TIMES;
    protected int sumCount;
    protected static int currentCount;

    public Enemy(Resources res){
        super(res);
        initBitmap();
        random = new Random();
        objectW = enemy.getWidth();
        objectH = enemy.getHeight();
    }

    public void init(int arg0, int arg1, int arg2){
        super.init(arg0, arg1, arg2);
        TIMES = arg0;
        isDead = false;
        currentX = random.nextInt(MyView.screenW - objectW);
        currentY = -objectH * (currentCount * 2 + 1);
    }

    public int getScore(){
        return score;
    }

    public int getHP(){
        return HP;
    }

    public boolean getHit(){
        return isHit;
    }

    public void setHp(int HP){
        this.HP = HP;
    }

    public void setHit(boolean isHit){
        this.isHit = isHit;
    }

    public abstract void draw(Canvas canvas, Paint paint);
    public abstract void logic();
    protected abstract void initBitmap();

    //RectF--碰撞
    public boolean isCollidedWith(GameObject gameObject){
        if(getBorder().contains(gameObject.getBorder())){
            isHit = true;
            return true;
        }else{
            return false;
        }
    }

    public void release(){
        if(!enemy.isRecycled()){
            enemy.recycle();
        }
    }
}
