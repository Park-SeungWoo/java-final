package com.jeremy.frames.menus.events;

import com.jeremy.frames.AbstractFrame;
import com.jeremy.core.constants.FontsNColors;

import java.awt.*;
import java.awt.event.*;

public class BloodTest extends AbstractFrame {
	
	private Label l1Comp, l2Comp;
	private Panel p1Comp;
	private CheckboxGroup cbg;
	private Checkbox cb1, cb2, cb3, cb4;
	public BloodTest(){this("Select Blood type");}
	public BloodTest(String title) {
		super(title, 400, 300);
	}

	@Override
	protected void setFrameConfig() {
		super.setFrameConfig();
		setLayout(null);
	}

	@Override
	protected void setComponentsConfig() {
		this.l1Comp = new Label("About Your BloodType", Label.CENTER);
		this.l1Comp.setBounds(50, 50, 300, 30);
		this.l1Comp.setFont(FontsNColors.Fonts.titleFont);
		this.l1Comp.setForeground(Color.blue);

		this.l2Comp = new Label("Please select one", Label.CENTER);
		this.l2Comp.setBounds(50, 100, 300, 30);
		this.l2Comp.setFont(FontsNColors.Fonts.titleFont);
		this.l2Comp.setBackground(Color.black);
		this.l2Comp.setForeground(Color.YELLOW);

		this.p1Comp = new Panel();
		this.p1Comp.setBounds(50, 150, 300, 100);
		this.p1Comp.setBackground(Color.orange);

		this.cbg = new CheckboxGroup();
		this.cb1 = new Checkbox("A", cbg, true);
		this.cb2 = new Checkbox("B", cbg, false);
		this.cb3 = new Checkbox("AB", cbg, false);
		this.cb4 = new Checkbox("O", cbg, false);

		cb1.setFont(FontsNColors.Fonts.textFont);
		cb2.setFont(FontsNColors.Fonts.textFont);
		cb3.setFont(FontsNColors.Fonts.textFont);
		cb4.setFont(FontsNColors.Fonts.textFont);

		p1Comp.add(cb1); p1Comp.add(cb2); p1Comp.add(cb3); p1Comp.add(cb4);
	}

	@Override
	protected void addComponentListener() {
		cb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				l2Comp.setText("You selected " + cb1.getLabel());
			}
		});

		cb2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				l2Comp.setText("You selected " + cb2.getLabel());
			}
		});

		cb3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				l2Comp.setText("You selected " + cb3.getLabel());
			}
		});

		cb4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				l2Comp.setText("You selected " + cb4.getLabel());
			}
		});
	}

}
