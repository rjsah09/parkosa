package com.parkosa.gui;

import javax.swing.JFrame;

public class GUIController {
	public void drawScreen() {
        JFrame frame = new JFrame("Parkosa");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        UI parkKosaUI = new ParkKosaUI();
        frame.add(parkKosaUI);
        parkKosaUI.placeComponents();
        
        frame.setVisible(true);
    }
}
