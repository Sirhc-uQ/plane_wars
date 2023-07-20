package com.example.planewars.enemys;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class Carrier extends Enemy {
    //加速停止的Y值
    private int appearanceY;
    private int oriSpeed;

    public Carrier(Resources res){
        super(res);
        score = GameConstant.CARRIRT_SCORE;
        appearanceY = -random.nextInt(100)-objectH;
    }

    public void init(int arg0, int arg1, int arg2) {
        super.init(arg0, arg1, arg2);
        SPEED = (TIMES*random.nextInt(4)+2)/2;
        oriSpeed = SPEED;
        HP = GameConstant.CARRIER_HP;
        sumCount = GameConstant.CARRIER_SUM;
        currentCount++;
        if (currentCount >= sumCount) {
            currentCount = 0;
        }
    }

    protected void initBitmap(){
        enemy = BitmapFactory.decodeResource(res, R.drawable.enemy3);
        enemy_hit = BitmapFactory.decodeResource(res, R.drawable.enemy3_hit);

        enemy = MyView.imageScale(enemy, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
        enemy_hit = MyView.imageScale(enemy_hit, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
    }

    public void draw(Canvas canvas, Paint paint){
        if(isHit){
            canvas.drawBitmap(enemy_hit, currentX, currentY, paint);
            isHit = false;
        }else{
            canvas.drawBitmap(enemy, currentX, currentY, paint);
        }
    }

    public void logic(){
        if(!isDead) {
            //加速出现后恢复正常
            if(currentY < appearanceY) {
                SPEED = SPEED < 12 ? SPEED+1 : SPEED;
            }else{
                SPEED = oriSpeed;
            }
            currentY += SPEED;
            if (currentY > MyView.screenH+objectH) {
                isDead = true;
            }
        }
    }

    public void release(){
        super.release();
        if(!enemy_hit.isRecycled()){
            enemy_hit.recycle();
        }
    }
}
