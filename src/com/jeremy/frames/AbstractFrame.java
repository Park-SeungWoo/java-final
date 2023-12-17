package com.jeremy.frames;

import com.jeremy.core.utils.Coords;
import com.jeremy.frames.menus.helps.Dev;
import com.jeremy.frames.menus.helps.Help;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class AbstractFrame extends Frame {
    protected final Toolkit tk = Toolkit.getDefaultToolkit();
    protected final int frameW;
    protected final int frameH;
    private PopupMenu popupMenu;
    private MenuItem closeM, devInfoM, programInfoM;

    protected AbstractFrame(){
        this(800, 800);
    }
    protected AbstractFrame(int frameW, int frameH){
        this("", frameW, frameH);
    }

    protected AbstractFrame(String title){
        this(title, 800, 800);
    }
    protected AbstractFrame(String title, int frameW, int frameH) {
        super(title);
        this.frameW = frameW;
        this.frameH = frameH;

        this.addExclusiveListener();
        this.setFrameConfig();
        this.setComponentsConfig();
        this.addComponentListener();

        this.addPopupMenu();
        this.addComponentsToFrame();
        try {
            this.addMenuBar();
        } catch (IllegalAccessException exception){
            throw new RuntimeException("Cannot access to menuBar field");
        } catch (NoSuchFieldException ignored) {}

        this.setVisible(true);
    }

    ////////////////////////// Methods
    protected void addExclusiveListener() {
        // exit or dispose when window closing button clicked
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AbstractFrame.this.closeWindow();
            }
        });

        // show popup menu at the position where mouse clicked
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3)
                    popupMenu.show(AbstractFrame.this, e.getX(), e.getY());
                else
                    super.mouseClicked(e);
            }
        });
    }
    protected void setFrameConfig(){
        this.alignFrameToCenter();
    };
    protected abstract void setComponentsConfig();
    protected abstract void addComponentListener();
    /**
     * Get center coordinates of window dimension to align Frames or Components to the center
     */
    protected final Coords getCenterOfDimension(int compW, int compH){
        Dimension dim = this.tk.getScreenSize();
        return getCenterCoordinate(compW, compH, dim.getWidth(), dim.getHeight());
    }
    /**
     * Get center coordinates of any box to align any component to the center
     */
    protected final Coords getCenterCoordinate(double componentW, double componentH, double boxW, double boxH){
        return new Coords((int) ((boxW - componentW) / 2), (int) ((boxH - componentH) / 2));
    }
    /**
     * Align frame to the center
     */
    private void alignFrameToCenter() {
        Coords frameCoords = this.getCenterOfDimension(this.frameW, this.frameH);
        this.setBounds(frameCoords.x, frameCoords.y, frameW, frameH);
    }

    /**
     * Add popup menu to all frames
     */
    private void addPopupMenu() {
        this.popupMenu = new PopupMenu();

        this.closeM = new MenuItem("close");
        this.programInfoM = new MenuItem("Program Info");
        this.devInfoM = new MenuItem("Developer Info");

        this.popupMenu.add(this.closeM);
        this.popupMenu.addSeparator();
        this.popupMenu.add(this.programInfoM);
        this.popupMenu.add(this.devInfoM);

        this.setPopupListener();

        this.add(this.popupMenu);
    }
    /**
     * Set event listeners of popup menus
     */
    private void setPopupListener() {
        this.closeM.addActionListener(new PopupMenuEventListener());
        this.programInfoM.addActionListener(new PopupMenuEventListener());
        this.devInfoM.addActionListener(new PopupMenuEventListener());
    }
    /**
     * Add Components in fields.
     * Must set components name ends with 'Comp' and also as private
     */
    private void addComponentsToFrame() {
        Field[] fields = this.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.getName().endsWith("Comp"))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        Component comp = (Component) field.get(this);
                        this.add(comp);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    /**
     * Set menuBar automatically.
     * Must set MenuBar field name as 'menuBar' and also as private
     */
    private void addMenuBar() throws NoSuchFieldException, IllegalAccessException {
        Field menuField = this.getClass().getDeclaredField("menuBar");
        menuField.setAccessible(true);
        MenuBar menuBar = (MenuBar) menuField.get(this);
        this.setMenuBar(menuBar);
    }
    /**
     * close method
     */
    private void closeWindow() {
        if(this.getName().contains("MainFrame"))
            System.exit(0);
        else
            dispose();
    }

    /**
     * Popup menu event listener class
     */
    private class PopupMenuEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String menuName = ((MenuItem) e.getSource()).getLabel();
            switch (menuName) {
                case "close":
                    AbstractFrame.this.closeWindow();
                    break;
                case "Program Info":
                    new Help();
                    break;
                case "Developer Info":
                    new Dev();
                    break;
            }
        }
    }
}
