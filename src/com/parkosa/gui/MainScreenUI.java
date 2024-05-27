package com.parkosa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.parkosa.dao.AccountDAO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.AccountVO;

public class MainScreenUI extends UI {
	public void placeComponents() {
        setLayout(null);

        JButton parkingReserve = new JButton("주차 예약");
        parkingReserve.setBounds(40, 140, 295, 25);
        add(parkingReserve);
        
        JButton checkReserve = new JButton("예약 확인");
        checkReserve.setBounds(40, 200, 295, 25);
        add(checkReserve);

        JButton accountModify = new JButton("회원정보 수정");
        accountModify.setBounds(40, 260, 295, 25);
        add(accountModify);

        JButton carModify = new JButton("차량 등록/수정");
        carModify.setBounds(40, 320, 295, 25);
        add(carModify);

        JButton cancelButton = new JButton("로그아웃");
        cancelButton.setBounds(40, 380, 295, 25);
        add(cancelButton);

        
        //-- 이벤트 발생 --//
        
        //취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SignedAccount.signOut();
            	GUIController.changeUI(ui, new ParkKosaUI());
            }
        });
        
	}
}

