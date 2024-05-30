package com.parkosa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.parkosa.dao.CarDAO;
import com.parkosa.dao.CarTypeDAO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.CarVO;

public class RegisterCarUI extends UI{

    @Override
    public void placeComponents() {
        setLayout(null);

        //-- 버튼, 입력 칸 생성 --//
        JLabel carNumberLabel = new JLabel("자동차 번호를 입력하세요");
        carNumberLabel.setBounds(250, 260-100, 150, 25);
        add(carNumberLabel);

        JTextField carNumberField = new JTextField();
        carNumberField.setBounds(250, 290-100, 300, 25);
        add(carNumberField);

        JButton carNumberValidateButton = new JButton("중복확인");
        carNumberValidateButton.setBounds(250, 320-100, 300, 25);
        add(carNumberValidateButton);

        JLabel carSelectBox = new JLabel("자동차 종류를 선택하세요");
        carSelectBox.setBounds(250, 370-100, 150, 25);
        add(carSelectBox);

        CarTypeDAO carTypeDAO = new CarTypeDAO();
        String[] items = new String[carTypeDAO.carTypeList().size()];
        for (int i =0; i< carTypeDAO.carTypeList().size(); i++) {
            items[i] = carTypeDAO.carTypeList().get(i).getName();
        }

        JComboBox<String> comboSelectCarBox = new JComboBox<>(items);
        comboSelectCarBox.setBounds(250, 400-100, 300, 25);
        add(comboSelectCarBox);


        JButton reserveButton = new JButton("차량등록");
        reserveButton.setBounds(350, 450-100, 100, 25);
        add(reserveButton);

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 530, 100, 25);
        add(cancelButton);

        //취소 버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new MainScreenUI());
            }
        });

        //중복 확인 버튼 이벤트
        carNumberValidateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CarDAO carDAO = new CarDAO();
                if (carNumberField.isEditable() == false) {
                    carNumberField.setEditable(true);
                    carNumberField.setText("");
                    carNumberValidateButton.setText("중복확인");
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
                        carNumberValidateButton.setText("다시 입력하려면 클릭해주세요.");
                    }
                }
            }
        });

      //차량 등록 버튼 이벤트
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (carNumberField == null || carNumberField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "차 번호를 입력해주세요..");
                } else {
                    String selectedItem = (String) comboSelectCarBox.getSelectedItem();
                    int carId = 0;
                    CarTypeDAO carTypeDAO = new CarTypeDAO();
                    carId = carTypeDAO.selectCarNo(selectedItem);

                    CarVO CarVO = new CarVO(
                            carNumberField.getText(),
                            SignedAccount.getPhoneNumber(),
                            carId
                            );


                   CarDAO CarDAO = new CarDAO();
                   CarDAO.insertCar(CarVO);
                   
                   GUIController.changeUI(ui, new MainScreenUI());
                }
            }
        });

    }
}
