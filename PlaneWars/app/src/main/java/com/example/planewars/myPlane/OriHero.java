package com.example.planewars.myPlane;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class OriHero extends MyPlane {

    public OriHero(Resources res){
        super(res);
        type = GameConstant.TYPE_HERO_1;
    }

    protected void initBitmap(){
        super.initBitmap();
        hero1 = BitmapFactory.decodeResource(res, R.drawable.hero1);
        hero2 = BitmapFactory.decodeResource(res, R.drawable.hero2);

        hero1 = MyView.imageScale(hero1, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        hero2 = MyView.imageScale(hero2, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
    }
}
