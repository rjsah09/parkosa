package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dto.RegisteredCarDTO;

public class FeePolicyManageUI extends UI {
	
    public void placeComponents() {
        setLayout(null);
        
        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 10, 100, 25);
        add(cancelButton);
     
        CarDAO carDAO = new CarDAO();
        List<RegisteredCarDTO> registeredCars = carDAO.getRegisteredCars();
        
        DefaultTableModel model = new DefaultTableModel(new String[] {"차량 번호", "차종", "삭제"}, 0) {
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
        innerTable.setTableHeader(null);
        
        JScrollPane jsp = new JScrollPane(innerTable,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(10, 50, 365, 200);
        add(jsp);
        
        
        JLabel idLabel= new JLabel("ID");
        idLabel.setBounds(10,270,100,25);
        add(idLabel);
        
        JTextField idField = new JTextField();
        idField.setBounds(120,270,255,25);
        add(idField);
        
        JLabel increaseMinuteLabel= new JLabel("기본 증가시간");
        increaseMinuteLabel.setBounds(10,310,100,25);
        add(increaseMinuteLabel);
        
        JTextField increaseMinuteField = new JTextField();
        increaseMinuteField.setBounds(120,310,255,25);
        add(increaseMinuteField);
        
        JLabel increaseFeeLabel= new JLabel("단위시간당 이용료");
        increaseFeeLabel.setBounds(10,350,100,25);
        add(increaseFeeLabel);
        
        JTextField increaseFeeField = new JTextField();
        increaseFeeField.setBounds(120,350,255,25);
        add(increaseFeeField);
        
        JLabel maximumTimeLabel= new JLabel("최대 이용시간");
        maximumTimeLabel.setBounds(10,390,100,25);
        add(maximumTimeLabel);
        
        JTextField maximumTimeField = new JTextField();
        maximumTimeField.setBounds(120,390,255,25);
        add(maximumTimeField);
        
        JLabel carTypeIdLabel= new JLabel("차종ID");
        carTypeIdLabel.setBounds(10,430,100,25);
        add(carTypeIdLabel);
        
        JTextField carTypeIdField = new JTextField();
        carTypeIdField.setBounds(120,430,255,25);
        add(carTypeIdField);
        
        JLabel parkingLotIdLabel= new JLabel("주차장ID");
        parkingLotIdLabel.setBounds(10,470,100,25);
        add(parkingLotIdLabel);
        
        JTextField parkingLotIdfield= new JTextField();
        parkingLotIdfield.setBounds(120,470,255,25);
        add(parkingLotIdfield);
        
        JButton insertButton = new JButton("생성");
        insertButton.setBounds(10,510,100,30);
        add(insertButton);
        
        JButton modifyButton = new JButton("수정");
        modifyButton.setBounds(140,510,100,30);
        add(modifyButton);
        
        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(275,510,100,30);
        add(deleteButton);

   
        
        for (int i = 0; i < registeredCars.size(); i++) {
            String[] row = new String[3];
            row[0] = registeredCars.get(i).getCarCode();
            row[1] = registeredCars.get(i).getCarTypeName();
            row[2] = "삭제";
            model.addRow(row);
        }
        
        // Event listener for the cancel button
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
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
                GUIController.changeUI(ui, new AdminUI());
            }
        });

    }
}
