package com.parkosa.image;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//import com.parkosa.gui.파일2; 

public class ImagePopUpFrame extends JFrame {

    JScrollPane scrollPane;

    ImageIcon icon;

    public void loadFile(String url) {

        icon = new ImageIcon(url);
        
        JPanel panel = new JPanel() {

            public void paintComponent(Graphics g) {

                g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

                setOpaque(false);

                super.paintComponent(g);

            }

        };

        scrollPane = new JScrollPane(panel);

        this.getContentPane().setLayout(null);

        setContentPane(scrollPane);

        this.setBounds(0, 0, this.getWidth(), this.getHeight());

    }

}
