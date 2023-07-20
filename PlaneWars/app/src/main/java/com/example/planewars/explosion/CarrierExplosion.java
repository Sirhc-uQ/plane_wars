package com.example.planewars.explosion;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class CarrierExplosion extends Explosion {

    public CarrierExplosion(Resources res){
        super(res);
        SPEED = 4;
    }

    protected void initBitmap(){
        boom1 = BitmapFactory.decodeResource(res, R.drawable.enemy3_down1);
        boom2 = BitmapFactory.decodeResource(res, R.drawable.enemy3_down2);
        boom3 = BitmapFactory.decodeResource(res, R.drawable.enemy3_down3);
        boom4 = BitmapFactory.decodeResource(res, R.drawable.enemy3_down4);
        boom5 = BitmapFactory.decodeResource(res, R.drawable.enemy3_down5);

        boom1 = MyView.imageScale(boom1, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
        boom2 = MyView.imageScale(boom2, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
        boom3 = MyView.imageScale(boom3, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
        boom4 = MyView.imageScale(boom4, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
        boom5 = MyView.imageScale(boom5, GameConstant.CARRIER_WIDTH, GameConstant.CARRIER_HEIGHT);
    }

}
