package com.parkosa.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.parkosa.dao.AccountDAO;
import com.parkosa.vo.AccountVO;

public class SignUpUI extends UI {

	public void placeComponents() {
        setLayout(null);

        //-- 버튼, 입력 칸 생성 --//
        JLabel phoneNumberLabel = new JLabel("전화번호");
        phoneNumberLabel.setBounds(255, 20, 80, 25);
        add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(255, 50, 290, 25);
        add(phoneNumberField);
        
        JButton phoneNumberValidateButton = new JButton("중복확인");
        phoneNumberValidateButton.setBounds(255, 80, 290, 25);
        add(phoneNumberValidateButton);
        
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(255, 130, 80, 25);
        add(passwordLabel);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(255, 160, 290, 25);
        passwordField.setEchoChar('*');
        add(passwordField);
        
        JLabel checkPasswordLabel = new JLabel("비밀번호 확인");
        checkPasswordLabel.setBounds(255, 210, 80, 25);
        add(checkPasswordLabel);
        
        JPasswordField checkPasswordField = new JPasswordField();
        checkPasswordField.setBounds(255, 240, 290, 25);
        passwordField.setEchoChar('*');
        add(checkPasswordField);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(255, 290, 80, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(255, 320, 290, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("이메일");
        emailLabel.setBounds(255, 370, 80, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(255, 400, 290, 25);
        add(emailField);


        JButton reserveButton = new JButton("회원가입");
        reserveButton.setBounds(350, 450, 100, 25);
        add(reserveButton);

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 530, 100, 25);
        add(cancelButton);
        
        //-- 이벤트 발생 --//
        
        //취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIController.changeUI(ui, new ParkKosaUI());
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
                	JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
                }else if (!emailField.getText().contains("@")){
                	JOptionPane.showMessageDialog(null, "정확한 이메일 주소를 입력해주세요.");                	
                }else { 
            	
                	 AccountVO accountVO = new AccountVO(phoneNumberField.getText()
                			 , passwordField.getText()
                			 , nameField.getText()
                			 , emailField.getText()
                			 , LocalDate.now());
            	
                	 AccountDAO accountDAO = new AccountDAO();
                	 accountDAO.insertAccount(accountVO);
                	 
                	 GUIController.changeUI(ui, new ParkKosaUI());
                }
            }
        });
        
        //중복 확인 버튼 이벤트
        phoneNumberValidateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AccountDAO accountDAO = new AccountDAO();
            	if (phoneNumberField.isEditable()==false) {
            		phoneNumberField.setEditable(true);
            		phoneNumberField.setText("");
            		phoneNumberValidateButton.setText("중복확인");
            	} else if (phoneNumberField.isEditable()&!(phoneNumberField.getText().equals(""))) {	
            		boolean duplicated = accountDAO.checkPhoneNumberDuplicated(phoneNumberField.getText());
	            	
	            	if (phoneNumberField == null || phoneNumberField.getText().equals("")) {
	            		JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.");
	            		return;
	            	}
	            	String result = !duplicated ? "사용 가능한 전화번호입니다." : "사용 불가능한 번호입니다.";

	            	JOptionPane.showMessageDialog(null, result);
	            	if (!duplicated) {
	            		phoneNumberField.setEditable(false);
	            		phoneNumberValidateButton.setText("다시 입력하려면 클릭해주세요.");

	            	}
            	
            	}
            }
        });

    }
        
}
