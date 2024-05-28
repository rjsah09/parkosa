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

public class parkingspaceManageUI extends UI {
	
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
        jsp.setBounds(10, 50, 365, 280);
        add(jsp);
        
        
        JLabel idLabel= new JLabel("ID");
        idLabel.setBounds(10,350,60,25);
        add(idLabel);
        
        JTextField idField = new JTextField();
        idField.setBounds(100,350,275,25);
        add(idField);
        
        JLabel parkingLotIdLabel= new JLabel("주차장ID");
        parkingLotIdLabel.setBounds(10,390,60,25);
        add(parkingLotIdLabel);
        
        JTextField parkingLotIdfield= new JTextField();
        parkingLotIdfield.setBounds(100,390,275,25);
        add(parkingLotIdfield);
        
        JLabel descriptionLabel= new JLabel("주차장ID");
        descriptionLabel.setBounds(10,430,60,25);
        add(descriptionLabel);
        
        JTextField descriptionfield= new JTextField();
        descriptionfield.setBounds(100,430,275,25);
        add(descriptionfield);
        
        JLabel feePolicyIdLabel= new JLabel("주차장ID");
        feePolicyIdLabel.setBounds(10,470,60,25);
        add(feePolicyIdLabel);
        
        JTextField feePolicyIdfield= new JTextField();
        feePolicyIdfield.setBounds(100,470,275,25);
        add(feePolicyIdfield);
        
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
