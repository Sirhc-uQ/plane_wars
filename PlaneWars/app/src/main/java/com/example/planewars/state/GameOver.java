package com.example.planewars.state;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.planewars.MainActivity;
import com.example.planewars.MyApp;
import com.example.planewars.R;
import com.example.planewars.view.MyView;

public class GameOver extends GameState {
    private Bitmap back;
    //重新开始和结束游戏
    private Bitmap button1, button1_pressed;
    private Bitmap button2, button2_pressed;

    private int button1_x, button1_y;
    private int button2_x, button2_y;

    private boolean isPressed1;
    private boolean isPressed2;

    private int score, maxScore;

    private int score_x, score_y;
    private int maxScore_x, maxScore_y;

    public GameOver(){
        super();
    }

    public GameOver(Resources res){
        super(res);
    }


    @Override
    public void init(Resources res) {
        super.init(res);
    }

    @Override
    public void initPos() {
        super.initPos();
        button2_x = (MyView.screenW - button2.getWidth())/2;
        button2_y = MyView.screenH - button2.getHeight() - 100;

        button1_x = (MyView.screenW - button1.getWidth())/2;
        button1_y = button2_y - button1.getHeight() - 100;
        maxScore = MainActivity.score;
    }

    @Override
    public void initBitmap() {
        super.initBitmap();
        back = BitmapFactory.decodeResource(res, R.drawable.back_gameover);
        back = MyView.initBitmap(back);

        button1 = BitmapFactory.decodeResource(res, R.drawable.game_again);
        button1_pressed = BitmapFactory.decodeResource(res, R.drawable.game_again_pressed);

        button2 = BitmapFactory.decodeResource(res, R.drawable.game_over);
        button2_pressed = BitmapFactory.decodeResource(res, R.drawable.game_over_pressed);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(back, 0, 0, paint);
        drawScore(canvas, paint);

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
                //结束游戏
                MyApp.getIntance().removeALLActivity_();
            }
        }
    }

    //绘制分数
    private void drawScore(Canvas canvas, Paint paint){
        String temp1 = MyView.sumScore + "";
        String temp2 = maxScore + "";
        paint.setStrokeWidth(3);
        paint.setColor(Color.GRAY);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        Rect bounds1 = new Rect();
        Rect bounds2 = new Rect();
        paint.getTextBounds(temp1, 0, temp1.length(), bounds1);
        paint.getTextBounds(temp2, 0, temp1.length(), bounds2);

        score_x = MyView.screenW/2;
        score_y = MyView.screenH/3;
        maxScore_x = score_x;
        maxScore_y = MyView.screenH/6;

        canvas.drawText(temp1, score_x, score_y, paint);
        canvas.drawText(temp2, maxScore_x, maxScore_y, paint);
    }
}
