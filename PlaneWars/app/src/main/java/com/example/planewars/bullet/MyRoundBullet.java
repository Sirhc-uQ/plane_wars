package com.example.planewars.bullet;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class MyRoundBullet extends Bullets {

    public MyRoundBullet(Resources res){
        super(res);
        isDead = false;
        SPEED = GameConstant.ROUND_BULLET_SPEED;
        ATK = GameConstant.ROUND_ATK;
    }

    public void init(int arg0, int arg1, int arg2){
        super.init(arg0, arg1, arg2);
        currentX = currentX+(GameConstant.HERO_2_WIDTH-objectW)/2;
        currentY = currentY-objectH;
    }
    protected void initBitmap(){
        objectW = GameConstant.ROUND_BULLET_WIDTH;
        objectH = GameConstant.ROUND_BULLET_HEIGHT;
        bullet = BitmapFactory.decodeResource(res, R.drawable.round_bullet);

        bullet = MyView.imageScale(bullet, objectW, objectH);
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bullet, currentX, currentY, paint);
    }

    public void logic(){
        currentY -= SPEED;
        if(currentY < 0){
            isDead = true;
        }
    }
}

