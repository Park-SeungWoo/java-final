package com.jeremy.core.constants;

public class GameConstants {
    public static class Player {
        public static final int WIDTH = 50;
        public static final int HEIGHT = 50;
        public static final int X = 100;
        public static final int START_Y = Floor.Y - HEIGHT;
        public static final int JUMP_HEIGHT = 150;
    }
    public static class Obstacle {
        public static final int WIDTH = 20;
        public static final int DEFAULT_HEIGHT = 50;
        public static final int START_X = SCREEN_WIDTH;
        public static final int START_Y = Floor.Y - DEFAULT_HEIGHT;
        public static final int MIN_DIST = 350;
        public static final int MAX_ADDITIONAL_DIST = 200;  // default distance from last obstacle + random distance
        public static final int MAX_ADDITIONAL_HEIGHT = 20;  // default height + random height
    }
    public static class Speed {
        public static final int INCREASE_CONDITION = 5;  // every these successful attempts, increase level
        public static final double MAX_MOVE_SPEED = 5;  // pixel movement per frame (level)
        public static final int DEFAULT_MOVE_SPEED = 1;
        public static final int JUMP_SPEED_MSPF = 5;  // millisecond per frame
        public static final int OBST_SPEED_MSPF = 5;
    }
    public static class Floor {
        public static final int WIDTH = SCREEN_WIDTH;
        public static final int HEIGHT = 50;
        public static final int X = 0;
        public static final int Y = 450;
    }
    public static class Strings {
        public static final String RUNNING_TIME = "Running Time: ";
        public static final String SCORE = "Score: ";
        public static final String READY = "Press Enter To Start";
        public static final String GAME_OVER = "Game Over. Press Enter To Restart";
        public static final String PAUSE = "Press Enter To Resume";
    }
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 500;
}
