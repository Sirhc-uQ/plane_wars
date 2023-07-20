package com.example.planewars.explosion;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class WarcraftExplosion extends Explosion {

    public WarcraftExplosion(Resources res){
        super(res);
        SPEED = 8;
    }
    protected void initBitmap(){
        boom1 = BitmapFactory.decodeResource(res, R.drawable.enemy2_down1);
        boom2 = BitmapFactory.decodeResource(res, R.drawable.enemy2_down2);
        boom3 = BitmapFactory.decodeResource(res, R.drawable.enemy2_down3);
        boom4 = BitmapFactory.decodeResource(res, R.drawable.enemy2_down4);
        boom5 = BitmapFactory.decodeResource(res, R.drawable.enemy2_down4);

        boom1 = MyView.imageScale(boom1, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
        boom2 = MyView.imageScale(boom2, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
        boom3 = MyView.imageScale(boom3, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
        boom4 = MyView.imageScale(boom4, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
        boom5 = MyView.imageScale(boom5, GameConstant.WARCRAFT_WIDTH, GameConstant.WARCRAFT_HEIGHT);
    }
}
