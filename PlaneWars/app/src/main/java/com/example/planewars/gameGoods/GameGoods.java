package com.example.planewars.gameGoods;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.gameObject.GameObject;
import com.example.planewars.view.MyView;

import java.util.Random;

public class GameGoods extends GameObject {
    protected Bitmap gameGoods;
    protected Random random;
    protected int count;
    private int direction;


    public GameGoods(Resources res){
        super(res);
        initBitmap();
        random = new Random();
        objectW = gameGoods.getWidth();
        objectH = gameGoods.getHeight();
        count = 0;
    }

    public void init(int arg0, int arg1, int arg2){
        super.init(arg0, arg1, arg2);
        currentX = random.nextInt(MyView.screenW - objectW);
        currentY = 0;
        isDead = false;
        direction = random.nextInt(2)+3;
        SPEED = 10;
    }

    protected void initBitmap(){
    }

    public void draw(Canvas canvas, Paint paint){
        if(!isDead){
            canvas.drawBitmap(gameGoods, currentX, currentY, paint);
        }
    }

    public void logic() {
        // 左上方移动时的逻辑
        if (direction == GameConstant.DIR_LEFT_UP) {
            currentX -= random.nextInt(3) + SPEED;
            currentY -= random.nextInt(3) + SPEED;
            if (currentX <= 0 || currentY <= 0) {
                if (currentX <= 0)
                    currentX = 0;
                else
                    currentY = 0;
                int dir = 0;
                do {
                    dir = random.nextInt(4) + 1;
                }
                while (dir == direction);
                direction = dir;
                this.SPEED = 10 + random.nextInt(5);
            }
        }
        // 右上方移动时的逻辑
        else if (direction == GameConstant.DIR_RIGHT_UP) {
            currentX += random.nextInt(3) + SPEED;
            currentY -= random.nextInt(3) + SPEED;
            if (currentX >= MyView.screenW - objectW || currentY <= 0) {
                if (currentX >= MyView.screenW - objectW)
                    currentX = MyView.screenW - objectW;
                else
                    currentY = 0;
                int dir = 0;
                do {
                    dir = random.nextInt(4) + 1;
                }
                while (dir == direction);
                direction = dir;
                this.SPEED = 10 + random.nextInt(5);
            }
        }
        // 左下方移动时的逻辑
        else if (direction == GameConstant.DIR_LEFT_DOWN) {
            currentX -= random.nextInt(3) + SPEED;
            currentY += random.nextInt(3) + SPEED;
            if (currentX <= 0 || currentY >= MyView.screenH - objectH) {
                if (currentX <= 0)
                    currentX = 0;
                else
                    currentY = MyView.screenH - objectH;
                int dir = 0;
                do {
                    dir = random.nextInt(4) + 1;
                }
                while (dir == direction);
                direction = dir;
                this.SPEED = 10 + random.nextInt(5);
            }
        }
        // 右下方移动时的逻辑
        else if (direction == GameConstant.DIR_RIGHT_DOWN) {
            currentX += random.nextInt(3) + SPEED;
            currentY += random.nextInt(3) + SPEED;
            if (currentX >= MyView.screenW - objectW || currentY >= MyView.screenH - objectH) {
                if (currentX >= MyView.screenW - objectW)
                    currentX = MyView.screenW - objectW;
                else
                    currentY = MyView.screenH - objectH;
                int dir = 0;
                do {
                    dir = random.nextInt(4) + 1;
                }
                while (dir == direction);
                direction = dir;
                this.SPEED = 10 + random.nextInt(5);
            }
        }

        //长时间未接，则物品消失
        count++;
        if(count > GameConstant.GOODS_FADE){
            isDead = true;
        }
    }

    public boolean isCollidedWith(GameObject gameObject){
        if(getBorder().contains(gameObject.getBorder())){
            return true;
        }else{
            return false;
        }
    }

    public void release(){
        if(gameGoods.isRecycled()){
            gameGoods.recycle();
        }
    }
}
