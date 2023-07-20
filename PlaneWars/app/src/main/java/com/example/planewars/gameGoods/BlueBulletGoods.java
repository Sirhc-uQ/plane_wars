package com.example.planewars.gameGoods;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class BlueBulletGoods extends GameGoods {

    public BlueBulletGoods(Resources res){
        super(res);

    }

    public void initBitmap(){
        gameGoods = BitmapFactory.decodeResource(res, R.drawable.ufo1);

        gameGoods = MyView.imageScale(gameGoods, 90,135);
    }
}
