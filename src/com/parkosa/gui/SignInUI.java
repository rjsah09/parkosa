package com.parkosa.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dto.SignInDTO;
import com.parkosa.sign.SignedAccount;

public class SignInUI extends UI {
	public void placeComponents() {
        setLayout(null);
      //-- 버튼, 입력 칸 생성 --//
        JLabel imageLabel = new JLabel(new ImageIcon(".\\resources\\images\\logo.png"));
        imageLabel.setBounds(260, 10, 300, 307);
        add(imageLabel);
        
        JLabel commentLabel = new JLabel("ㅡ ㅡ 고객 정보를 입력해주세요. ㅡ ㅡ");
        commentLabel.setBounds(300,300,300,25);
        commentLabel.setForeground(Color.blue);
        add(commentLabel);
        
        JLabel phoneNumberLabel = new JLabel("전화번호");
        phoneNumberLabel.setBounds(260, 350, 80, 25);
        add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(320, 350, 200, 25);
        add(phoneNumberField);
        
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(260, 400, 80, 25);
        add(passwordLabel);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(320, 400, 200, 25);
        passwordField.setEchoChar('*');
        add(passwordField);
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 530, 100, 25);
        add(cancelButton);

        JButton signInButton = new JButton("로그인");
        signInButton.setBounds(350, 450, 100, 25);
        add(signInButton);
        
        JButton adminButton = new JButton("관리자");
        adminButton.setBounds(710, 530, 70, 25);
        add(adminButton);
        
        //-- 이벤트 발생 --//
        //취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIController.changeUI(ui, new ParkKosaUI());
            }
        });
        
        //로그인 버튼 이벤트
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if (phoneNumberField == null || phoneNumberField.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
            	} else if (passwordField == null || passwordField.getText().equals("")) {
                	JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                } else { 
            	
                	 SignInDTO signInDTO = new SignInDTO();
                	 signInDTO.setPhoneNumber(phoneNumberField.getText());
                	 signInDTO.setPassword(passwordField.getText());
            	
                	 AccountDAO accountDAO = new AccountDAO();
                	 boolean duplicated = accountDAO.checkPhoneNumberDuplicated(phoneNumberField.getText());
                	 String result = accountDAO.signIn(signInDTO);
                	 if (!duplicated){
                		 JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다.");
                	 } else if (result == null || result.equals("12")) {
                		 JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                	 } else {
                		 SignedAccount.signIn(result);
                		 GUIController.changeUI(ui, new MainScreenUI());
                	 }
                	 
                }
            }
        });
        
        //관리자 버튼 이벤트
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIController.changeUI(ui, new ParkingLotManageUI());
            }
        });
        
    }
}
