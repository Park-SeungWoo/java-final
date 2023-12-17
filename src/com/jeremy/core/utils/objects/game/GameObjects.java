package com.jeremy.core.utils.objects.game;

import com.jeremy.core.utils.Coords;

public abstract class GameObjects {
    public final int WIDTH, HEIGHT;
    public Coords curCoord;
    public Coords topLeft;
    public Coords bottomRight;
    public GameObjects(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }
    public final void setBounds() {
        this.topLeft = new Coords(this.curCoord.x, this.curCoord.y);
        this.bottomRight = new Coords(this.curCoord.x + this.WIDTH, this.curCoord.y + this.HEIGHT);
    }
}
