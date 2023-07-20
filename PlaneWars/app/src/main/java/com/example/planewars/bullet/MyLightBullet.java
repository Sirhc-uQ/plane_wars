package com.example.planewars.bullet;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;
import com.example.planewars.gameObject.GameObject;

public class MyLightBullet extends Bullets {
    private int currentX_1, currentY_1;
    private int currentX_2, currentY_2;

    private boolean attack_2;
    private boolean isDead_2;

    public MyLightBullet(Resources res){
        super(res);
        isDead = false;
        isDead_2 = false;
        SPEED = GameConstant.LIGHT_BULLET_SPEED;
        ATK = GameConstant.LIGHT_ATK;

    }

    public void init(int arg0, int arg1, int arg2) {
        super.init(arg0, arg1, arg2);
        currentX_1 = currentX+(GameConstant.HERO_3_WIDTH)/2-30-objectW;
        currentY_1 = currentY - objectH;
        currentX_2 = currentX+(GameConstant.HERO_3_WIDTH)/2+30;
        currentY_2 = currentY - objectH;
    }

    public boolean getState(){
        return isDead&&isDead_2;
    }

    private RectF getBorder_1(){
        return new RectF(currentX_1, currentY_1, currentX_1+objectW, currentY_1+objectH);
    }

    private RectF getBorder_2(){
        return new RectF(currentX_2, currentY_2, currentX_2+objectW, currentY_2+objectH);
    }

    protected void initBitmap(){
        objectW = GameConstant.LIGHT_BULLET_WIDTH;
        objectH = GameConstant.LIGHT_BULLET_HEIGHT;
        bullet = BitmapFactory.decodeResource(res, R.drawable.light_bullet);

        bullet = MyView.imageScale(bullet, objectW, objectH);
    }

    public void draw(Canvas canvas, Paint paint){
        if(!isDead){
            canvas.drawBitmap(bullet, currentX_1, currentY_1, paint);
        }
        if(!isDead_2){
            canvas.drawBitmap(bullet, currentX_2, currentY_2, paint);
        }
    }

    public void logic(){
        currentY_1 -= SPEED;
        if(currentY_1 < 0){
            isDead = true;
        }

        currentY_2 -= SPEED;
        if(currentY_2 < 0){
            isDead_2 = true;
        }
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
