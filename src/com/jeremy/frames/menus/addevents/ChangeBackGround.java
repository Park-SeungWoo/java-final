package com.jeremy.frames.menus.addevents;

import com.jeremy.frames.AbstractFrame;
import com.jeremy.core.constants.FontsNColors;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

public class ChangeBackGround extends AbstractFrame {
    // components
    private CheckboxGroup colorSelectionGroup;
    private Checkbox selectionBlueComp, selectionGreenComp, selectionPurpleComp, selectionRandComp;

    public ChangeBackGround(){
        this("Change Background");
    }
    public ChangeBackGround(String title) {
        super(title, 400, 400);
    }

    ////////////////////////// Methods
    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setBackground(FontsNColors.Colors.orange);
        this.setLayout(new FlowLayout());
    }
    @Override
    protected void setComponentsConfig() {
        // make components
        this.colorSelectionGroup = new CheckboxGroup();
        this.selectionBlueComp = new Checkbox("Blue", colorSelectionGroup, false);
        this.selectionGreenComp = new Checkbox("Green", colorSelectionGroup, false);
        this.selectionPurpleComp = new Checkbox("Purple", colorSelectionGroup, false);
        this.selectionRandComp = new Checkbox("Random", colorSelectionGroup, false);

    }
    @Override
    protected void addComponentListener() {
        this.selectionGreenComp.addItemListener(new ColorCheckBoxItemListener());
        this.selectionBlueComp.addItemListener(new ColorCheckBoxItemListener());
        this.selectionPurpleComp.addItemListener(new ColorCheckBoxItemListener());
        this.selectionRandComp.addItemListener(new ColorCheckBoxItemListener());
    }

    /////////////////////// EventHandler classes
    private class ColorCheckBoxItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Checkbox selected = colorSelectionGroup.getSelectedCheckbox();
            switch (selected.getLabel()){
                case "Blue":
                    setBackground(FontsNColors.Colors.blue);
                    break;
                case "Green":
                    setBackground(FontsNColors.Colors.green);
                    break;
                case "Purple":
                    setBackground(FontsNColors.Colors.purple);
                    break;
                case "Random":
                    setBackground(FontsNColors.Colors.getRandomColor());
                    break;
            }
        }
    }

}
