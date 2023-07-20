package com.example.planewars.state;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.planewars.Constant.GameConstant;
import com.example.planewars.Constant.GameData;
import com.example.planewars.MainActivity;
import com.example.planewars.view.MyView;
import com.example.planewars.R;
import com.example.planewars.background.Background;
import com.example.planewars.bullet.Bullets;
import com.example.planewars.bullet.Missile;
import com.example.planewars.bullet.MyBlueBullet;
import com.example.planewars.bullet.MyLightBullet;
import com.example.planewars.bullet.MyRoundBullet;
import com.example.planewars.bullet.MyYellowBullet;
import com.example.planewars.enemys.Airplane;
import com.example.planewars.enemys.Carrier;
import com.example.planewars.enemys.Enemy;
import com.example.planewars.enemys.Warcraft;
import com.example.planewars.explosion.AirplaneExplosion;
import com.example.planewars.explosion.CarrierExplosion;
import com.example.planewars.explosion.Explosion;
import com.example.planewars.explosion.MyplaneExplosion;
import com.example.planewars.explosion.WarcraftExplosion;
import com.example.planewars.myPlane.ExtraHero1;
import com.example.planewars.myPlane.ExtraHero2;
import com.example.planewars.myPlane.MyPlane;
import com.example.planewars.myPlane.OriHero;
import com.example.planewars.gameGoods.BlueBulletGoods;
import com.example.planewars.gameGoods.BombGoods;
import com.example.planewars.gameGoods.GameGoods;
import com.example.planewars.gameGoods.LifeGoods;
import com.example.planewars.gameGoods.MissileGoods;
import java.util.Vector;

public class Gaming extends GameState {
    //记录login时，输入的用户名
    private String username = MainActivity.username;

    private Bitmap background;

    private Background back;

    //游戏组件存储
    private Vector<Bullets> heroMagazine;
    private Vector<Explosion> explosionVector;
    private Vector<Enemy> enemyPlane;
    private Vector<MyPlane> heroPlane;
    //指针
    private Enemy newEnemy;
    private Bullets newBullet;
    private Explosion newExplosion;
    private GameGoods newGoods;
    //物品
    private Vector<GameGoods> gameGoods;
    private boolean GotBlueBullet = false;
    private boolean GotMissile = false;
    private boolean GotBomb = false;
    //创造敌机时间
    private int createEnemyTime = GameData.CREATE_ENEMY_TIME;
    //用来计时
    private int countCreatEnemy;
    private int countHeroBullet;
    //各种积分
    private int warcraftScore;
    private int carrierScore;
    private int lifeScore;
    private int bulletScore;
    private int missileScore;
    private int bombScore;
    private int sumScore;
    //敌机速度增加
    private int speedtimes = GameConstant.GAME_SPEED;
    //子弹计数
    private int blueCount;
    private int missileCount;
    //主角
    private MyPlane myPlane;
    private Explosion myplaneExplosion;
    private int type;
    private Boolean isInit;


    private Bitmap pause, resume;

    private int name_x, name_y;
    private int pause_x, pause_y;

    private Rect bounds1, bounds2;

    //记录游戏时间
    private long start;

    public Gaming(){
        super();
    }

    public Gaming(Resources res){
        super(res);
    }

    @Override
    public void init(Resources res) {
        super.init(res);
        initGame();
        initObject();
    }

    @Override
    public void initPos() {
        super.initPos();

        name_x = MyView.screenW-150;
        name_y = 50;
        pause_x = 0;
        pause_y = 0;

        bounds1 = new Rect();
        bounds2 = new Rect();
    }

    @Override
    public void initBitmap() {
        super.initBitmap();

        pause = BitmapFactory.decodeResource(res, R.drawable.game_pause_pressed);
        resume = BitmapFactory.decodeResource(res, R.drawable.game_resume_pressed);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if(isInit) {
            back.draw(canvas, paint);

            //主角子弹绘制
            for (int i = 0; i < heroMagazine.size(); i++) {
                heroMagazine.elementAt(i).draw(canvas, paint);
            }

            myPlane.draw(canvas, paint);


            //物品绘制
            for (int i = 0; i < gameGoods.size(); i++) {
                gameGoods.elementAt(i).draw(canvas, paint);
            }
            //敌机绘制
            for (int i = 0; i < enemyPlane.size(); i++) {
                enemyPlane.elementAt(i).draw(canvas, paint);
            }
            //爆炸
            for (int i = 0; i < explosionVector.size(); i++) {
                explosionVector.elementAt(i).draw(canvas, paint);
            }
            if (myPlane.getState()) {
                myplaneExplosion.draw(canvas, paint);
            }

            canvas.drawBitmap(pause, pause_x, pause_y, paint);

            drawNameAndTime(canvas, paint);

            drawScore(canvas, paint);
        }

    }

    @Override
    public void logic() {
        if(isInit) {

            back.logic();

            myPlane.logic();

            //游戏组件逻辑
            gameObjectsLogic();

            //碰撞逻辑
            collisionLogic();

            //增加游戏物品
            gameGoodsLogic();

            /**
             * 生成主角子弹
             */
            heroBulletLogic();

            //子弹1
            if (GotBlueBullet && !myPlane.getState() && countHeroBullet % 10 == 0) {
                heroMagazine.add(new MyBlueBullet(res));
                heroMagazine.lastElement().init(0, myPlane.getCurrentX(), myPlane.getCurrentY());
                //播放音效
                MyView.gameSounds.playShooting();
                blueCount++;
                if (blueCount > GameConstant.MY_BLUE_AMOUNT) {
                    //子弹1设置为可出现，并重新计数
                    GameData.BULLETGOODS1_APPEARANCE = true;
                    GotBlueBullet = false;
                    bulletScore = 0;
                    blueCount = 0;
                }
            }
            //导弹
            if (GotMissile && countHeroBullet % 80 == 0) {
                heroMagazine.add(new Missile(res));
                heroMagazine.lastElement().init(0, myPlane.getCurrentX(), myPlane.getCurrentY());
                //播放音效
                MyView.gameSounds.playShooting();
                missileCount++;
                if (missileCount > GameConstant.MY_MISSILE_AMOUNT) {
                    //导弹设置为可出现，重新计数
                    GameData.MISSILEGOODS_APPEARANCE = true;
                    GotMissile = false;
                    missileScore = 0;
                    missileCount = 0;
                }
            }
            //炸弹
            if (GotBomb) {
                enemyExplosion();
                MyView.gameSounds.playBomb();
                GotBomb = false;
            }

            // 提升等级
            if (sumScore >= speedtimes * GameConstant.LEVELUP_SCORE && speedtimes < GameConstant.MAX_GRADE) {
                speedtimes++;
            }

            //增加到创造时间时，读入下一组敌机
            countCreatEnemy++;
            if (countCreatEnemy % createEnemyTime == 0) {
                addEnemy();
            }

            //主角爆炸
            if (myPlane.getHP() <= 0) {
                MyView.gameSounds.playGameOver();
                myPlane.setState(true);
                myplaneExplosion.init(0, myPlane.getCurrentX(), myPlane.getCurrentY());
                myplaneExplosion.logic();
            }

            //切换结束状态
            if(myplaneExplosion.getState()){
                MyView.sumScore = sumScore;
                MainActivity.setScore(sumScore);
                process.setGameState(Process.GAME_OVER);
            }

        }else{
            initHero();
        }

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        myPlane.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            //判断是否仍点击按钮，防止移到别处
            if(getBorder(pause, pause_x, pause_y).contains(x, y)){
                release();
                process.setGameState(Process.GAME_PAUSE);
            }
        }
    }

    //初始化
    private void initGame() {

        //读出位图到临时位图bmpBackGround1进行适合屏幕变换后保存到background
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = MyView.initBitmap(background);

        process = new Process();
        process.setGameState(new GameStart(res));

        //对组件进行初始化
        heroMagazine = new Vector<Bullets>();
        enemyPlane = new Vector<Enemy>();
        explosionVector = new Vector<Explosion>();
        gameGoods = new Vector<GameGoods>();
        back = new Background(background);

        MyView.start = 0;

    }

    //初始化数据
    private void initObject(){
        //初始化物品
        GotBlueBullet = false;
        GotMissile = false;
        GotBomb = false;
        //初始化子弹
        countCreatEnemy = 0;
        countHeroBullet = 0;
        //初始化额外子弹数量
        blueCount = 0;
        missileCount = 0;
        //初始化分数
        warcraftScore = 0;
        carrierScore = 0;
        lifeScore = 0;
        bulletScore = 0;
        missileScore = 0;
        bombScore = 0;
        sumScore = 0;

        isInit = false;
    }

    //初始化英雄
    private void initHero(){
        switch (Process.GAME_START.heroType){
            default:
                myPlane = new OriHero(res);
            case GameConstant.TYPE_HERO_1:{
                myPlane = new OriHero(res);
                break;
            }
            case GameConstant.TYPE_HERO_2: {
                myPlane = new ExtraHero1(res);
                break;
            }
            case GameConstant.TYPE_HERO_3:{
                myPlane = new ExtraHero2(res);
                break;
            }
        }
        myPlane.init(0,0,0);
        myplaneExplosion = new MyplaneExplosion(res);
        type = myPlane.getType();
        isInit = true;
    }

    /**
     * 生成主角原始子弹
     */
    private void heroBulletLogic(){
        //弹药计数
        countHeroBullet++;
        //原始子弹
        if(!myPlane.getState()){
            //不同主角飞机的子弹生成
            switch (type){
                case GameConstant.TYPE_HERO_1: {
                    if (countHeroBullet % 5 == 0) {
                        heroMagazine.add(new MyYellowBullet(res));
                        heroMagazine.lastElement().init(0, myPlane.getCurrentX(), myPlane.getCurrentY());
                        MyView.gameSounds.playShooting();
                    }
                    break;
                }
                case GameConstant.TYPE_HERO_2: {
                    if (countHeroBullet % 20 == 0) {
                        heroMagazine.add(new MyRoundBullet(res));
                        heroMagazine.lastElement().init(0, myPlane.getCurrentX(), myPlane.getCurrentY());
                        MyView.gameSounds.playShooting();
                    }
                    break;
                }
                case GameConstant.TYPE_HERO_3: {
                    if (countHeroBullet % 15 == 0) {
                        heroMagazine.add(new MyLightBullet(res));
                        heroMagazine.lastElement().init(0, myPlane.getCurrentX(), myPlane.getCurrentY());
                        MyView.gameSounds.playShooting();
                    }
                    break;
                }
            }
        }
    }

    //游戏物品逻辑
    private void gameObjectsLogic(){
        //敌机逻辑
        for (int i = 0; i < enemyPlane.size(); i++) {
            newEnemy = enemyPlane.elementAt(i);

            if (newEnemy.getState()) {
                addGameScore(newEnemy.getScore());
                enemyPlane.elementAt(i).release();
                enemyPlane.removeElementAt(i);
            } else {
                newEnemy.logic();
            }
        }
        //主角子弹逻辑
        for(int i = 0;i < heroMagazine.size();i++){
            newBullet = heroMagazine.elementAt(i);

            if(newBullet.getState()){
                heroMagazine.elementAt(i).release();
                heroMagazine.removeElementAt(i);
            }else{
                newBullet.logic();
            }
        }
        //爆炸逻辑
        for(int i = 0;i < explosionVector.size();i++){
            newExplosion = explosionVector.elementAt(i);

            if(newExplosion.getState()){
                explosionVector.elementAt(i).release();
                explosionVector.removeElementAt(i);
            }else{
                newExplosion.logic();
            }
        }
    }

    //所有组件的碰撞逻辑
    private void collisionLogic(){
        /**
         * 主角和物品碰撞
         */
        for(GameGoods goods : gameGoods){
            //子弹1物品碰撞
            if(goods instanceof BlueBulletGoods){
                if(myPlane.isCollidedWith(goods)) {
                    MyView.gameSounds.playGetBullets();
                    //将子弹1物品设置为不出现
                    GotBlueBullet = true;
                    GameData.BULLETGOODS1_APPEARANCE = false;
                    //物品设置为已使用
                    goods.setState(true);
                    break;
                }
            }
            //导弹物品碰撞
            if(goods instanceof MissileGoods){
                if(myPlane.isCollidedWith(goods)){
                    MyView.gameSounds.playGetBullets();
                    GotMissile = true;
                    GameData.MISSILEGOODS_APPEARANCE = false;
                    goods.setState(true);
                    break;
                }
            }
            //炸弹物品碰撞
            if(goods instanceof BombGoods){
                if(myPlane.isCollidedWith(goods)) {
                    GotBomb = true;
                    //GameData.BOMBGOODS_APPEARANCE = false;
                    //物品设置为已使用
                    goods.setState(true);
                    break;
                }
            }
            //加血物品碰撞
            if(goods instanceof LifeGoods){
                if(myPlane.isCollidedWith(goods)) {
                    if(myPlane.getHP() < GameConstant.HP_MAX_AMOUNT){
                        myPlane.setHP(myPlane.getHP()+1);
                    }
                    //物品设置为已使用
                    goods.setState(true);
                    break;
                }
            }
        }

        //主角和敌机碰撞掉血
        for (int i = 0; i < enemyPlane.size(); i++) {
            newEnemy = enemyPlane.elementAt(i);
            if (myPlane.isCollidedWith(newEnemy)) {
                MyView.gameSounds.playCollision();
                //子弹1清除
                GameData.BULLETGOODS1_APPEARANCE = true;
                //导弹清除
                GameData.MISSILEGOODS_APPEARANCE = true;
                GotBlueBullet = false;
                GotMissile = false;
                bulletScore = 0;

                //进行扣血
                myPlane.setHP(myPlane.getHP() - 1);
            }
        }

        //主角子弹和敌机的碰撞
        for (int i = 0; i < heroMagazine.size();i++){
            newBullet = heroMagazine.elementAt(i);
            for(int j = 0;j < enemyPlane.size();j++){
                newEnemy = enemyPlane.elementAt(j);
                if(newBullet.isCollidedWith(newEnemy)){
                    newEnemy.setHp(newEnemy.getHP()-newBullet.getATK());
                    newEnemy.setHit(true);
                }
            }
        }

        //设置敌机爆炸
        for(Enemy enemy : enemyPlane){
            if(enemy.getHP() <= 0){
                enemy.setState(true);
                if(enemy instanceof Airplane){
                    explosionVector.add(new AirplaneExplosion(res));
                    explosionVector.lastElement().init(0, enemy.getCurrentX(), enemy.getCurrentY());
                    MyView.gameSounds.playExplosion1();
                    break;
                }
                if(enemy instanceof Warcraft){
                    explosionVector.add(new WarcraftExplosion(res));
                    explosionVector.lastElement().init(0, enemy.getCurrentX(), enemy.getCurrentY());
                    MyView.gameSounds.playExplosion2();
                    break;
                }
                if(enemy instanceof Carrier){
                    explosionVector.add(new CarrierExplosion(res));
                    explosionVector.lastElement().init(0, enemy.getCurrentX(), enemy.getCurrentY());
                    MyView.gameSounds.playExplosion3();
                    break;
                }
            }
        }

    }

    //游戏物品逻辑
    private void gameGoodsLogic(){
        //出现子弹1物品
        if(GameData.BULLETGOODS1_APPEARANCE && bulletScore > GameConstant.BULLET1_APPEARANCE){
            //加入游戏物品Vector
            gameGoods.add(new BlueBulletGoods(res));
            gameGoods.lastElement().init(0,0,0);
            bulletScore = 0;
        }

        //出现导弹物品
        if(GameData.MISSILEGOODS_APPEARANCE && missileScore > GameConstant.MISSILE_APPEARANCE){
            gameGoods.add(new MissileGoods(res));
            gameGoods.lastElement().init(0,0,0);
            missileScore = 0;
        }

        //出现炸弹物品
        if(GameData.BOMBGOODS_APPEARANCE && bombScore > GameConstant.BOMB_APPEARANCE){
            gameGoods.add(new BombGoods(res));
            gameGoods.lastElement().init(0,0,0);
            bombScore = 0;
        }

        //出现加血物品
        if(GameData.LIFEGOODS_APPEARANCE && lifeScore > GameConstant.LIFE_APPEARANCE){
            gameGoods.add(new LifeGoods(res));
            gameGoods.lastElement().init(0,0,0);
            lifeScore = 0;
        }

        //物品与主角碰撞后，从Vector中移去
        for (int i = 0; i<gameGoods.size(); i++){
            newGoods = gameGoods.elementAt(i);
            if(newGoods.getState()){
                newGoods.release();
                gameGoods.removeElementAt(i);
            }else{
                newGoods.logic();
            }
        }
    }

    //读入敌机
    private void addEnemy(){
        for(int i = 0;i < GameConstant.AIRPLANE_SUM;i++){
            newEnemy = new Airplane(res);
            newEnemy.init(speedtimes,0,0);
            enemyPlane.add(newEnemy);
        }

        if(warcraftScore > GameConstant.WARCRAFT_APPEARANCE) {
            for (int i = 0; i < GameConstant.WARCRAFT_SUM; i++) {
                newEnemy = new Warcraft(res);
                newEnemy.init(speedtimes, 0, 0);
                enemyPlane.add(newEnemy);
            }
            warcraftScore = 0;
        }

        if(carrierScore > GameConstant.CARRIER_APPEARANCE) {
            for (int i = 0; i < GameConstant.CARRIER_SUM; i++) {
                newEnemy = new Carrier(res);
                newEnemy.init(speedtimes, 0, 0);
                enemyPlane.add(newEnemy);
            }
            carrierScore = 0;
        }
    }

    //炸毁当前所有敌机
    private void enemyExplosion(){
        for(Enemy enemy : enemyPlane){
            if(enemy.getCurrentY() > -3*enemy.getObjectH()){
                //设置血量
                enemy.setHp(0);
                //设置血量后，爆炸logic中会添加敌机的爆炸
                enemy.setHp(0);
            }
        }
    }

    //绘制用户名和进行时间
    private void drawNameAndTime(Canvas canvas, Paint paint){
        paint.setStrokeWidth(3);
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);

        start = MyView.start;
        String time = "TIME: "+start+" S";
        paint.getTextBounds(username, 0, username.length(), bounds1);
        paint.getTextBounds(time, 0, time.length(), bounds2);

        canvas.drawText(username, MyView.screenW-150, 50, paint);
        canvas.drawText(time, MyView.screenW/2, 50, paint);

    }

    //绘制分数
    private void drawScore(Canvas canvas, Paint paint){
        String temp = sumScore + "";
        paint.setStrokeWidth(3);
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        Rect bounds = new Rect();
        paint.getTextBounds(temp, 0, temp.length(), bounds);
        canvas.drawText(temp, MyView.screenW-150, 150, paint);
    }

    //增加游戏分数的方法
    private void addGameScore(int score) {
        warcraftScore += score; // 中型敌机的积分
        carrierScore += score; // 大型敌机的积分
        //bossScore += score; // boss型敌机的积分
        missileScore += score; // 导弹的积分
        bombScore += score;
        lifeScore += score;// 生命的积分
        bulletScore += score; // 子弹的积分
        sumScore += score; // 游戏总得分

    }

    private void release(){
        for(int i = 0;i < heroMagazine.size();i++){
            heroMagazine.elementAt(i).release();
            heroMagazine.removeElementAt(i);
        }
        for(int i = 0;i < enemyPlane.size();i++){
            enemyPlane.elementAt(i).release();
            enemyPlane.removeElementAt(i);
        }
        for(int i = 0;i < explosionVector.size();i++){
            explosionVector.elementAt(i).release();
            explosionVector.removeElementAt(i);
        }
    }
}
