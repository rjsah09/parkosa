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
import javax.swing.JPanel;
import javax.swing.Timer;

public class ParkKosaUI extends UI {
	
	UI ui = this;

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

        // 이미지 슬라이드 공간 패널
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        imagePanel.setBackground(Color.WHITE);
        add(imagePanel);

        // 공지사항 패널
        JPanel noticePanel = new JPanel();
        noticePanel.setBackground(Color.GRAY);
        JLabel noticeLabel = new JLabel("공지 사항 / 광고 / 요금 관련 안내", JLabel.CENTER);
        noticeLabel.setForeground(Color.WHITE);
        noticePanel.add(noticeLabel);
        add(noticePanel);

        // 하단 이미지 인덱스 패널
        JPanel indexPanel = new JPanel();
        indexPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        indexPanel.setBackground(Color.WHITE);

        JLabel[] dots = new JLabel[5]; // 5개 이미지로 가정
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new JLabel("●");
            dots[i].setFont(new Font("Arial", Font.PLAIN, 24));
            dots[i].setForeground(i == 0 ? Color.BLACK : Color.LIGHT_GRAY); // 첫 번째 이미지는 활성화 상태로 설정
            final int index = i;
            dots[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 이미지 인덱스를 클릭하면 해당 이미지 표시
                    showImage(index, imageLabel);
                    resetTimer();
                }
            });
            indexPanel.add(dots[i]);
        }
        add(indexPanel);

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
        Timer timer = new Timer(3000, new ActionListener() {
            int currentIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % 5; // 5개 이미지로 가정
                showImage(currentIndex, imageLabel);
            }
        });
        timer.start();

        /*
        // 마우스 드래그 이벤트 추가
        imagePanel.addMouseListener(new MouseAdapter() {
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
                resetTimer();
            }
        });*/

        // 버튼 이벤트 처리
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼 클릭 시 동작 정의
            	GUIController.changeUI(ui, new SignInUI());
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 클릭 시 동작 정의
            }
        });
    }

    private static void showImage(int index, JLabel imageLabel) {
        String[] images = {
            "resources/iamges/main2.png", 
            "resources/iamges/main2.png", 
            "resources/iamges/main3.png", 
            "resources/iamges/main4.png", 
            "resources/iamges/main5.png"
        };
        try {
            BufferedImage img = ImageIO.read(new File(images[index]));
            Image scaledImage = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel.setText("Image not found");
        }
    }

    private static void resetTimer() {
        // 타이머 리셋 메서드
    }
}