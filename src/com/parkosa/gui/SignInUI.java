package com.parkosa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel phoneNumberLabel = new JLabel("전화번호");
        phoneNumberLabel.setBounds(40, 20, 80, 25);
        add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(40, 50, 295, 25);
        add(phoneNumberField);
        
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(40, 110, 80, 25);
        add(passwordLabel);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(40, 140, 295, 25);
        passwordField.setEchoChar('*');
        add(passwordField);
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(60, 350, 100, 25);
        add(cancelButton);

        JButton signInButton = new JButton("로그인");
        signInButton.setBounds(215, 350, 100, 25);
        add(signInButton);
        
        JButton adminButton = new JButton("관리자");
        adminButton.setBounds(310, 530, 70, 25);
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
                		 System.out.println(SignedAccount.getPhoneNumber());
                		 GUIController.changeUI(ui, new MainScreenUI());
                	 }
                	 
                }
            }
        });
        
        //관리자 버튼 이벤트
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIController.changeUI(ui, new AdminUI());
            }
        });
        
    }
}
