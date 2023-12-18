package com.jeremy.core.utils.objects.game;

import com.jeremy.core.constants.GameConstants;
import com.jeremy.core.utils.Coords;

public class Player extends GameObjects{
    public boolean isJumping;
    public boolean beenTop;  // in jumping state
    public int jumpingH;
    public Player() {
        super(GameConstants.Player.WIDTH, GameConstants.Player.HEIGHT);
        this.clear();
    }
    public void clear() {
        this.isJumping = false;
        this.beenTop = false;
        this.jumpingH = 0;
        this.curCoord = new Coords(GameConstants.Player.X, GameConstants.Player.START_Y);
        this.setBounds();
    }
}
