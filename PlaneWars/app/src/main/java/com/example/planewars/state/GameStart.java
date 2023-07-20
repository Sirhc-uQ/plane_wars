package com.example.planewars.state;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.R;
import com.example.planewars.view.MyView;

public class GameStart extends GameState {
    //存储英雄种类
    public static int heroType;

    private Bitmap bgMenu;
    private Bitmap hero1, hero2, hero3;
    private Bitmap hero1_2, hero2_2, hero3_2;
    private Bitmap button, button_pressed;
    private Bitmap flag;

    private int flag_x, flag_y;
    private int button_x, button_y;
    private int hero1_x, hero1_y;
    private int hero2_x, hero2_y;
    private int hero3_x, hero3_y;

    private boolean isChosen;
    private boolean isPressed;
    private int turn;

    public GameStart(){
        super();
    }

    public GameStart(Resources res){
        super(res);
    }

    @Override
    public void init(Resources res) {
        super.init(res);
    }

    public void init(){}

    @Override
    public void initPos() {
        super.initPos();
        button_x = (MyView.screenW - button.getWidth())/2;
        button_y = MyView.screenH - button.getHeight() - 50;
        //放置三个英雄的位置
        hero2_x = (MyView.screenW - hero2.getWidth())/2;
        hero2_y = MyView.screenH/3 * 2;
        hero1_x = hero2_x -160-hero1.getWidth();
        hero1_y = hero2_y;
        hero3_x = hero2_x +hero2.getWidth()+160;
        hero3_y = hero2_y;
    }

    @Override
    public void initBitmap() {
        super.initBitmap();
        bgMenu = BitmapFactory.decodeResource(res, R.drawable.bgmenu);
        flag = BitmapFactory.decodeResource(res, R.drawable.hero_hp);
        hero1 = BitmapFactory.decodeResource(res, R.drawable.hero1);
        hero2 = BitmapFactory.decodeResource(res, R.drawable.extra_hero1);
        hero3 = BitmapFactory.decodeResource(res, R.drawable.extra_hero3);
        hero1_2 = BitmapFactory.decodeResource(res, R.drawable.hero2);
        hero2_2 = BitmapFactory.decodeResource(res, R.drawable.extra_hero2);
        hero3_2 = BitmapFactory.decodeResource(res, R.drawable.extra_hero4);

        button = BitmapFactory.decodeResource(res, R.drawable.game_start);
        button_pressed = BitmapFactory.decodeResource(res, R.drawable.game_start_pressed);

        bgMenu = MyView.initBitmap(bgMenu);
        //对图片进行缩放
        flag = MyView.imageScale(flag, GameConstant.HP_WIDTH, GameConstant.HP_HEIGHT);
        hero1 = MyView.imageScale(hero1, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        hero1_2 = MyView.imageScale(hero1_2, GameConstant.HERO_1_WIDTH, GameConstant.HERO_1_HEIGHT);
        hero2 = MyView.imageScale(hero2, GameConstant.HERO_2_WIDTH, GameConstant.HERO_2_HEIGHT);
        hero2_2 = MyView.imageScale(hero2_2, GameConstant.HERO_2_WIDTH, GameConstant.HERO_2_HEIGHT);
        hero3 = MyView.imageScale(hero3, GameConstant.HERO_3_WIDTH, GameConstant.HERO_3_HEIGHT);
        hero3_2 = MyView.imageScale(hero3_2, GameConstant.HERO_3_WIDTH, GameConstant.HERO_3_HEIGHT);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bgMenu, 0, 0, paint);
        //使指示标闪烁出现
        if(isChosen && turn < 10){
            canvas.drawBitmap(flag, flag_x, flag_y, paint);
        }
        //使飞机闪烁出现
        if(turn%2 == 0){
            canvas.drawBitmap(hero1, hero1_x, hero1_y, paint);
            canvas.drawBitmap(hero2, hero2_x, hero2_y, paint);
            canvas.drawBitmap(hero3, hero3_x, hero3_y, paint);
        }else{
            canvas.drawBitmap(hero1_2, hero1_x, hero1_y, paint);
            canvas.drawBitmap(hero2_2, hero2_x, hero2_y, paint);
            canvas.drawBitmap(hero3_2, hero3_x, hero3_y, paint);
        }
        //按钮
        if(isPressed){
            canvas.drawBitmap(button_pressed, button_x, button_y, paint);
        }else{
            canvas.drawBitmap(button, button_x, button_y, paint);
        }

        turn++;
        if(turn > 15){
            turn = 0;
        }

    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(getBorder(hero1, hero1_x, hero1_y).contains(x,y)){
            flag_x = hero1_x + (hero1.getWidth()-flag.getWidth())/2;
            flag_y = hero1_y - flag.getHeight()-30;
            heroType = GameConstant.TYPE_HERO_1;
            isChosen = true;
            return;
        }
        if(getBorder(hero2, hero2_x, hero2_y).contains(x,y)){
            flag_x = hero2_x + (hero2.getWidth()-flag.getWidth())/2;
            flag_y = hero2_y - flag.getHeight()-30;
            heroType = GameConstant.TYPE_HERO_2;
            isChosen = true;
            return;
        }
        if(getBorder(hero3, hero3_x, hero3_y).contains(x,y)){
            flag_x = hero3_x + (hero3.getWidth()-flag.getWidth())/2;
            flag_y = hero3_y - flag.getHeight()-30;
            heroType = GameConstant.TYPE_HERO_3;
            isChosen = true;
            return;
        }

        if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
            //判断是否点击按钮
            if(getBorder(button, button_x, button_y).contains(x, y)){
                isPressed = true;
            }else{
                isPressed = false;
            }
            //抬起时
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            //判断是否仍点击按钮，防止移到别处
            if(getBorder(button, button_x, button_y).contains(x, y)){
                isPressed = false;

                //切换游戏进行状态
                Process.GAMING.init(res);
                process.setGameState(Process.GAMING);
            }
        }
    }
}
