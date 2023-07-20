package com.example.planewars.state;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.planewars.R;

public class Process {
    public final static GameStart GAME_START = new GameStart();
    public final static Gaming GAMING = new Gaming();
    public final static GamePause GAME_PAUSE = new GamePause();
    public final static GameOver GAME_OVER = new GameOver();

    private GameState gameState;

    public Process(){}

    public Process(Resources res){
        GAME_START.init(res);
        GAMING.init(res);
        GAME_PAUSE.init(res);
        GAME_OVER.init(res);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        this.gameState.setProcess(this);
    }

    public void gameDraw(Canvas canvas, Paint paint){
        this.gameState.draw(canvas, paint);
    }

    public void gameLogic(){
        this.gameState.logic();
    }

    public void gameOnTouchEvent(MotionEvent event){
        this.getGameState().onTouchEvent(event);
    }


}
