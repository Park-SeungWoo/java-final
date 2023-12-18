package com.jeremy.frames;

import com.jeremy.core.utils.Coords;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class MainFrame extends AbstractFrame {
    // components
    private MenuBar menuBar;
    private Menu fileMenu, eventMenu, addEventMenu, helpMenu;
    private MenuItem fmNew, fmExit, hmHelp, hmDev, emCoffee, emBlood, aemAA, aemBB, aemGame;
    private Image img;
    private TextField idComp, pwComp;
    private Label idLComp, pwLComp;
    private Button loginComp;

    public MainFrame(){
        this("Java Project 박승우");
    }
    public MainFrame(String title) {
        super(title, 1280, 745);
    }

    ////////////////////////// Methods
    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setBackground(FontsNColors.Colors.purple);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("assets/icon.png").getImage());
    }
    @Override
    protected void setComponentsConfig() {
        // make components
        this.menuBar = new MenuBar();

        this.fileMenu = new Menu("File");
        this.fmNew = new MenuItem("New Background Image");
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

        int tfWidth = 200;
        int tfHeight = 40;
        int lWidth = 200;
        int lHeight = 30;
        int btnWidth = 200;
        int btnHeight = 50;
        Coords tfCoords = this.getCenterCoordinate(tfWidth, tfHeight, this.frameW, this.frameH);
        Coords lCoords = this.getCenterCoordinate(lWidth, lHeight, this.frameW, this.frameH);
        Coords btnCoords = this.getCenterCoordinate(btnWidth, btnHeight, this.frameW, this.frameH);

        this.idLComp = new Label("ID");
        this.idLComp.setFont(FontsNColors.Fonts.titleFont);
        this.idLComp.setBounds(lCoords.x, lCoords.y - ((lHeight / 2) + tfHeight), lWidth, lHeight);

        this.idComp = new TextField();
        this.idComp.setFont(FontsNColors.Fonts.textFont);
        this.idComp.setBounds(tfCoords.x, tfCoords.y - (tfHeight / 2), tfWidth, tfHeight);

        this.pwLComp = new Label("PASSWORD");
        this.pwLComp.setFont(FontsNColors.Fonts.titleFont);
        this.pwLComp.setBounds(lCoords.x, lCoords.y + (lHeight / 2), lWidth, lHeight);

        this.pwComp = new TextField();
        this.pwComp.setFont(FontsNColors.Fonts.textFont);
        this.pwComp.setBounds(tfCoords.x, tfCoords.y + ((tfHeight / 2) + lHeight), tfWidth, tfHeight);
        this.pwComp.setEchoChar('*');

        this.loginComp = new Button("Login");
        this.loginComp.setFont(FontsNColors.Fonts.buttonFont);
        this.loginComp.setBounds(btnCoords.x, btnCoords.y + ((btnHeight / 2) + lHeight + tfHeight + 30), btnWidth, btnHeight);

        this.fileMenu.add(this.fmNew); this.fileMenu.addSeparator(); this.fileMenu.add(this.fmExit);
        this.eventMenu.add(emCoffee); this.eventMenu.add(emBlood);
        this.addEventMenu.add(aemAA); this.addEventMenu.add(aemBB); this.addEventMenu.add(aemGame);
        this.helpMenu.add(this.hmHelp); this.helpMenu.add(this.hmDev);

        this.menuBar.add(this.helpMenu);
    }
    @Override
    protected void addComponentListener() {
        this.fmNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNewBackground();
            }
        });
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
        this.loginComp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateUser();
            }
        });
        this.idComp.addKeyListener(new EnterToLogin());
        this.pwComp.addKeyListener(new EnterToLogin());
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(this.img, 0, 25, this);
    }

    private void validateUser() {
        String id = this.idComp.getText();
        String pw = this.pwComp.getText();
        if (id.equals("admin") && pw.equals("admin")){
            // remove label, textfield, button
            this.remove(this.idComp);
            this.remove(this.pwComp);
            this.remove(this.idLComp);
            this.remove(this.pwLComp);
            this.remove(this.loginComp);
            // add menus to menubar
            this.menuBar.remove(this.helpMenu);  // for the order
            this.menuBar.add(this.fileMenu); this.menuBar.add(this.eventMenu); this.menuBar.add(this.addEventMenu); this.menuBar.add(this.helpMenu);
            // draw background image
            this.img = tk.getImage("assets/developer_img.jpg");
            this.repaint();
            this.validate();
            return;
        }
        // alert
        idComp.setText("");
        pwComp.setText("");
        idComp.requestFocus();
        JOptionPane.showMessageDialog(this, "Login Failed\nTry again");
    }

    private void setNewBackground() {
        FileDialog fDialog = new FileDialog(this, "Open", FileDialog.LOAD);
        fDialog.setVisible(true);
        String path = fDialog.getDirectory() + fDialog.getFile();
        if (!List.of("jpg", "jpeg", "png").contains(path.split("\\.")[1])) return;

        this.img = tk.getImage(path);

        // Use MediaTracker to wait until the image is fully loaded
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.img, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException ignore) {}

        this.setSize(this.img.getWidth(this), this.img.getHeight(this));
        this.alignFrameToCenter();
        this.repaint();
    }

    private class EnterToLogin extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                validateUser();
            }
        }
    }

    /**
     * Main method to start
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

}
