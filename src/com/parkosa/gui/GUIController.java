package com.parkosa.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIController {
	public void drawScreen() {
        JFrame frame = new JFrame("Parkosa");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*JPanel signUpPanel = new JPanel();
        frame.add(signUpPanel);
        SignupUI.placeComponents(signUpPanel);*/
        
//        JPanel signInPanel = new JPanel();
//        frame.add(signInPanel);
//        SignInUI.placeComponents(signInPanel);
        JPanel CarDataPannel = new JPanel();
        frame.add(CarDataPannel);
        CarDataUI.placeComponents(CarDataPannel);
        frame.setVisible(true);
    }
}
