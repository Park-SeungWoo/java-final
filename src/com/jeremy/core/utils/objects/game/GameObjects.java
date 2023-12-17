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
    public final boolean hasCollision(GameObjects obj) {
        // obj의 tl, tr, bl, br 좌표중 하나가 내 boundary안에 있거나 걸쳐 있으면 충돌로 인식
        // bl, br은 지금 필요 없으나 scalability 고려해 만들어둠
        if (obj.topLeft.x >= this.topLeft.x && obj.topLeft.x <= this.bottomRight.x &&  // tr
        obj.topLeft.y >= this.topLeft.y && obj.topLeft.y <= this.bottomRight.y) return true;

        if (obj.bottomRight.x >= this.topLeft.x && obj.bottomRight.x <= this.bottomRight.x &&  // tl
        obj.topLeft.y >= this.topLeft.y && obj.topLeft.y <= this.bottomRight.y) return true;

        if (obj.bottomRight.x >= this.topLeft.x && obj.bottomRight.x <= this.bottomRight.x &&  // br
        obj.bottomRight.y >= this.topLeft.y && obj.bottomRight.y <= this.bottomRight.y) return true;

        if (obj.topLeft.x >= this.topLeft.x && obj.topLeft.x <= this.bottomRight.x &&  // bl
        obj.bottomRight.y >= this.topLeft.y && obj.bottomRight.y <= this.bottomRight.y) return true;

        return false;
    }
}
