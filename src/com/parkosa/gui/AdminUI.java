package com.parkosa.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.sign.SignedAccount;

public class AdminUI extends UI{
    public void placeComponents() {
        setLayout(null);

        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();

        JLabel nameLabel = new JLabel("주차장 관리창", JLabel.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setBounds(100, 60, 200, 50);
        nameLabel.setFont(new Font("NanumGothic", Font.BOLD, 30));
        add(nameLabel);

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 10, 100, 25);
        add(cancelButton);

        JButton parkinglotManage = new JButton("주차장 관리");
        customizeButton(parkinglotManage);
        parkinglotManage.setBounds(40, 170, 295, 50);
        add(parkinglotManage);

        JButton parkingspaceManage = new JButton("주차공간 관리");
        customizeButton(parkingspaceManage);
        parkingspaceManage.setBounds(40, 310, 295, 50);
        add(parkingspaceManage);

        JButton policyManage = new JButton("정책 관리");
        customizeButton(policyManage);
        policyManage.setBounds(40, 450, 295, 50);
        add(policyManage);

        //-- 이벤트 발생 --//
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignedAccount.signOut();
                GUIController.changeUI(ui, new ParkKosaUI());
            }
        });
        
        parkinglotManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new ParkingLotManageUI());
            }
        });

        parkingspaceManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new ParkingSpaceManageUI());
            }
        });

        policyManage.addActionListener(new ActionListener() {
            public void
            actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new FeePolicyManageUI(1));
            }
        });

    }

    // 버튼 스타일링을 위한 메서드
    private void customizeButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("NanumGothic", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}
