package com.parkosa.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ParkKosaUI extends JFrame {
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JPanel indexPanel;
    private JLabel[] dots;
    private JButton signupButton, loginButton;
    private int currentIndex = 0;
    private final String[] images = {
        "C:\\Users\\KOSA\\eclipse-workspace\\y\\img\\image1.jpg", 
        "C:\\Users\\KOSA\\eclipse-workspace\\y\\img\\image2.jpg", 
        "C:\\Users\\KOSA\\eclipse-workspace\\y\\img\\image3.jpg", 
        "C:\\Users\\KOSA\\eclipse-workspace\\y\\img\\image4.jpg", 
        "C:\\Users\\KOSA\\eclipse-workspace\\y\\img\\image5.jpg"
    };
    private Timer timer;
    private int mouseX;

    public ParkKosaUI() {
        // 기본적인 JFrame 설정
        setTitle("ParkKosa");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // 전체 패널 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // 절대 레이아웃 사용
        add(mainPanel);
        
        // 상단 이미지 및 타이틀 패널
        JLabel titleLabel = new JLabel("ParKosa",JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(25.0f));
        mainPanel.add(titleLabel);
        
        // 이미지 슬라이드 공간 패널
        imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        mainPanel.add(imagePanel);
        
        // 공지사항 패널
        JPanel noticePanel = new JPanel();
        noticePanel.setBackground(Color.GRAY);
        JLabel noticeLabel = new JLabel("공지 사항 / 광고 / 요금 관련 안내", JLabel.CENTER);
        noticeLabel.setForeground(Color.WHITE);
        noticePanel.add(noticeLabel);
        mainPanel.add(noticePanel);
        
        // 하단 버튼 및 이미지 인덱스 패널
        indexPanel = new JPanel();
        indexPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        dots = new JLabel[images.length];
        for (int i = 0; i < images.length; i++) {
            dots[i] = new JLabel("●");
            dots[i].setFont(new Font("Arial", Font.PLAIN, 24));
            if (i == currentIndex) {
                dots[i].setForeground(Color.BLACK); // 현재 이미지 활성화 표시
            } else {
                dots[i].setForeground(Color.LIGHT_GRAY);
            }
            final int index = i;
            dots[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showImage(index);
                    resetTimer();
                }
            });
            indexPanel.add(dots[i]);
        }
        mainPanel.add(indexPanel);
        
        // 버튼 패널
        signupButton = new JButton("회원가입");
        loginButton = new JButton("로그인");
        mainPanel.add(signupButton);
        mainPanel.add(loginButton);
        
        // 초기 레이아웃 설정
        setInitialLayout(mainPanel, titleLabel, noticePanel);
        
        // 초기 이미지 표시
        showImage(currentIndex);
        
        // 이미지 전환 타이머 설정
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % images.length;
                showImage(currentIndex);
            }
        });
        timer.start();
        
        // 마우스 드래그 이벤트 추가
        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int newMouseX = e.getX();
                if (newMouseX > mouseX + 50) {
                    // 오른쪽으로 드래그 - 이전 이미지
                    showPreviousImage();
                } else if (newMouseX < mouseX - 50) {
                    // 왼쪽으로 드래그 - 다음 이미지
                    showNextImage();
                }
                resetTimer();
            }
        });

        // 창 크기 조절 이벤트 추가
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustLayout(mainPanel, titleLabel, noticePanel);
            }
        });
    }

    private void setInitialLayout(JPanel mainPanel, JLabel titleLabel, JPanel noticePanel) {
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int panelWidth = frameWidth - 120;
        int panelHeight = (int)(panelWidth*1.3);

        imagePanel.setBounds(60, 50, panelWidth, panelHeight);
        imageLabel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        titleLabel.setBounds(100, 10, 200, 50); // 위치 및 크기 설정
        
        int noticePanelY = panelHeight + 60;
        int noticePanelHeight = 50;
        noticePanel.setBounds(60, noticePanelY, panelWidth, noticePanelHeight);

        int indexPanelY = noticePanelY + noticePanelHeight;
        indexPanel.setBounds(60, indexPanelY, panelWidth, 40);

        int buttonY = indexPanelY + 40;
        int buttonWidth = 100;
        int buttonHeight = 30;
        loginButton.setBounds((frameWidth / 2) + 50, buttonY, buttonWidth, buttonHeight);
        signupButton.setBounds((frameWidth / 2) - 150, buttonY, buttonWidth, buttonHeight);
    }

    private void adjustLayout(JPanel mainPanel, JLabel titleLabel, JPanel noticePanel) {
        setInitialLayout(mainPanel, titleLabel, noticePanel);
        showImage(currentIndex);
    }

    private void showImage(int index) {
        currentIndex = index;
        try {
            BufferedImage img = ImageIO.read(new File(images[currentIndex]));
            Image scaledImage = img.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel.setText("Image not found");
        }
        for (int i = 0; i < dots.length; i++) {
            if (i == currentIndex) {
                dots[i].setForeground(Color.BLACK);
            } else {
                dots[i].setForeground(Color.LIGHT_GRAY);
            }
        }
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        showImage(currentIndex);
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        showImage(currentIndex);
    }

    private void resetTimer() {
        if (timer != null) {
            timer.restart();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParkKosaUI ui = new ParkKosaUI();
            ui.setVisible(true);
        });
    }
}