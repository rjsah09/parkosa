package com.parkosa.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.sign.SignedAccount;

public class MainScreenUI extends UI {

    public void placeComponents() {
        setLayout(null);

        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();

        JLabel nameLabel = new JLabel(name + "님", JLabel.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setBounds(100, 10, 200, 50);
        nameLabel.setFont(new Font("NanumGothic", Font.BOLD, 18));
        add(nameLabel);

        JButton parkingReserve = new JButton("주차 예약");
        customizeButton(parkingReserve);
        parkingReserve.setBounds(40, 100, 295, 50);
        add(parkingReserve);

        JButton checkReserve = new JButton("예약 확인");
        customizeButton(checkReserve);
        checkReserve.setBounds(40, 170, 295, 50);
        add(checkReserve);

        JButton	checkPast = new JButton("지난 예약 내역");
        customizeButton(checkPast);
        checkPast.setBounds(40, 240, 295, 50);
        add(checkPast);

        JButton carModify = new JButton("차량 등록");
        customizeButton(carModify);
        carModify.setBounds(40, 310, 295, 50);
        add(carModify);

        JButton carList = new JButton("등록 차량 관리");
        customizeButton(carList);
        carList.setBounds(40, 380, 295, 50);
        add(carList);

        JButton cancelButton = new JButton("로그아웃");
        customizeButton(cancelButton);
        cancelButton.setBounds(40, 450, 295, 50);
        add(cancelButton);

        //-- 이벤트 발생 --//

        // 취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignedAccount.signOut();
                GUIController.changeUI(ui, new ParkKosaUI());
            }
        });

        carModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new RegisterCarUI());
            }
        });

        carList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new RegisteredCarsUI());
            }
        });
        
        parkingReserve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new RegisterReservationUI());
			}
		});
        
        checkReserve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new ReservationHistoryUI());
			}
		});
        
        checkPast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new PastReservationHistoryUI());
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
