package com.parkosa.gui;

import javax.swing.JPanel;

public abstract class UI extends JPanel{
	
	UI ui;
	
	public UI() {
		ui = this;
	}
	
	public abstract void placeComponents();
}
