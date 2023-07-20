package com.example.planewars.explosion;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class AirplaneExplosion extends Explosion {

    public AirplaneExplosion(Resources res){
        super(res);
        SPEED = 10;
    }

    protected void initBitmap(){
        boom1 = BitmapFactory.decodeResource(res, R.drawable.enemy1_down1);
        boom2 = BitmapFactory.decodeResource(res, R.drawable.enemy1_down2);
        boom3 = BitmapFactory.decodeResource(res, R.drawable.enemy1_down3);
        boom4 = BitmapFactory.decodeResource(res, R.drawable.enemy1_down4);
        boom5 = BitmapFactory.decodeResource(res, R.drawable.enemy1_down4);

        boom1 = MyView.imageScale(boom1, GameConstant.AIRPLANE_WIDTH, GameConstant.AIRPLANE_HEIGHT);
        boom2 = MyView.imageScale(boom2, GameConstant.AIRPLANE_WIDTH, GameConstant.AIRPLANE_HEIGHT);
        boom3 = MyView.imageScale(boom3, GameConstant.AIRPLANE_WIDTH, GameConstant.AIRPLANE_HEIGHT);
        boom4 = MyView.imageScale(boom4, GameConstant.AIRPLANE_WIDTH, GameConstant.AIRPLANE_HEIGHT);
        boom5 = MyView.imageScale(boom5, GameConstant.AIRPLANE_WIDTH, GameConstant.AIRPLANE_HEIGHT);
    }
}
