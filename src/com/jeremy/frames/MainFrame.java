package com.jeremy.frames;

import com.jeremy.frames.menus.addevents.ChangeBackGround;
import com.jeremy.frames.menus.addevents.Game;
import com.jeremy.frames.menus.addevents.MouseLocation;
import com.jeremy.frames.menus.events.BloodTest;
import com.jeremy.frames.menus.events.Coffee;
import com.jeremy.frames.menus.helps.Dev;
import com.jeremy.frames.menus.helps.Help;
import com.jeremy.core.constants.FontsNColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends AbstractFrame {
    // components
    private MenuBar menuBar;
    private Menu fileMenu, eventMenu, addEventMenu, helpMenu;
    private MenuItem fmJoin, fmExit, hmHelp, hmDev, emCoffee, emBlood, aemAA, aemBB, aemGame;
    private Image img;

    public MainFrame(){
        this("Java Project 박승우");
    }
    public MainFrame(String title) {
        super(title, 820, 603);
    }

    ////////////////////////// Methods
    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setBackground(FontsNColors.Colors.orange);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("assets/icon.png").getImage());

        this.img = tk.getImage("assets/awt.png");
    }
    @Override
    protected void setComponentsConfig() {
        // make components
        this.menuBar = new MenuBar();

        this.fileMenu = new Menu("File");
        this.fmJoin = new MenuItem("Join", new MenuShortcut('J', true));
        this.fmExit = new MenuItem("Exit", new MenuShortcut('E', false));

        this.eventMenu = new Menu("Event");
        this.emCoffee = new MenuItem("Select Coffee");
        this.emBlood = new MenuItem("Select BloodType");

        this.addEventMenu = new Menu("Add Event");
        this.aemAA = new MenuItem("Change Background");
        this.aemBB = new MenuItem("Mouse Location");
        this.aemGame = new MenuItem("Game");

        this.helpMenu = new Menu("Help");
        this.hmHelp = new MenuItem("Program Introduction", new MenuShortcut('P', true));
        this.hmDev = new MenuItem("Developer Introduction", new MenuShortcut('D', true));

        this.fileMenu.add(this.fmJoin); this.fileMenu.addSeparator(); this.fileMenu.add(this.fmExit);
        this.helpMenu.add(this.hmHelp); this.helpMenu.add(this.hmDev);
        this.eventMenu.add(emCoffee); this.eventMenu.add(emBlood);
        this.addEventMenu.add(aemAA); this.addEventMenu.add(aemBB); this.addEventMenu.add(aemGame);
        this.menuBar.add(this.fileMenu); this.menuBar.add(this.eventMenu); this.menuBar.add(this.addEventMenu); this.menuBar.add(this.helpMenu);
    }
    @Override
    protected void addComponentListener() {
        this.fmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.hmDev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dev();
            }
        });
        this.hmHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Help();
            }
        });
        this.emCoffee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Coffee();
            }
        });
        this.emBlood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BloodTest();
            }
        });
        this.aemAA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangeBackGround();
            }
        });
        this.aemBB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new MouseLocation(); }
        });
        this.aemGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
            }
        });
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(this.img, 0, 25, this);
    }

    /**
     * Main method to start
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

}
