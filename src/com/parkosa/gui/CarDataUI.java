package com.parkosa.gui;

import com.parkosa.dao.AccountDAO;
import com.parkosa.vo.AccountVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CarDataUI {

    public static void main(String[] args) {
        CarDataUI ui = new CarDataUI();
        GUIController guiController = new GUIController();
        guiController.drawScreen();
    }



    public static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        //-- 버튼, 입력 칸 생성 --//
        JLabel carNumberLabel = new JLabel("자동차 번호를 입력하세요");
        carNumberLabel.setBounds(40, 20, 150, 25);
        panel.add(carNumberLabel);

        JTextField carNumberField = new JTextField();
        carNumberField.setBounds(40, 50, 295, 25);
        panel.add(carNumberField);

        JButton phoneNumberValidateButton = new JButton("중복확인");
        phoneNumberValidateButton.setBounds(40, 80, 295, 25);
        panel.add(phoneNumberValidateButton);

        JLabel carSelectBox = new JLabel("자동차 종류를 선택하세요");
        carSelectBox.setBounds(40, 110, 150, 25);
        panel.add(carSelectBox);

        String[] items = {"경차", "세단", "전기차"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(40, 140, 295, 25);
        panel.add(comboBox);

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(215, 350, 100, 25);
        panel.add(cancelButton);

        JButton reserveButton = new JButton("차량입력완료");
        reserveButton.setBounds(60, 350, 100, 25);
        panel.add(reserveButton);
        

    }
        
}
