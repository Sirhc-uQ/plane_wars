package com.example.planewars.explosion;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class MyplaneExplosion extends Explosion{

    public MyplaneExplosion(Resources res){
        super(res);
        SPEED = 2;
    }
    protected void initBitmap(){
        boom1 = BitmapFactory.decodeResource(res, R.drawable.hero_blowup_n1);
        boom2 = BitmapFactory.decodeResource(res, R.drawable.hero_blowup_n2);
        boom3 = BitmapFactory.decodeResource(res, R.drawable.hero_blowup_n2);
        boom4 = BitmapFactory.decodeResource(res, R.drawable.hero_blowup_n3);
        boom5 = BitmapFactory.decodeResource(res, R.drawable.hero_blowup_n3);

        boom1 = MyView.imageScale(boom1, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        boom2 = MyView.imageScale(boom2, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        boom3 = MyView.imageScale(boom3, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        boom4 = MyView.imageScale(boom4, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        boom5 = MyView.imageScale(boom5, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
    }

    public void draw(Canvas canvas, Paint paint){
        switch (boomCount){
            case 1:
            case 2:
            case 3:
                canvas.drawBitmap(boom1, currentX, currentY, paint);
                break;
            case 4:
                canvas.drawBitmap(boom2, currentX, currentY, paint);
                break;
            case 5:
            case 6:
                canvas.drawBitmap(boom3, currentX, currentY, paint);
                break;
            case 7:
            case 8:
                canvas.drawBitmap(boom4, currentX, currentY, paint);
                break;
            case 9:
            case 10:
                canvas.drawBitmap(boom5, currentX, currentY, paint);
                break;
        }

        boomCount++;

    }

    public void logic(){
        currentY += SPEED;
        if(boomCount >= 20){
            isDead = true;
        }
    }
}
