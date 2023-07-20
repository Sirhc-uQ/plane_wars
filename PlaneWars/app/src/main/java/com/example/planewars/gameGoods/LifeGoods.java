package com.example.planewars.gameGoods;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class LifeGoods extends GameGoods {

    public LifeGoods(Resources res){
        super(res);
    }

    protected void initBitmap(){
        gameGoods = BitmapFactory.decodeResource(res, R.drawable.hero_hp);

        gameGoods = MyView.imageScale(gameGoods, 70, 64);
    }
}
