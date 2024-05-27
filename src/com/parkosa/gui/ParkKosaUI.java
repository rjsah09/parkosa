package com.parkosa.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ParkKosaUI extends UI {
    
	int currentIndex = 0;
    
    public void placeComponents() {
        // JPanel에 기본적인 레이아웃 설정
        setLayout(null);
        setBackground(Color.WHITE);

        // 상단 이미지 및 타이틀
        JLabel titleLabel = new JLabel("ParKosa", JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(100, 10, 200, 50);
        add(titleLabel);
        
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(50, 70, 300, 400);
        add(imageLabel);

        // 공지사항 패널
        JLabel jLabel = new JLabel();
        jLabel.setBackground(Color.GRAY);
        JLabel noticeLabel = new JLabel("공지 사항 / 광고 / 요금 관련 안내", JLabel.CENTER);
        noticeLabel.setForeground(Color.WHITE);
        jLabel.add(noticeLabel);
        add(jLabel);

        // 버튼 패널
        JButton signupButton = new JButton("회원가입");
        JButton loginButton = new JButton("로그인");
        add(signupButton);
        add(loginButton);

        // 버튼 위치 설정
        Dimension buttonSize = new Dimension(100, 30);
        signupButton.setPreferredSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);
        signupButton.setBounds(100, 500, 100, 30);
        loginButton.setBounds(200, 500, 100, 30);

        // 초기 이미지 표시
        showImage(0, imageLabel);

        // 이미지 전환 타이머 설정
        Timer timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % 5; // 5개 이미지로 가정
                showImage(currentIndex, imageLabel);
            }
        });
        timer.start();

      
        // 마우스 드래그 이벤트 추가
        imageLabel.addMouseListener(new MouseAdapter() {
            int mouseX;

            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int newMouseX = e.getX();
                if (newMouseX > mouseX + 50) {
                    // 오른쪽으로 드래그 - 이전 이미지
                    int newIndex = (currentIndex - 1 + 5) % 5; // 5개 이미지로 가정
                    showImage(newIndex, imageLabel);
                } else if (newMouseX < mouseX - 50) {
                    // 왼쪽으로 드래그 - 다음 이미지
                    int newIndex = (currentIndex + 1) % 5; // 5개 이미지로 가정
                    showImage(newIndex, imageLabel);
                    
                }
                timer.restart();
            }
        });

        // 버튼 이벤트 처리*/
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼 클릭 시 동작 정의
            	GUIController.changeUI(ui, new SignUpUI());
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 클릭 시 동작 정의
            	GUIController.changeUI(ui, new SignInUI());
            }
        });
    }

    private static void showImage(int index, JLabel imageLabel) {
        String[] images = {
            ".\\resources\\images\\image1.jpg", 
            ".\\resources\\images\\image2.jpg", 
            ".\\resources\\images\\image3.PNG", 
            ".\\resources\\images\\image4.jpg", 
            ".\\resources\\images\\image5.jpg"
        };
        
        try {
            BufferedImage img = ImageIO.read(new File(images[index]));
            ImageIcon imageIcon = new ImageIcon(img);
            Image scaledImage = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel.setText("Image not found");
        }
    }

}