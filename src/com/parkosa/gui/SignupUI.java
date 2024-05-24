package com.parkosa.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.parkosa.dao.AccountDAO;
import com.parkosa.vo.AccountVO;

public class SignupUI {

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
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if (phoneNumberField == null || phoneNumberField.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
            	} else if (phoneNumberField.isEditable()){
                    JOptionPane.showMessageDialog(null, "전화번호 중복확인을 먼저 해주세요.");
                } else if (passwordField == null || passwordField.getText().equals("")) {
                	JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                } else if (checkPasswordField == null || checkPasswordField.getText().equals("")) {
                	JOptionPane.showMessageDialog(null, "비밀번호를 다시 한번 입력해주세요.");
                } else if (passwordField.getText().equals(checkPasswordField.getText())==false){
                    JOptionPane.showMessageDialog(null, "비밀번호가 서로 일치하지 않습니다.");
                } else if (nameField == null || nameField.getText().equals("")) {
                	JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
                } else if (emailField == null || emailField.getText().equals("")) {                	  
                	JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
                } else { 
            	
                	 AccountVO accountVO = new AccountVO(phoneNumberField.getText()
                			 , passwordField.getText()
                			 , nameField.getText()
                			 , emailField.getText()
                			 , LocalDate.now());
            	
                	 AccountDAO accountDAO = new AccountDAO();
                	 accountDAO.insertAccount(accountVO);
                }
            }
        });
        
        //중복 확인 버튼 이벤트
        phoneNumberValidateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AccountDAO accountDAO = new AccountDAO();
            	boolean duplicated = accountDAO.checkPhoneNumberDuplicated(phoneNumberField.getText());
            	
            	if (phoneNumberField == null || phoneNumberField.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
            		return;
            	}
            	
            	String result = !duplicated ? "사용 가능한 전화번호입니다." : "사용 불가능한 번호입니다.";
            	
            	JOptionPane.showMessageDialog(null, result);
            	if (!duplicated) {
            		phoneNumberField.setEditable(false);
            	}
            }
        });
    }
        
}
