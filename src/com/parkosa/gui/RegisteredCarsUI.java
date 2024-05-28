package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dto.RegisteredCarDTO;

public class RegisteredCarsUI extends UI {
	
	public void placeComponents() {
        setLayout(null);
        
        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(10, 10, 100, 25);
        add(cancelButton);
        
    	CarDAO carDAO = new CarDAO();
    	List <RegisteredCarDTO> registeredCars = carDAO.getRegisteredCars();
    	
    	DefaultTableModel model = new DefaultTableModel(new String[] {"차량 번호", "차종", ""}, 0) {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		};
    	};
        
    	JTable innerTable = new JTable(model);
    	innerTable.addMouseListener(new TableMouseAdaptor());
    	innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
    	innerTable.setRowHeight(20);
    	innerTable.setShowVerticalLines(false);
    	innerTable.setShowHorizontalLines(false);
    	
        JScrollPane jsp = new JScrollPane(innerTable,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(10, 50, 365, 500);
        add(jsp);
        
        for (int i = 0 ; i < registeredCars.size() ; i++) {
    		String[] row = new String[3];
    		row[0] = registeredCars.get(i).getCarCode();
    		row[1] = registeredCars.get(i).getCarTypeName();
    		row[2] = "삭제";
    		model.addRow(row);
    	}
        
        //버튼 이벤트
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIController.changeUI(ui, new MainScreenUI());
            }
        });
        
	}
	
}

class TableMouseAdaptor implements MouseListener {
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		
		if (col == 2) {
			String carCode = (String) table.getValueAt(row, 0);
			int result = JOptionPane.showConfirmDialog(null, "등록한 차량을 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_NO_OPTION) {
				CarDAO carDAO = new CarDAO();
				if (carDAO.deleteCar(carCode)) {					
					((DefaultTableModel)table.getModel()).removeRow(row);
				}
			}
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
