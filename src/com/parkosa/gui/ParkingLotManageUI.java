package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dao.ParkingLotDAO;
import com.parkosa.dto.InsertParkingLotDTO;
import com.parkosa.dto.RegisteredCarDTO;

public class ParkingLotManageUI extends UI {
    
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
        
        JLabel nameLabel= new JLabel("이름");
        nameLabel.setBounds(10,310,60,25);
        add(nameLabel);
        
        JTextField nameField = new JTextField();
        nameField.setBounds(100,310,275,25);
        add(nameField);
        
        JLabel telNumberLabel= new JLabel("번호");
        telNumberLabel.setBounds(10,350,60,25);
        add(telNumberLabel);
        
        JTextField telNumberField = new JTextField();
        telNumberField.setBounds(100,350,275,25);
        add(telNumberField);
        
        JLabel imageFileLabel= new JLabel("이미지 파일");
        imageFileLabel.setBounds(10,390,80,25);
        add(imageFileLabel);
        
        JButton imageFileField = new JButton("파일 열기");
        imageFileField.setBounds(100,390,275,25);
        add(imageFileField);
        
        JLabel addressLabel= new JLabel("주소");
        addressLabel.setBounds(10,430,60,25);
        add(addressLabel);
        
        JTextField addressField = new JTextField();
        addressField.setBounds(100,430,275,25);
        add(addressField);
        
        JLabel locationIdLabel= new JLabel("주소id");
        locationIdLabel.setBounds(10,470,60,25);
        add(locationIdLabel);
        
        JTextField locationIdField= new JTextField();
        locationIdField.setBounds(100,470,275,25);
        add(locationIdField);
        
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
                
                InsertParkingLotDTO insertParkingLotDTO = new InsertParkingLotDTO(nameField.getText(),
                											telNumberField.getText(),
                											Integer.valueOf(locationIdField.getText()),
                											addressField.getText(),
                											"asdasd");
                
                ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
                parkingLotDAO.insertParkLot(insertParkingLotDTO);
            }
        });
        
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG or PNG","jpg");
        		
        imageFileField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(getParent());
                
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                	JLabel imagePathLabel = new JLabel();
                	imagePathLabel.setText(fileChooser.getSelectedFile().getPath());
                	imagePathLabel.setBounds(100,390,275,25);
                	add(imagePathLabel);
                }
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