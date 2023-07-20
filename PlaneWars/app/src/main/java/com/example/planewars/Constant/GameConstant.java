package com.example.planewars.Constant;

public abstract class GameConstant {
    public static int currentCount = 0;

    //初始
    public static int HP_AMOUNT = 3;
    public static int HP_MAX_AMOUNT = 9;
    public static int GAME_SPEED = 1;
    public static int MAX_GRADE = 6;
    public static int LEVELUP_SCORE = 10000;

    //主角类型
    public final static int TYPE_HERO_1 = 1;
    public final static int TYPE_HERO_2 = 2;
    public final static int TYPE_HERO_3 = 3;

    //敌机数量
    public static int AIRPLANE_SUM = 10;
    public static int WARCRAFT_SUM = 5;
    public static int CARRIER_SUM = 3;
    public static int BOSS_SUM = 1;

    //敌机血量
    public static int AIRPLANE_HP = 1;
    public static int WARCRAFT_HP = 5;
    public static int CARRIER_HP = 15;
    public static int BOSS_HP = 200;
    public static int BOSS_ANGER_HP = 100;
    public static int BOSS_CRAZY_HP = 50;

    //击毁敌机得分
    public static int AIRPLANE_SCORE = 100;
    public static int WARCRAFT_SCORE = 300;
    public static int CARRIRT_SCORE = 800;
    public static int BOSS_SCORE = 2000;

    //物体出现相应的分数条件
    public static int WARCRAFT_APPEARANCE = 2000;// 中型机
    public static int CARRIER_APPEARANCE = 4000;// 大型机
    public static int BOSSPLANE_APPEARANCE = 30000;// Boss
    public static int MISSILE_APPEARANCE = 5000;// 导弹
    public static int LIFE_APPEARANCE = 10000;// 生命
    public static int BULLET1_APPEARANCE = 3000;// 子弹1
    public static int BULLET2_APPEARANCE = 7000;// 子弹2
    public static int BOMB_APPEARANCE = 20000;

    //物体消失计数
    public static int GOODS_FADE = 300;

    //子弹速度
    public static int YELLOW_BULLET_SPEED = 50;
    public static int BLUE_BULLET_SPEED = 60;
    public static int LIGHT_BULLET_SPEED = 70;
    public static int ROUND_BULLET_SPEED = 30;
    public static int MISSILE_SPEED = 80;

    //子弹伤害
    public static int YELLOW_ATK = 1;
    public static int BLUE_ATK = 1;
    public static int LIGHT_ATK = 2;
    public static int ROUND_ATK = 5;
    public static int MISSILE_ATK = 10;
    public static int MYPLANE_SPEED = 30;

    //子弹数量
    public static int MY_BLUE_AMOUNT = 50;
    public static int MY_MISSILE_AMOUNT = 10;

    //移动方向
    public static int DIR_LEFT_UP = 1;
    public static int DIR_RIGHT_UP = 2;
    public static int DIR_LEFT_DOWN = 3;
    public static int DIR_RIGHT_DOWN = 4;
    public static int DIR_LEFT = 5;
    public static int DIR_RIGHT = 6;
    public static int DIR_UP = 7;
    public static int DIR_DOWN = 8;

    /**
     * 各种组件的大小
     */
    //hero1
    public static int HERO_1_WIDTH = 160;
    public static int HERO_1_HEIGHT = 160;
    //hero2
    public static int HERO_2_WIDTH = 160;
    public static int HERO_2_HEIGHT = 256;
    //hero3
    public static int HERO_3_WIDTH = 200;
    public static int HERO_3_HEIGHT = 131;
    //HP
    public static int HP_WIDTH = 70;
    public static int HP_HEIGHT = 64;
    //敌机
    public static int AIRPLANE_WIDTH = 100;
    public static int AIRPLANE_HEIGHT = 100;
    public static int WARCRAFT_WIDTH = 140;
    public static int WARCRAFT_HEIGHT = 200;
    public static int CARRIER_WIDTH = 340;
    public static int CARRIER_HEIGHT = 520;
    //子弹
    public static int YELLOW_BULLET_WIDTH = 20;
    public static int YELLOW_BULLET_HEIGHT = 50;
    public static int BLUE_BULLET_WIDTH = 14;
    public static int BLUE_BULLET_HEIGHT = 33;
    public static int MISSILE_WIDTH = 72;
    public static int MISSILE_HEIGHT = 183;
    public static int MISSILE_LAUNCH_HEIGHT = 126;
    public static int ROUND_BULLET_WIDTH = 100;
    public static int ROUND_BULLET_HEIGHT = 100;
    public static int LIGHT_BULLET_WIDTH = 16;
    public static int LIGHT_BULLET_HEIGHT = 60;

}
