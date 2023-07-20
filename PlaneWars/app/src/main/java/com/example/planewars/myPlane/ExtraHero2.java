package com.example.planewars.myPlane;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class ExtraHero2 extends MyPlane {

    public ExtraHero2(Resources res){
        super(res);
        type = GameConstant.TYPE_HERO_3;
    }

    protected void initBitmap(){
        super.initBitmap();
        hero1 = BitmapFactory.decodeResource(res, R.drawable.extra_hero3);
        hero2 = BitmapFactory.decodeResource(res, R.drawable.extra_hero4);

        hero1 = MyView.imageScale(hero1, GameConstant.HERO_3_WIDTH, GameConstant.HERO_3_HEIGHT);
        hero2 = MyView.imageScale(hero2, GameConstant.HERO_3_WIDTH, GameConstant.HERO_3_HEIGHT);
    }
}
