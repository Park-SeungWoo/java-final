package com.jeremy.core.utils.objects.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GameCanvas extends Canvas {
    private Player player;
    private Queue<Obstacle> obstacles;
    public GameCanvas() {
        this.initGame();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(GameConstants.Floor.X, GameConstants.Floor.Y, GameConstants.Floor.WIDTH, GameConstants.Floor.HEIGTH);
        drawPlayer(g);
        drawObstacles(g);
    }
    private void initGame() {
        this.player = new Player();
        this.obstacles = new LinkedList<>();
        obstacles.offer(new Obstacle(0));
    }

    private void drawPlayer(Graphics g) {
        g.drawRect(this.player.curCoord.x, this.player.curCoord.y, this.player.WIDTH, this.player.HEIGHT);
    }
    private void drawObstacles(Graphics g) {
        for (Obstacle obstacle : this.obstacles){
            g.drawRect(obstacle.curCoord.x, obstacle.curCoord.y, obstacle.WIDTH, obstacle.HEIGHT);
        }
    }
    public void moveObstacles() {
        this.obstacles.stream().forEach(obstacle -> {
            obstacle.curCoord.x -= 1;
        });
        this.repaint();
    }
    public boolean detectCollision() {
        Obstacle frontObs = this.obstacles.peek();
        if (frontObs == null) return false;

        // obstacle의
        return false;
//
//
//        return this.player.curCoord.x + this.player.WIDTH >= frontObs.curCoord.x &&
//                this.player.curCoord.y + this.player.HEIGHT >= frontObs.curCoord.y;
    }
    public boolean checkPassed() {
        Obstacle frontObs = this.obstacles.peek();
        if(frontObs == null) return false;

        if (frontObs.curCoord.x <= 0) {
            this.obstacles.poll();
            return true;
        }
        return false;
    }
    public boolean jumpPlayer() {
        if (this.player.beenTop) {  // falling
            this.player.jumpingH--;
            this.player.curCoord.y++;
            if (this.player.jumpingH == 0) {
                this.player.clear();
                return true;
            }
        } else {  // rising
            this.player.jumpingH++;
            this.player.curCoord.y--;
            if (this.player.jumpingH == GameConstants.Player.JUMP_HEIGHT) this.player.beenTop = true;
        }
        this.repaint();
        return false;
    }
    public void clearGame() {
        this.player.clear();
        this.obstacles.clear();
        this.obstacles.offer(new Obstacle(0));
        repaint();
    }
    public boolean isJumping() {
        return this.player.isJumping;
    }
    public void startJump() {
        this.player.isJumping = true;
    }
}
