package com.jeremy.frames.menus.addevents;

import com.jeremy.core.constants.FontsNColors;
import com.jeremy.core.utils.Coords;
import com.jeremy.frames.AbstractFrame;

import java.awt.*;
import java.awt.event.*;

public class MouseLocation extends AbstractFrame {
    private Label labelComp;
    public MouseLocation(){
        this("Mouse Location");
    }
    public MouseLocation(String title) {
        super(title, 700, 500);
    }

    @Override
    protected void addExclusiveListener() {
        super.addExclusiveListener();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                labelComp.setText(String.format("Mouse Pointer Location: (%d, %d)", e.getX(), e.getY()));
                if (e.getX() == e.getY()) labelComp.setText(labelComp.getText() + " X == Y");
            }
        });
    }

    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setBackground(FontsNColors.Colors.green);
        this.setLayout(null);
    }

    @Override
    protected void setComponentsConfig() {
        int labelW = 600;
        int labelH = 50;
        Coords labelCoord = getCenterCoordinate(labelW, labelH, frameW, frameH);

        this.labelComp = new Label("Mouse Pointer Location");
        this.labelComp.setFont(FontsNColors.Fonts.titleFont);
        this.labelComp.setBackground(FontsNColors.Colors.blue);
        this.labelComp.setBounds(labelCoord.x, 50, labelW, labelH);
        this.labelComp.setAlignment(Label.CENTER);
    }

    @Override
    protected void addComponentListener() {
    }

}
