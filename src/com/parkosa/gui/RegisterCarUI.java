package com.parkosa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.parkosa.dao.CarDAO;
import com.parkosa.dto.SignInDTO;
import com.parkosa.vo.CarVO;
import com.parkosa.sign.SignedAccount;
public class RegisterCarUI extends UI{

    @Override
    public void placeComponents() {
        setLayout(null);
        //-- 버튼, 입력 칸 생성 --//
        SignInDTO signInDTO = new SignInDTO();
        JLabel userlLabel = new JLabel("Id: ");
        userlLabel.setBounds(40, 0, 50, 25);
        add(userlLabel);

        JLabel userPhoneNumberLabel = new JLabel(signInDTO.getPhoneNumber());
        userPhoneNumberLabel.setBounds(40, 0, 100, 25);
        add(userPhoneNumberLabel);

        //-- 버튼, 입력 칸 생성 --//
        JLabel carNumberLabel = new JLabel("자동차 번호를 입력하세요");
        carNumberLabel.setBounds(40, 20, 150, 25);
        add(carNumberLabel);

        JTextField carNumberField = new JTextField();
        carNumberField.setBounds(40, 50, 295, 25);
        add(carNumberField);

        JButton carNumberValidateButton = new JButton("중복확인");
        carNumberValidateButton.setBounds(40, 80, 295, 25);
        add(carNumberValidateButton);

        JLabel carSelectBox = new JLabel("자동차 종류를 선택하세요");
        carSelectBox.setBounds(40, 110, 150, 25);
        add(carSelectBox);

        String[] items = {"경차", "승용차", "전기차"};
        JComboBox<String> comboSelectCarBox = new JComboBox<>(items);
        comboSelectCarBox.setBounds(40, 140, 295, 25);
        add(comboSelectCarBox);

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(215, 350, 100, 25);
        add(cancelButton);

        JButton reserveButton = new JButton("차량입력완료");
        reserveButton.setBounds(60, 350, 100, 25);
        add(reserveButton);


        //취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                GUIController.changeUI(ui, new ParkKosaUI());
            }
        });

        //중복 확인 버튼 이벤트


        carNumberValidateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CarDAO carDAO = new CarDAO();
                if (carNumberField.isEditable() == false) {
                    carNumberField.setEditable(true);
                    carNumberField.setText("");
                } else if (carNumberField.isEditable()&!(carNumberField.getText().equals(""))) {
                    boolean duplicated = carDAO.checkCarCodeDuplicated(carNumberField.getText());

                    if (carNumberField == null || carNumberField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "차량 번호를 입력해주세요.");
                        return;
                    }
                    String result = !duplicated ? "사용 가능한 차량번호입니다." : "이미 등록된 차량번호입니다.";

                    JOptionPane.showMessageDialog(null, result);
                    if (!duplicated) {
                        carNumberField.setEditable(false);
                    }
                }
            }
        });


      //회원가입 버튼 이벤트
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (carNumberField == null || carNumberField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "차 번호를 입력해주세요..");
                }
                else if(userPhoneNumberLabel.getText()==null){
                    JOptionPane.showMessageDialog(null, "로그인 에러 발생");
                }
                else {
                    int carType = 0;
                    String selectedItem = (String) comboSelectCarBox.getSelectedItem();
                    if(selectedItem.equals("경차")){
                        carType = 1;
                    }else if(selectedItem.equals("승용차")){
                        carType = 2;
                    }else if(selectedItem.equals("전기차")){
                        carType = 3;
                    }


                    CarVO CarVO = new CarVO(
                            carNumberField.getText(),
                            userPhoneNumberLabel.getText(),
                            carType
                            );


                   CarDAO CarDAO = new CarDAO();
                    CarDAO.insertCar(CarVO);
                }
            }
        });


    }
}