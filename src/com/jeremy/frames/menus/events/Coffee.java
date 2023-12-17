package com.jeremy.frames.menus.events;

import com.jeremy.frames.AbstractFrame;
import com.jeremy.core.constants.FontsNColors;

import java.awt.*;
import java.awt.event.*;

public class Coffee extends AbstractFrame {
	private Label l1Comp;
	private Panel p1Comp;
	private Button b1, b2, b3;

	public Coffee() {
		this("Select Coffee");
	}
	public Coffee(String title) {
		super(title, 400, 400);
	}

	@Override
	protected void setFrameConfig() {
		super.setFrameConfig();
		setBackground(new Color(210,250,234));
		setLayout(null);
	}

	@Override
	protected void setComponentsConfig() {
		this.p1Comp = new Panel();
		this.p1Comp.setLayout(new GridLayout(1,3));
		this.p1Comp.setBounds(25, 80, 350, 160);

		this.b1 = new Button("Americano");
		this.b2 = new Button("Caffe Latte");
		this.b3 = new Button("Cappuccino");

		this.b1.setFont(FontsNColors.Fonts.buttonFont);
		this.b2.setFont(FontsNColors.Fonts.buttonFont);
		this.b3.setFont(FontsNColors.Fonts.buttonFont);

		this.l1Comp = new Label("Select Coffee", Label.CENTER);
		this.l1Comp.setBackground(Color.pink);
		this.l1Comp.setBounds(25, 285, 350, 50);
		this.l1Comp.setFont(FontsNColors.Fonts.titleFont);

		this.p1Comp.add(b1); this.p1Comp.add(b2); this.p1Comp.add(b3);
	}

	@Override
	protected void addComponentListener() {
		this.b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l1Comp.setText("Selected " + b1.getLabel());
			}
		});

		this.b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l1Comp.setText("Selected " + b2.getLabel());
			}
		});

		this.b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l1Comp.setText("Selected " + b3.getLabel());
			}
		});
	}
}
