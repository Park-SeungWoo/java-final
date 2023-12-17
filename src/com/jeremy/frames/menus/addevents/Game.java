package com.jeremy.frames.menus.addevents;

import com.jeremy.core.constants.FontsNColors;
import com.jeremy.core.utils.objects.game.GameCanvas;
import com.jeremy.core.utils.objects.game.GameConstants;
import com.jeremy.frames.AbstractFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends AbstractFrame {
    private GameCanvas canvasComp;
    private boolean isPlaying;
    private int clearCnt;  // amount of successfully passed obstacles
    private int runningTime;
    private Timer gameThread, jumpThread;

    /**
     * 시작 전, 플레이 중, 게임 오버 표시
     */

    public Game() {
        this("Game");
    }
    public Game(String title) {
        super(title, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
        this.initGame();
        this.initThreads();
    }

    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setBackground(FontsNColors.Colors.blue);
        this.setLayout(null);
    }

    @Override
    protected void setComponentsConfig() {
        this.canvasComp = new GameCanvas();
        this.canvasComp.setBounds(0, 25, frameW, frameH);
    }

    @Override
    protected void addComponentListener() {
    }

    @Override
    protected void addExclusiveListener() {
        super.addExclusiveListener();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                final int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER && !isPlaying) {  // enter -> start/restart game
                    isPlaying = true;
                    gameThread.start();
                    if (canvasComp.isJumping()) jumpThread.start();
                } else if(key == KeyEvent.VK_SPACE && isPlaying) {  // space bar -> player jump
                    if (canvasComp.isJumping()) return;
                    canvasComp.startJump();
                    jumpThread.start();
                } else if (key == KeyEvent.VK_ESCAPE & isPlaying) {  // esc -> stop game
                    isPlaying = false;
                    canvasComp.stopGameStr();
                    gameThread.stop();
                    jumpThread.stop();
                }
            }
        });
    }
    private void initGame() {
        this.isPlaying = false;
        this.clearCnt = 0;
        this.runningTime = 0;
    }
    private void initThreads() {
        gameThread = new Timer(GameConstants.Speed.OBST_SPEED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runningTime += GameConstants.Speed.OBST_SPEED;
                canvasComp.gameStartStr(runningTime, clearCnt);
                canvasComp.moveObstacles();
                if(canvasComp.checkPassed()) {
                    clearCnt++;
                    if (clearCnt % GameConstants.LEVEL_INCREASE_RATE == 0) canvasComp.increaseLevel();
                }
                if(canvasComp.detectCollision()) {  // game over
                    // show label with score
                    canvasComp.gameOverStr();
                    initGame();
                    canvasComp.clearGame();
                    gameThread.stop();
                    jumpThread.stop();
                }
                canvasComp.addObstacle();
            }
        });
        jumpThread = new Timer(GameConstants.Speed.JUMP_SPEED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(canvasComp.jumpPlayer()) {  // if jump done
                    jumpThread.stop();
                }
            }
        });
    }
}
