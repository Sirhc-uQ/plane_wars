package com.example.planewars.sounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.planewars.R;

public class GameSounds {
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private Context context;
    private int shoot, bomb;
    private int explosion1, explosion2, explosion3;
    private int collision, gameover;
    private int getGoods;
    private int button;

    public GameSounds(Context context){
        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.game_music);
        soundPool = new SoundPool(9, AudioManager.STREAM_MUSIC, 0);
        shoot = soundPool.load(context, R.raw.bullet, 1);
        explosion1 = soundPool.load(context, R.raw.enemy1_down, 1);
        explosion2 = soundPool.load(context, R.raw.enemy2_down, 1);
        explosion3 = soundPool.load(context, R.raw.enemy3_down, 1);
        bomb = soundPool.load(context, R.raw.use_bomb, 1);
        collision = soundPool.load(context, R.raw.hero_collision, 1);
        gameover = soundPool.load(context,R.raw.game_over, 1);
        getGoods = soundPool.load(context, R.raw.get_bullets, 1);
        button = soundPool.load(context, R.raw.button, 1);
    }

    public void playBgm(){
        mediaPlayer.start();
    }

    public void pauseBgm(){
        mediaPlayer.stop();
    }

    public void playShooting(){
        soundPool.play(shoot, 1,1,1,0,1);
    }

    public void playExplosion1(){
        soundPool.play(explosion1,1,1,1,0,1);
    }

    public void playExplosion2(){
        soundPool.play(explosion2,1,1,1,0,1);
    }

    public void playExplosion3(){
        soundPool.play(explosion3,1,1,1,0,1);
    }

    public void playBomb(){
        soundPool.play(bomb,1,1,1,0,1);
    }

    public void playCollision(){
        soundPool.play(collision,1,1,1,0,1);
    }

    public void playGetBullets(){
        soundPool.play(getGoods,1,1,1,0,1);
    }

    public void playGameOver(){
        soundPool.play(gameover,1,1,1,0,1);
    }

    public void playButton(){
        soundPool.play(button,1,1,1,0,1);
    }

    public void release(){
        mediaPlayer.release();
        soundPool.release();
    }

}
