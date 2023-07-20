package com.example.planewars.enemys;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class Airplane extends Enemy {
    private int frameCount;

    public Airplane(Resources res){
        super(res);
        score = GameConstant.AIRPLANE_SCORE;
    }

    public void init(int arg0, int arg1, int arg2) {
        super.init(arg0, arg1, arg2);
        SPEED = TIMES * random.nextInt(6)+30;
        HP = GameConstant.AIRPLANE_HP;
        sumCount = GameConstant.AIRPLANE_SUM;
        currentCount++;
        if (currentCount >= sumCount) {
            currentCount = 0;
        }
    }

    protected void initBitmap(){
        enemy = BitmapFactory.decodeResource(res, R.drawable.enemy1);

        enemy = MyView.imageScale(enemy, GameConstant.AIRPLANE_WIDTH, GameConstant.AIRPLANE_HEIGHT);
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(enemy, currentX, currentY, paint);
    }

    public void logic(){
        //一直向下
        if(!isDead) {
            currentY += SPEED;
            if (currentY > MyView.screenH+objectH) {
                isDead = true;
            }
        }
    }
}
