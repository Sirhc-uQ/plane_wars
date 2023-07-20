package com.example.planewars.bullet;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;
import com.example.planewars.gameObject.GameObject;

public class Missile extends Bullets {
    private Bitmap missile_launch;

    private int currentX_1, currentY_1;
    private int currentX_2, currentY_2;

    private int lauchSpeed;
    private boolean isReady;

    private boolean attack_2;
    private boolean isDead_2;

    public Missile(Resources res){
        super(res);
//        objectW = bullet.getWidth();
//        objectH = bullet.getHeight();
        lauchSpeed = 10;
        isReady = false;
        isDead = false;
        isDead_2 = false;
        SPEED = GameConstant.MISSILE_SPEED;
        ATK = GameConstant.MISSILE_ATK;
    }

    public void init(int arg0, int arg1, int arg2) {
        super.init(arg0, arg1, arg2);
        currentX_1 = currentX+20;
        currentY_1 = currentY-100;
        currentX_2 = currentX+160-20-objectW;
        currentY_2 = currentY-100;
    }

    protected void initBitmap(){
        objectW = GameConstant.MISSILE_WIDTH;
        objectH = GameConstant.MISSILE_HEIGHT;
        bullet = BitmapFactory.decodeResource(res, R.drawable.missile_1);
        missile_launch = BitmapFactory.decodeResource(res, R.drawable.missile_2);

        bullet = MyView.imageScale(bullet, objectW, objectH);
        missile_launch = MyView.imageScale(missile_launch, objectW, GameConstant.MISSILE_LAUNCH_HEIGHT);
    }

    @Override
    public boolean getState(){
        return isDead&&isDead_2;
    }

    public void draw(Canvas canvas, Paint paint){
        if(!isDead){
            if(isReady){
                canvas.drawBitmap(bullet, currentX_1, currentY_1, paint);
            }else{
                canvas.drawBitmap(missile_launch, currentX_1, currentY_1, paint);
            }
        }

        if(!isDead_2) {
            if(isReady){
                canvas.drawBitmap(bullet, currentX_2, currentY_2, paint);
            }else{
                canvas.drawBitmap(missile_launch, currentX_2, currentY_2, paint);
            }
        }
    }
    public void logic(){
        //两枚导弹的逻辑，准备后发射
        if(currentX_1 > currentX-objectW){
            currentX_1 -= lauchSpeed;
            currentY_1 += lauchSpeed;
        }else{
            isReady = true;
            currentY_1 -= SPEED;
            if(currentY_1 < 0){
                isDead = true;
            }
        }

        if(currentX_2 < currentX+160){
            currentX_2 += lauchSpeed;
            currentY_2 += lauchSpeed;
        }else{
            isReady = true;
            currentY_2 -= SPEED;
            if(currentY_2 < 0){
                isDead_2 = true;
            }
        }
    }

    private RectF getBorder_1(){
        return new RectF(currentX_1, currentY_1, currentX_1+objectW, currentY_1+objectH);
    }

    private RectF getBorder_2(){
        return new RectF(currentX_2, currentY_2, currentX_2+objectW, currentY_2+objectH);
    }

    @Override
    public boolean isCollidedWith(GameObject gameObject){
        //初始化，将子弹设置为未攻击状态，否则若子弹未全部消失，始终返回true
        attack = false;
        attack_2 = false;

        if(!isDead) {
            if (getBorder_1().intersect(gameObject.getBorder())) {
                isDead = true;
                attack = true;
            }
        }
        if(!isDead_2) {
            if (getBorder_2().intersect(gameObject.getBorder())) {
                isDead_2 = true;
                attack_2 = true;
            }
        }

        if(attack&&attack_2)
            ATK = 2*ATK;

        return attack||attack_2;
    }
}
