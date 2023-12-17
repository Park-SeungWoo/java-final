package com.jeremy.core.utils.objects.game;

import com.jeremy.core.utils.Coords;

public class Obstacle extends GameObjects{
    public Obstacle(int additionalH) {
        super(GameConstants.Obstacle.WIDTH, GameConstants.Obstacle.DEFAULT_HEIGHT + additionalH);
        this.curCoord = new Coords(GameConstants.Obstacle.START_X, GameConstants.Obstacle.START_Y - additionalH);
        this.setBounds();
    }
}
