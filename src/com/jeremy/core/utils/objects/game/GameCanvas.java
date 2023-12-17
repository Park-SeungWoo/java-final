package com.jeremy.core.utils.objects.game;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameCanvas extends Canvas {
    private Player player;
    private Queue<Obstacle> obstacles;
    private int additionalDist, lastAdded, speed;
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
        this.additionalDist = (int) (Math.random() * GameConstants.Obstacle.MAX_ADDITIONAL_DIST);
        this.lastAdded = 0;
        this.speed = GameConstants.Speed.DEFAULT_SPEED;
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
        this.obstacles.forEach(obstacle -> {
            obstacle.curCoord.x -= this.speed;
            obstacle.setBounds();
        });
        this.repaint();
    }
    public boolean detectCollision() {
        Obstacle frontObs = this.obstacles.peek();
        if (frontObs == null) return false;

        return this.player.hasCollision(frontObs);
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
            this.player.jumpingH -= this.speed;
            this.player.curCoord.y += this.speed;
            if (this.player.jumpingH == 0) {
                this.player.clear();
                return true;
            }
        } else {  // rising
            this.player.jumpingH += this.speed;
            this.player.curCoord.y -= this.speed;
            if (this.player.jumpingH == GameConstants.Player.JUMP_HEIGHT) this.player.beenTop = true;
        }
        this.repaint();
        this.player.setBounds();
        return false;
    }
    public void clearGame() {
        this.initGame();
        repaint();
    }
    public boolean isJumping() {
        return this.player.isJumping;
    }
    public void startJump() {
        this.player.isJumping = true;
    }
    public void addObstacle() {
        if (this.lastAdded > GameConstants.Obstacle.MIN_DIST + this.additionalDist) {
            obstacles.offer(new Obstacle((int) (Math.random() * GameConstants.Obstacle.MAX_ADDITIONAL_HEIGHT)));
            this.additionalDist = (int) (Math.random() * GameConstants.Obstacle.MAX_ADDITIONAL_DIST);
            this.lastAdded = 0;
        }
        this.lastAdded += this.speed;
    }
}
