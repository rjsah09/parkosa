package com.parkosa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.parkosa.dao.AccountDAO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.AccountVO;

import dto.SignInDTO;

public class SignInUI {
	public static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        //-- 버튼, 입력 칸 생성 --//
        JLabel phoneNumberLabel = new JLabel("전화번호");
        phoneNumberLabel.setBounds(40, 20, 80, 25);
        panel.add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(40, 50, 295, 25);
        panel.add(phoneNumberField);
        
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(40, 110, 80, 25);
        panel.add(passwordLabel);
        
        JTextField passwordField = new JTextField();
        passwordField.setBounds(40, 140, 295, 25);
        panel.add(passwordField);
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(215, 350, 100, 25);
        panel.add(cancelButton);

        JButton signInButton = new JButton("로그인");
        signInButton.setBounds(60, 350, 100, 25);
        panel.add(signInButton);
        
        //-- 이벤트 발생 --//
        
        //취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        //회원가입 버튼 이벤트
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
                	 //String result = accountDAO.signIn(signInDTO));
                	 String result = "0000";
                	 if (result == null || result.equals("12")) {
                		 JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다.");
                	 } else {
                		 SignedAccount.getInstance().signIn(result);
                	 }
                	 
                }
            }
        });
        
    }
}
