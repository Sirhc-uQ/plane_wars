package com.example.planewars.myPlane;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.gameGoods.GameGoods;
import com.example.planewars.gameObject.GameObject;
import com.example.planewars.R;

public abstract class MyPlane extends GameObject {
    protected Bitmap hero1, hero2;
    protected int type;

    private Bitmap heroHp;

    private boolean isUp, isDown, isLeft, isRight;
    private boolean isCollided;
    //无敌时间，60ms
    private int invincibleTime = 60;
    //无敌时间计时
    private int collisionCount;
    private int HP;

    private int TURN;
    private int score = 0;

    public MyPlane(Resources res){
        //this.res = res;
        super(res);
        initBitmap();
        objectW = hero1.getWidth();
        objectH = hero1.getHeight();
        currentX = MyView.screenW/2-objectW/2;//飞机的起始位置
        currentY = MyView.screenH-objectH;
        SPEED = GameConstant.MYPLANE_SPEED;
    }

    public void init(int arg0, int arg1, int arg2){
        super.init(arg0, arg1, arg2);
        HP = 3;
        collisionCount = 0;
        isCollided = false;
        isDead = false;
        TURN = 0;
    }

    protected void initBitmap(){
        heroHp = BitmapFactory.decodeResource(res, R.drawable.hero_hp);

        heroHp = MyView.imageScale(heroHp, GameConstant.HP_WIDTH,GameConstant.HP_HEIGHT);
    }

    public int getType(){
        return type;
    }

    public int getHP(){
        return HP;
    }

    public int getScore(){
        return score;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public void setExtra(Bitmap bitmap){
        hero2 = bitmap;
    }

    public void setLocation(int x, int y){
        currentX = x - objectW/2;
        currentY = y - objectH/2;
    }

    public void draw(Canvas canvas, Paint paint){
        if(!isDead) {
            TURN++;
            if (isCollided == false) {
                if (TURN % 2 == 0) {
                    canvas.drawBitmap(hero1, currentX, currentY, paint);
                } else {
                    canvas.drawBitmap(hero2, currentX, currentY, paint);
                }
            } else {
                //无敌状态进行闪烁
                if (collisionCount % 2 == 0) {
                    canvas.drawBitmap(hero1, currentX, currentY, paint);
                }
            }

            if(HP == 1){//当只有一滴血的时候进行闪烁提示
                if(TURN % 2 == 0){
                    canvas.drawBitmap(heroHp, 30, MyView.screenH - heroHp.getHeight() - 30, paint);
                }
            }else {
                for (int i = 0; i < HP; i++) {
                    canvas.drawBitmap(heroHp, i * (heroHp.getWidth() + 5) + 30, MyView.screenH - heroHp.getHeight() - 30, paint);
                }
            }
        }

    }

    public void logic(){
        if(isUp)
            currentY -= SPEED;
        if(isDown)
            currentY += SPEED;
        if(isLeft)
            currentX -= SPEED;
        if(isRight)
            currentX += SPEED;
        //判断屏幕x的边界
        if(currentX + objectW >= MyView.screenW){
            currentX = MyView.screenW - objectW;
        }else if(currentX <= 0){
            currentX = 0;
        }
        //判断屏幕y的边界
        if(currentY + objectH >= MyView.screenH){
            currentY = MyView.screenH - objectH;
        }else if(currentY <= 0){
            currentY = 0;
        }
        //判断无敌，并对变量进行还原
        if(isCollided){
            collisionCount++;
            if(collisionCount >= invincibleTime){
                isCollided = false;
                collisionCount = 0;
            }
        }

        score++;

    }

    //判定是否发生碰撞
    public boolean isCollidedWith(GameObject gameObject){
        if(isCollided == false) {
            if (getBorder().intersect(gameObject.getBorder())) {
                //如果碰撞为物品则无反应
                if(gameObject instanceof GameGoods){
                    isCollided = false;
                }else{
                    isCollided = true;
                }
                return true;
            } else {
                return false;
            }
        }else if(gameObject instanceof GameGoods && getBorder().intersect(gameObject.getBorder())){
            return true;
        }else{
            return false;
        }
    }

    //触摸事件
    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(this.getBorder().contains(x, y)){
            setLocation(x, y);
        }

        return true;
    }

    //public abstract Bullets getBullets();
}
