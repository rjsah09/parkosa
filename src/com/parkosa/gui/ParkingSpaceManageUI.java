package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarTypeDAO;
import com.parkosa.dao.FeePolicyDAO;
import com.parkosa.dao.ParkingSpaceDAO;
import com.parkosa.dto.InsertFeePolicyDTO;
import com.parkosa.dto.InsertParkingSpaceDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.RegisteredParkingSpaceDTO;
import com.parkosa.vo.CarTypeVO;

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
        List <RegisteredParkingSpaceDTO> registeredParkingSpaces = parkingSpaceDAO.listParkingSpace(parkingLotId);
        
        FeePolicyDAO dao = new FeePolicyDAO();
        List<InsertFeePolicyDTO> dtos = dao.getListFeePolicy(parkingLotId);
        
        CarTypeDAO carTypeDAO = new CarTypeDAO();
        List<CarTypeVO> list = carTypeDAO.carTypeList();
        String[] items = new String[list.size() + 1];
        
        for (int i = 0; i < list.size(); i++) {
            items[i] = list.get(i).getName();
        }
        
        items[list.size()] = "모든 차종";
        
        DefaultTableModel model = new DefaultTableModel(new String[] {"차종","단위시간(분)", "증가액", "최대시간", "주차구역"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable innerTable1 = new JTable(model);
        innerTable1.addMouseListener(new TableMouseAdaptor());
        innerTable1.setFont(new Font("NanumGothic", Font.PLAIN, 16));
        innerTable1.setRowHeight(20);
        innerTable1.setShowVerticalLines(false);
        innerTable1.setShowHorizontalLines(false);

        JScrollPane jsp1 = new JScrollPane(innerTable1,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp1.setBounds(10, 50, 365, 170);
        add(jsp1);
        
        DefaultTableModel model2 = new DefaultTableModel(new String[] {"단위시간(분)","최대시간", "증가액", "차종"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };       
        
        JTable innerTable2 = new JTable(model2);
        innerTable2.addMouseListener(new TableMouseAdaptor());
        innerTable2.setFont(new Font("NanumGothic", Font.PLAIN, 16));
        innerTable2.setRowHeight(20);
        innerTable2.setShowVerticalLines(false);
        innerTable2.setShowHorizontalLines(false);

        JScrollPane jsp2 = new JScrollPane(innerTable2,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp2.setBounds(10, 240, 365, 170);
        add(jsp2);

        JLabel descriptionLabel = new JLabel("주차구역");
        descriptionLabel.setBounds(10, 430, 60, 25);
        add(descriptionLabel);

        JTextField descriptionfield = new JTextField();
        descriptionfield.setBounds(100, 430, 275, 25);
        add(descriptionfield);

        JLabel feePolicyIdLabel = new JLabel("요금정책ID");
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
        
        for (int i = 0; i < dtos.size(); i++) {
            String[] row = new String[4];
            row[0] = Integer.toString(dtos.get(i).getIncreaseMinute());
            row[1] = Integer.toString(dtos.get(i).getMaximumTime());
            row[2] = Integer.toString(dtos.get(i).getIncreaseFee());
            if (dtos.get(i).getCarTypeName() == null) {
            	row[3] = "모든 차종";            	
            } else {
            	row[3] = dtos.get(i).getCarTypeName();
            }
            model2.addRow(row);
        }

        // Event listener for the cancel button
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InsertParkingSpaceDTO insertParkingSpaceDTO = new InsertParkingSpaceDTO(parkingLotId, descriptionfield.getText(), Integer.valueOf(feePolicyIdField.getText()));
                ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO();
                
                int result = parkingSpaceDAO.insertFeePolicy(insertParkingSpaceDTO);
                String output = result == 1? "등록되었습니다." :"등록에 실패했습니다,";
                JOptionPane.showMessageDialog(null, output);
                
                if (result == 1) {
					model.setNumRows(0);
					List <RegisteredParkingSpaceDTO> registeredParkingSpaces = parkingSpaceDAO.listParkingSpace(parkingLotId);
					for (int i = 0; i < registeredParkingSpaces.size(); i++) {
			            String[] row = new String[5];
			            row[0] = registeredParkingSpaces.get(i).getDescription();
			            row[1] = Integer.toString(registeredParkingSpaces.get(i).getIncereaseMinute());
			            row[2] = Integer.toString(registeredParkingSpaces.get(i).getIncreaseFee());
			            row[3] = Integer.toString(registeredParkingSpaces.get(i).getMaximumTime());			            
			            row[4] = registeredParkingSpaces.get(i).getCarTypeName();
			            model.addRow(row);
			        }
					
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
                GUIController.changeUI(ui, new ParkingLotManageUI());
            }
        });

    }
}
