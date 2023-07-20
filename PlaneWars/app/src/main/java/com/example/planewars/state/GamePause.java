package com.example.planewars.state;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.planewars.view.MyView;
import com.example.planewars.R;

public class GamePause extends GameState {
    private Bitmap back;
    private Bitmap resume;
    private Bitmap button1, button1_pressed;
    private Bitmap button2, button2_pressed;

    private int resume_x, resume_y;
    private int button1_x, button1_y;
    private int button2_x, button2_y;

    private boolean isPressed1;
    private boolean isPressed2;


    public GamePause(){
        super();
    }

    public GamePause(Resources res){
        super(res);
    }

    @Override
    public void init(Resources res) {
        super.init(res);
    }

    @Override
    public void initPos() {
        super.initPos();

        resume_x = (MyView.screenW - resume.getWidth())/2;
        resume_y = (MyView.screenH - resume.getHeight())/2;

        button2_x = (MyView.screenW - button2.getWidth())/2;
        button2_y = MyView.screenH - button2.getHeight() - 100;

        button1_x = (MyView.screenW - button1.getWidth())/2;
        button1_y = button2_y - button1.getHeight() - 100;
    }

    @Override
    public void initBitmap() {
        super.initBitmap();
        back = BitmapFactory.decodeResource(res, R.drawable.background);
        back = MyView.initBitmap(back);

        resume = BitmapFactory.decodeResource(res, R.drawable.game_resume_nor);

        button1 = BitmapFactory.decodeResource(res, R.drawable.game_again);
        button1_pressed = BitmapFactory.decodeResource(res, R.drawable.game_again_pressed);

        button2 = BitmapFactory.decodeResource(res, R.drawable.game_over);
        button2_pressed = BitmapFactory.decodeResource(res, R.drawable.game_over_pressed);


    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(back, 0, 0, paint);
        canvas.drawBitmap(resume, resume_x, resume_y, paint);

        if(isPressed1){
            canvas.drawBitmap(button1_pressed, button1_x, button1_y, paint);
        }else{
            canvas.drawBitmap(button1, button1_x, button1_y, paint);
        }

        if(isPressed2){
            canvas.drawBitmap(button2_pressed, button2_x, button2_y, paint);
        }else{
            canvas.drawBitmap(button2, button2_x, button2_y, paint);
        }
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){

        }else if(event.getAction() == MotionEvent.ACTION_UP) {
            //判断是否仍点击按钮，防止移到别处
            if (getBorder(resume, resume_x, resume_y).contains(x, y)) {
                process.setGameState(Process.GAMING);
            }
        }

        //重新开始
        if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
            //判断是否点击按钮
            if(getBorder(button1, button1_x, button1_y).contains(x, y)){
                isPressed1 = true;
            }else{
                isPressed1 = false;
            }
            //抬起时
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            //判断是否仍点击按钮，防止移到别处
            if(getBorder(button1, button1_x, button1_y).contains(x, y)){
                isPressed1 = false;
                //重新开始
                process.setGameState(Process.GAME_START);
            }
        }

        //结束游戏
        if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
            //判断是否点击按钮
            if(getBorder(button2, button2_x, button2_y).contains(x, y)){
                isPressed2 = true;
            }else{
                isPressed2 = false;
            }
            //抬起时
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            //判断是否仍点击按钮，防止移到别处
            if(getBorder(button2, button2_x, button2_y).contains(x, y)){
                isPressed2 = false;
                //直接结束游戏，无得分
                MyView.sumScore = 0;
                process.setGameState(Process.GAME_OVER);
            }
        }

    }
}
