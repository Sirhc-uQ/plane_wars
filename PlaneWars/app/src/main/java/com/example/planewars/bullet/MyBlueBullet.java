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

public class MyBlueBullet extends Bullets {
    private int currentX_1, currentY_1;
    private int currentX_2, currentY_2;

    private boolean attack_2;
    private boolean isDead_2;

    public MyBlueBullet(Resources res){
        super(res);
//        objectW = bullet.getWidth();
//        objectH = bullet.getHeight();
        isDead = false;
        isDead_2 = false;
        SPEED = GameConstant.BLUE_BULLET_SPEED;
        ATK = GameConstant.BLUE_ATK;
    }

    public void init(int arg0, int arg1, int arg2) {
        super.init(arg0, arg1, arg2);
        currentX_1 = currentX+20;
        currentY_1 = currentY-80;
        currentX_2 = currentX+160-20-objectW;
        currentY_2 = currentY-80;
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
        objectW = GameConstant.BLUE_BULLET_WIDTH;
        objectH = GameConstant.BLUE_BULLET_HEIGHT;
        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet2);

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
