package com.example.planewars.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.planewars.Constant.GameData;
import com.example.planewars.sounds.GameSounds;
import com.example.planewars.state.Process;

import java.util.Random;

public class MyView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private Process process;

    private Resources res = this.getResources();

    private Context context;
    private Thread thread, time;

    private SurfaceHolder shf;
    private Canvas canvas;
    private Paint paint;

    private Random random;

    private boolean flag;

    public static int screenW, screenH;
    public static boolean isPlaying;

    public static long start;

    public static GameSounds gameSounds;

    //休息时间
    private final int sleepTime = GameData.SLEEP_TIME;

    //总得分
    public static int sumScore = 0;


    /**
     * 用来记录游戏进行时间
     */
    final Handler handler = new Handler(){          // handle
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    start++;
            }
            super.handleMessage(msg);
        }
    };
    //进行计时，每1秒发一次消息
    public class MyThread implements Runnable {// thread
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);// sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }
            }
        }
    }

    public MyView(Context context) {//实现构造方法
        super(context);
        this.context=context;//当前的上下文=当前方法上下文。

        shf=this.getHolder();//获得SurfaceHolder对象
        shf.addCallback(this);//监听surface的生命周期
        paint=new Paint();//创建画笔对象
        paint.setColor(Color.WHITE);//画背景
        paint.setAntiAlias(true);// 设置抗锯齿,文字清晰点,不过相对比较耗性能
        setFocusable(true);//设置【按键焦点】。
        setFocusableInTouchMode(true);//设置【触屏监听焦点】函数。
        //设置背景常亮
        this.setKeepScreenOn(true);

    }
    //surface大小改变时调用
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    //surface销毁时调用，一般在这里销毁绘图线程
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }
    //surface创建时调用，一般在这里开启绘图线程
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        screenW = this.getWidth();
        screenH = this.getHeight();//获取屏幕的高宽

        init();

        flag = true;
        //实列线程
        thread = new Thread(this);
        thread.start();//启动线程。
        time = new Thread(new MyThread());//计时
        time.start();
    }
    //主函数
    public void run(){
        while(flag) {
            long start = System.currentTimeMillis();//开始时间    系统时间
            myDraw();
            logic();

            long end = System.currentTimeMillis();//执行了画的方法，和画的逻辑时间后
            if(!isPlaying){//判断线程是否需要wait()
                synchronized (thread) {
                    try {
                        thread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try{
                if(end-start<sleepTime){//当执行的时间-开始时间<50
                    Thread.sleep(sleepTime-(end-start));//时间，减少CPU工作负荷
                }
            }catch(InterruptedException e){
                e.printStackTrace();//异常处理办法
            }
        }
    }

    private void init(){
        isPlaying = true;

        //设置游戏状态：开始
        process = new Process(res);
        process.setGameState(Process.GAME_START);

        gameSounds = new GameSounds(context);
    }

    private void myDraw(){
        try {
            canvas = shf.lockCanvas();// 通过SurfaceHolder对象获得 Surface的Canvas成员变量,并且锁住Canvas
            if (canvas != null) {
                process.gameDraw(canvas, paint);
            }

        } catch (Exception e) {

        } finally {
            if (canvas != null) {
                shf.unlockCanvasAndPost(canvas);//开始画
            }
        }
    }

    private void logic(){
        process.gameLogic();
    }

    //触摸回调函数重写
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        super.onTouchEvent(event);
        process.gameOnTouchEvent(event);

        return true;
    }


    //初始化图片，适应屏幕
    public static Bitmap initBitmap(Bitmap bitmap){
        Matrix matrix=new Matrix();
        float scale1 = (float)screenW/bitmap.getWidth();
        float scale2 = (float)screenH/bitmap.getHeight();
        matrix.setScale(scale1, scale2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    //缩放
    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }

}
