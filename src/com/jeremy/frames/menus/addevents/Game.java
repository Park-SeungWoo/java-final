package com.jeremy.frames.menus.addevents;

import com.jeremy.core.constants.FontsNColors;
import com.jeremy.core.utils.objects.game.GameCanvas;
import com.jeremy.core.utils.objects.game.GameConstants;
import com.jeremy.frames.AbstractFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends AbstractFrame {
    private GameCanvas canvasComp;
    private boolean isPlaying;
    private int clearCnt;  // amount of successfully passed obstacles
    private Timer gameThread, jumpThread;

    /**
     * speed rate로 장애물 하나 지나칠 때마다 default speed(0.5) - cnt * rate(0.01)해서 속도 빨라지게
     * 일정 거리 e.g 300px 마다 장애물 한번씩 나오게
     *  이때 random true or false로 나오거나 나오지 않게 하기
     *  1/2면 안나올 확률이 너무 크니깐 ramdom숫자 0~1중 threshold를 0.35 정도로 둬 이상의 랜덤값나오면 장애물 추가
     * 장애물 높이도 default height(50) + 10 * (0~3) 으로 주기
     * 점프 높이는 100까지 찍고 다시 내려오도록
     *
     * 플레이어 점프와 장애물 움직임은 각각 다른 스레드로
     * 메인 스레드는 주기적으로 충돌감지
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
        // canvas에 interface 다 만들고, 여기서 입력 이벤트 받고, timer 돌리고, 인터페이스 호출하기
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
                    gameThread.stop();
                    jumpThread.stop();
                }
            }
        });
    }
    private void initGame() {
        this.isPlaying = false;
        this.clearCnt = 0;
    }
    private void initThreads() {
        gameThread = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasComp.moveObstacles();
                if(canvasComp.checkPassed()) {
                    clearCnt++;
                    System.out.println(clearCnt);
                }
                if(canvasComp.detectCollision()) {  // game over
                    // show label with score
                    initGame();
                    canvasComp.clearGame();
                    gameThread.stop();
                    jumpThread.stop();
                }
            }
        });
        jumpThread = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(canvasComp.jumpPlayer()) {  // if jump done
                    jumpThread.stop();
                }
            }
        });
    }
}
