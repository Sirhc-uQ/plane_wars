package com.example.planewars.bullet;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class MyYellowBullet extends Bullets {

    public MyYellowBullet(Resources res){
        super(res);
//        objectW = bullet.getWidth();
//        objectH = bullet.getHeight();
        isDead = false;
        SPEED = GameConstant.YELLOW_BULLET_SPEED;
        ATK = GameConstant.YELLOW_ATK;
    }

    public void init(int arg0, int arg1, int arg2){
        super.init(arg0, arg1, arg2);
        currentX = currentX+70;
        currentY = currentY-50;
    }
    protected void initBitmap(){
        objectW = GameConstant.YELLOW_BULLET_WIDTH;
        objectH = GameConstant.YELLOW_BULLET_HEIGHT;
        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet1);

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
