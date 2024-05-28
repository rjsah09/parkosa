package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dao.LocationDAO;
import com.parkosa.dao.ParkingLotDAO;
import com.parkosa.dto.InsertParkingLotDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.getLocationDTO;
import com.parkosa.image.ImageSaver;

public class ParkingLotManageUI extends UI {
    
    public void placeComponents() {
    	
    	int provinceId;
    	int cityId;
    	int townId;
    	
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
        
        JLabel nameLabel= new JLabel("주차장 명");
        nameLabel.setBounds(10,270,60,25);
        add(nameLabel);
        
        JTextField nameField = new JTextField();
        nameField.setBounds(100,270,275,25);
        add(nameField);
        
        JLabel telNumberLabel= new JLabel("연락처");
        telNumberLabel.setBounds(10,310,60,25);
        add(telNumberLabel);
        
        JTextField telNumberField = new JTextField();
        telNumberField.setBounds(100,310,275,25);
        add(telNumberField);
        
        JLabel imageFileLabel= new JLabel("주자창 배치도");
        imageFileLabel.setBounds(10,350,80,25);
        add(imageFileLabel);
        
        JButton imageFileField = new JButton("파일 찾기");
        imageFileField.setBounds(100,350,275,25);
        add(imageFileField);
        
        JLabel imageFilePathLabel= new JLabel("파일 경로");
        imageFilePathLabel.setBounds(10,390,80,25);
        add(imageFilePathLabel);
        
        JTextField imageFilePathField = new JTextField();
        imageFilePathField.setBounds(100,390,275,25);
        add(imageFilePathField);        
        
        JLabel addressLabel= new JLabel("실제 주소");
        addressLabel.setBounds(10,430,60,25);
        add(addressLabel);
        
        JTextField addressField = new JTextField();
        addressField.setBounds(100,430,275,25);
        add(addressField);
        
        JLabel locationIdLabel= new JLabel("단위 주소");
        locationIdLabel.setBounds(10,470,60,25);
        add(locationIdLabel);
        
        JComboBox<String> provinceBox = new JComboBox<>();
        provinceBox.setBounds(100, 470, 92, 25);
        add(provinceBox);
        
        JComboBox<String> cityBox = new JComboBox<>();
        cityBox.setBounds(192, 470, 92, 25);
        add(cityBox);

        LocationDAO locationDAO = new LocationDAO();
        List<getLocationDTO> list = locationDAO.getLocations(0);
        String[] items = new String[list.size()];
        for (int i =0; i< list.size(); i++) {
        	System.out.println(items[i]);
            items[i] = list.get(i).getName();
        }
        
        JComboBox<String> townBox = new JComboBox<>();
        townBox.setBounds(284, 470, 92, 25);
        add(townBox);
        
        
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
        
        //시 버튼 선택 액션
        provinceBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int index = ((JComboBox)e.getSource()).getSelectedIndex();
        		int id = list.get(index).getId();	
        	}
        });
        
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                InsertParkingLotDTO insertParkingLotDTO = new InsertParkingLotDTO(nameField.getText(),
                											telNumberField.getText(),
                											0, // locationField return타입 변환 메소드 구현해야됨
                											addressField.getText(),
                											imageFilePathField.getText());
                
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
                	imageFilePathField.setText(fileChooser.getSelectedFile().getPath());
                	imageFilePathField.setEditable(false);
                	String path = fileChooser.getSelectedFile().getPath();
                	ImageSaver saver = new ImageSaver();
                	saver.saveImage(path);
                }
            }
        });
        
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new AdminUI());
            }
        });

    }
    
}