package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.ParkingSpaceDAO;
import com.parkosa.dto.InsertParkingSpaceDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.RegisteredParkingSpaceDTO;

public class ParkingSpaceManageUI extends UI {

	int parkingLotId;

    public ParkingSpaceManageUI(int parkingLotId) {
    	this.parkingLotId = parkingLotId;
	}
	
    public void placeComponents() {
        setLayout(null);

        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();

        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 10, 100, 25);
        add(cancelButton);

        ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO();
        List <RegisteredParkingSpaceDTO> registeredParkingSpaces = parkingSpaceDAO.listParkingSpace();
        
        DefaultTableModel model = new DefaultTableModel(new String[] {"차종","단위시간", "단위시간당요금", "최대이용시간", "참고사항"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable innerTable = new JTable(model);
        innerTable.addMouseListener(new TableMouseAdaptor());
        innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
        innerTable.setRowHeight(20);
        innerTable.setShowVerticalLines(false);
        innerTable.setShowHorizontalLines(false);

        JScrollPane jsp = new JScrollPane(innerTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(10, 50, 365, 330);
        add(jsp);

        JLabel parkingLotIdLabel = new JLabel("주차장ID");
        parkingLotIdLabel.setBounds(10, 390, 60, 25);
        add(parkingLotIdLabel);

        JTextField parkingLotIdfield = new JTextField();
        parkingLotIdfield.setBounds(100, 390, 275, 25);
        add(parkingLotIdfield);

        JLabel descriptionLabel = new JLabel("주차구역");
        descriptionLabel.setBounds(10, 430, 60, 25);
        add(descriptionLabel);

        JTextField descriptionfield = new JTextField();
        descriptionfield.setBounds(100, 430, 275, 25);
        add(descriptionfield);

        JLabel feePolicyIdLabel = new JLabel("요금정책");
        feePolicyIdLabel.setBounds(10, 470, 60, 25);
        add(feePolicyIdLabel);

        JTextField feePolicyIdField = new JTextField();
        feePolicyIdField.setText("10");
        feePolicyIdField.setBounds(100, 470, 275, 25);
        add(feePolicyIdField);

        JButton insertButton = new JButton("생성");
        insertButton.setBounds(10, 510, 100, 30);
        add(insertButton);

        JButton modifyButton = new JButton("수정");
        modifyButton.setBounds(140, 510, 100, 30);
        add(modifyButton);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(275, 510, 100, 30);
        add(deleteButton);


        for (int i = 0; i < registeredParkingSpaces.size(); i++) {
            String[] row = new String[5];
            row[4] = registeredParkingSpaces.get(i).getCarTypeName();
            row[1] = Integer.toString(registeredParkingSpaces.get(i).getIncereaseMinute());
            row[2] = Integer.toString(registeredParkingSpaces.get(i).getIncreaseFee());
            row[3] = Integer.toString(registeredParkingSpaces.get(i).getMaximumTime());
            row[0] = registeredParkingSpaces.get(i).getDescription();
            
            model.addRow(row);
        }

        // Event listener for the cancel button
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InsertParkingSpaceDTO insertParkingSpaceDTO = new InsertParkingSpaceDTO(Integer.valueOf(parkingLotIdfield.getText()), descriptionfield.getText(), Integer.valueOf(feePolicyIdField.getText()));
                ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO();
                parkingSpaceDAO.insertFeePolicy(insertParkingSpaceDTO);
            }

        });

        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new ParkingLotManageUI());
            }
        });

    }
}
