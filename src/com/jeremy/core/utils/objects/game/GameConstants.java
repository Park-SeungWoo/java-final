package com.jeremy.core.utils.objects.game;

public class GameConstants {
    public static class Player {
        public static final int WIDTH = 50;
        public static final int HEIGHT = 100;
        public static final int X = 100;
        public static final int START_Y = Floor.Y - HEIGHT;
        public static final int JUMP_HEIGHT = 100;
    }
    public static class Obstacle {
        public static final int WIDTH = 20;
        public static final int DEFAULT_HEIGHT = 50;
        public static final int START_X = SCREEN_WIDTH;
        public static final int START_Y = Floor.Y - DEFAULT_HEIGHT;
    }
    public static class Speed {
        public static final double MAX_SPEED = 0;
        public static final double SPEED_RATE = 0.01;
    }
    public static class Floor {
        public static final int WIDTH = SCREEN_WIDTH;
        public static final int HEIGTH = 50;
        public static final int X = 0;
        public static final int Y = 450;
    }
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 500;
}