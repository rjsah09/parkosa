package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.LocationDAO;
import com.parkosa.dao.ParkingLotDAO;
import com.parkosa.dto.InsertParkingLotDTO;
import com.parkosa.dto.RegisteredParkingLotDTO;
import com.parkosa.dto.getLocationDTO;
import com.parkosa.image.ImageSaver;

public class ParkingLotManageUI extends UI {
    int provinceId;
    int cityId;
    int townId;
    JComboBox<String> provinceBox;
    JComboBox<String> cityBox;
    JComboBox<String> townBox;
    JTable innerTable;

    public void placeComponents() {
    	
        setLayout(null);
        
        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 10, 100, 25);
        add(cancelButton);
     
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<RegisteredParkingLotDTO> registeredparkingLots = parkingLotDAO.listParkingLot();
        
        DefaultTableModel model = new DefaultTableModel(new String[] {"주차장 id", "주차장명", "주소", "요금정책", "주차구역"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        innerTable = new JTable(model);
        innerTable.addMouseListener(new ParkingLotTableAdaptor());
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
        
        LocationDAO locationDAO = new LocationDAO();
        List<getLocationDTO> provinceList = locationDAO.getLocations(null);
        String[] provinceItems = new String[provinceList.size()];
        for (int i = 0; i < provinceList.size(); i++) {
            provinceItems[i] = provinceList.get(i).getName();
        }
        
        provinceBox = new JComboBox<>(provinceItems);
        provinceBox.setBounds(100, 470, 92, 25);
        add(provinceBox);
        
        cityBox = new JComboBox<>();
        cityBox.setBounds(192, 470, 92, 25);
        add(cityBox);
        
        townBox = new JComboBox<>();
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
        
        for (int i = 0; i < registeredparkingLots.size(); i++) {
            String[] row = new String[5];
            row[0] = Integer.toString(registeredparkingLots.get(i).getId());
            row[1] = registeredparkingLots.get(i).getName();
            row[2] = registeredparkingLots.get(i).getAddress();
            row[3] = "요금정책";
            row[4] = "주차구역";
            model.addRow(row);
        }
        
        //도/시버튼 이벤트
        provinceBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cityId = 0;
        		townId = 0;
        		
        		int index = ((JComboBox) e.getSource()).getSelectedIndex();
        		provinceId = provinceList.get(index).getId();
        		
                List<getLocationDTO> cityList = locationDAO.getLocations(provinceId);
                cityBox.removeAllItems();
                for (getLocationDTO city : cityList) {
                    cityBox.addItem(city.getName());
                }
        	}
        });
        
        //시/군/구 버튼 이벤트
        cityBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	townId = 0;
            	
                if (cityBox.getSelectedItem() != null) {
                    int index = cityBox.getSelectedIndex();
                    if (index >= 0) {
                        List<getLocationDTO> cityList = locationDAO.getLocations(provinceId);
                        cityId = cityList.get(index).getId();
                        List<getLocationDTO> townList = locationDAO.getLocations(cityId);
                        townBox.removeAllItems();
                        for (getLocationDTO town : townList) {
                            townBox.addItem(town.getName());
                        }
                    }
                }
            }
        });
        
        //읍/면/동 버튼 이벤트
        townBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (townBox.getSelectedItem() != null) {
                    int index = townBox.getSelectedIndex();
                    if (index >= 0) {
                        List<getLocationDTO> cityList = locationDAO.getLocations(provinceId);
                        townId = cityList.get(index).getId();
                    }
                }
            }
        });
        
        //등록 버튼
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                InsertParkingLotDTO insertParkingLotDTO = new InsertParkingLotDTO(nameField.getText(),
                		telNumberField.getText(),
                		townId,
                		addressField.getText(),
                		imageFilePathField.getText()
                		);
                
                ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
                int result = parkingLotDAO.insertParkLot(insertParkingLotDTO);
                String output = result == 1 ? "등록되었습니다" : "등록에 실패했습니다";
                JOptionPane.showMessageDialog(null, output);
                if (result == 1) {
                	model.setNumRows(0);
                    List<RegisteredParkingLotDTO> registeredparkingLots = parkingLotDAO.listParkingLot();
                    for (int i = 0; i < registeredparkingLots.size(); i++) {
                        String[] row = new String[5];
                        row[0] = Integer.toString(registeredparkingLots.get(i).getId());
                        row[1] = registeredparkingLots.get(i).getName();
                        row[2] = registeredparkingLots.get(i).getAddress();
                        row[3] = "요금정책";
                        row[4] = "주차구역";
                        model.addRow(row);
                    }
                }
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
                	ImageSaver.saveImage(path);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIController.changeUI(ui, new AdminUI());
            }
        });

    }
    
    class ParkingLotTableAdaptor implements MouseListener {
    	public void mouseClicked(MouseEvent e) {
    		JTable table = (JTable) e.getSource();
    		int row = table.getSelectedRow();
    		int col = table.getSelectedColumn();
    		int parkingLotId = Integer.parseInt((String) table.getValueAt(row, 0));
    		
    		if (col == 3) {
    			GUIController.changeUI(ui, new FeePolicyManageUI(parkingLotId));
    		} else if (col == 4) {
    			
    		}
    	}

    	@Override
    	public void mousePressed(MouseEvent e) {
    	// TODO Auto-generated method stub
    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
    	// TODO Auto-generated method stub
    	}

    	@Override
    	public void mouseEntered(MouseEvent e) {
    	// TODO Auto-generated method stub
    	}

    	@Override
    	public void mouseExited(MouseEvent e) {
    	// TODO Auto-generated method stub
    	}
    }
    
}
