package com.example.planewars.Constant;

public abstract class GameData {
    /**
     * 游戏状态
     */
    public final static int GAME_START = 0;
    public final static int GAME_ING = 1;
    public final static int GAME_OVER = 2;
    public final static int GAME_PAUSE = 3;
    public final static int GAME_INIT_HERO = 4;
    public final static int GAME_EXIT = 5;

    /**
     * 线程休息
     */
    public static int SLEEP_TIME = 50;

    /**
     * 游戏数值
     */
    public static int CREATE_ENEMY_TIME = 150;
    //炸弹物品出现
    public static boolean BOMBGOODS_APPEARANCE = true;

    //导弹物品出现
    public static boolean MISSILEGOODS_APPEARANCE = true;

    //生命物品是否出现
    public static boolean LIFEGOODS_APPEARANCE = true;

    //子弹1物品是否出现
    public static boolean BULLETGOODS1_APPEARANCE= true;

    //子弹2物品是否出现
    public static boolean BULLETGOODS2_APPEARANCE = true;

}
