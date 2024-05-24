package com.parkosa.gui;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignupUI {

    public static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel phoneNumberLabel = new JLabel("전화번호");
        phoneNumberLabel.setBounds(40, 20, 80, 25);
        panel.add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(40, 50, 295, 25);
        panel.add(phoneNumberField);
        
        JButton checkIdButton = new JButton("중복확인");
        checkIdButton.setBounds(40, 80, 295, 25);
        panel.add(checkIdButton);
        
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(40, 110, 80, 25);
        panel.add(passwordLabel);
        
        JTextField passwordField = new JTextField();
        passwordField.setBounds(40, 140, 295, 25);
        panel.add(passwordField);
        
        JLabel checkpasswordLabel = new JLabel("비밀번호 확인");
        checkpasswordLabel.setBounds(40, 170, 80, 25);
        panel.add(checkpasswordLabel);
        
        JTextField checkpasswordField = new JTextField();
        checkpasswordField.setBounds(40, 200, 295, 25);
        panel.add(checkpasswordField);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(40, 230, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(40, 260, 295, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("이메일");
        emailLabel.setBounds(40, 290, 80, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(40, 320, 295, 25);
        panel.add(emailField);

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(215, 350, 100, 25);
        panel.add(cancelButton);

        JButton reserveButton = new JButton("회원가입");
        reserveButton.setBounds(60, 350, 100, 25);
        panel.add(reserveButton);

    }
}
