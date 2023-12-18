package com.jeremy.core.utils.objects.game;

import com.jeremy.core.constants.FontsNColors;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GameCanvas extends Canvas {
    private Player player;
    private Queue<Obstacle> obstacles;
    private int additionalDist, lastAdded, speed;
    private String runTimeStr, scoreStr, title;
    public GameCanvas() {
        this.initGame();
        this.title = GameConstants.Strings.READY;
        this.scoreStr = "";
        this.runTimeStr = "";
        this.setFocusable(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(GameConstants.Floor.X, GameConstants.Floor.Y, GameConstants.Floor.WIDTH, GameConstants.Floor.HEIGHT);
        drawPlayer(g);
        drawObstacles(g);
        drawTexts(g);
    }
    private void initGame() {
        this.player = new Player();
        this.obstacles = new LinkedList<>();
        obstacles.offer(new Obstacle(0));
        this.additionalDist = (int) (Math.random() * GameConstants.Obstacle.MAX_ADDITIONAL_DIST);
        this.lastAdded = 0;
        this.speed = GameConstants.Speed.DEFAULT_MOVE_SPEED;
    }

    private void drawPlayer(Graphics g) {
        g.setColor(this.player.color);
        g.fillRect(this.player.curCoord.x, this.player.curCoord.y, this.player.WIDTH, this.player.HEIGHT);
    }
    private void drawObstacles(Graphics g) {
        for (Obstacle obstacle : this.obstacles){
            g.setColor(obstacle.color);
            g.fillRect(obstacle.curCoord.x, obstacle.curCoord.y, obstacle.WIDTH, obstacle.HEIGHT);
        }
    }
    private void drawTexts(Graphics g) {
        FontMetrics titleMetrics = g.getFontMetrics(FontsNColors.Fonts.titleFont);
        FontMetrics infoMetrics = g.getFontMetrics(FontsNColors.Fonts.textFont);
        int width, height;

        width = titleMetrics.stringWidth(this.title);
        height = titleMetrics.getHeight();
        g.setColor(FontsNColors.Colors.red);
        g.setFont(FontsNColors.Fonts.titleFont);
        g.drawString(this.title, (this.getWidth() - width) / 2, (this.getHeight() - height) / 2 + titleMetrics.getAscent());

        width = infoMetrics.stringWidth(this.runTimeStr);
        height = infoMetrics.getHeight();
        g.setColor(FontsNColors.Colors.green);
        g.setFont(FontsNColors.Fonts.textFont);
        g.drawString(this.runTimeStr, (this.getWidth() - width) / 2, 25);

        width = infoMetrics.stringWidth(this.scoreStr);
        g.drawString(this.scoreStr, (this.getWidth() - width) / 2 , 25 + height);
    }
    public void gameStartStr(int runTime, int score) {
        this.title = "";
        this.runTimeStr = GameConstants.Strings.RUNNING_TIME + (runTime / 1000) + "s";
        this.scoreStr = GameConstants.Strings.SCORE + score + " points";
        this.repaint();
    }
    public void gameOverStr() {
        this.title = GameConstants.Strings.GAME_OVER;
        this.repaint();
    }
    public void stopGameStr() {
        this.title = GameConstants.Strings.PAUSE;
        this.repaint();
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
            if (this.player.jumpingH <= 0) {
                this.player.clear();
                return true;
            }
        } else {  // rising
            this.player.jumpingH += this.speed;
            this.player.curCoord.y -= this.speed;
            if (this.player.jumpingH >= GameConstants.Player.JUMP_HEIGHT) this.player.beenTop = true;
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
    public void increaseLevel() {
        if(this.speed < GameConstants.Speed.MAX_MOVE_SPEED) this.speed++;
    }
}
