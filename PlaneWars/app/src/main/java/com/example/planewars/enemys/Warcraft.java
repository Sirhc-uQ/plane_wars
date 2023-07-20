package com.example.planewars.enemys;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class Warcraft extends Enemy {

    public Warcraft(Resources res){
        super(res);
        score = GameConstant.WARCRAFT_SCORE;
    }

    public void init(int arg0, int arg1, int arg2) {
        super.init(arg0, arg1, arg2);
        SPEED = TIMES*random.nextInt(4)+2;
        HP = GameConstant.WARCRAFT_HP;
        sumCount = GameConstant.WARCRAFT_SUM;
        currentCount++;
        if (currentCount >= sumCount) {
            currentCount = 0;
        }
    }


    protected void initBitmap(){
        enemy = BitmapFactory.decodeResource(res, R.drawable.enemy2);
        enemy_hit = BitmapFactory.decodeResource(res, R.drawable.enemy2_hit);

        enemy = MyView.imageScale(enemy, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
        enemy_hit = MyView.imageScale(enemy_hit, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
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
