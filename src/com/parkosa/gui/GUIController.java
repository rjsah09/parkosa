package com.parkosa.gui;

import javax.swing.JFrame;

public class GUIController {
	
	static JFrame frame;
	
	public static void drawScreen() {
        frame = new JFrame("Parkosa");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        UI parkKosaUI = new ParkKosaUI();
        frame.add(parkKosaUI);
        parkKosaUI.placeComponents();
        
        frame.setVisible(true);
    }
	
	public static void changeUI(UI oldUI, UI newUI) {
		frame.remove(oldUI);
		frame.add(newUI);
		newUI.placeComponents();
		
		frame.setVisible(true);
	}
}
