package com.example.planewars.gameGoods;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class BombGoods extends GameGoods {

    public BombGoods(Resources res){
        super(res);
    }

    public void initBitmap(){
        gameGoods = BitmapFactory.decodeResource(res, R.drawable.ufo2);

        gameGoods = MyView.imageScale(gameGoods, 90, 150);
    }
}
