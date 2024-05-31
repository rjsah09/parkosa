package com.parkosa.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.parkosa.dao.CarDAO;
import com.parkosa.dao.ReservationDAO;
import com.parkosa.dto.RegisteredReservationDTO;

public class PastReservationHistoryUI extends UI{

	List<RegisteredReservationDTO> list;
	int reservationId;
	JTable innerTable;
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public void placeComponents() {
		setLayout(null);

		ReservationDAO reservationDAO = new ReservationDAO();
		JButton cancelButton = new JButton("뒤로가기");
		cancelButton.setBounds(10, 530, 100, 25);
		add(cancelButton);

		CarDAO carDAO = new CarDAO();
		list = reservationDAO.getPastReservations();

		DefaultTableModel model = new DefaultTableModel(new String[] {"주차장이름", "주차공간", "입차시간", "출차시간", "주차비"}, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		innerTable = new JTable(model);
		innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		innerTable.setRowHeight(20);
		innerTable.setShowVerticalLines(false);
		innerTable.setShowHorizontalLines(false);

		JScrollPane jsp = new JScrollPane(innerTable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(10, 10, 768, 510);
		
		for (int i = 0 ; i < list.size() ; i++) {
			String[] row = new String[6];
			row[0] = list.get(i).getParkingLotname();
			row[1] = list.get(i).getParkingSpaceDescription();
			row[2] = list.get(i).getStartTime();
			row[3] = list.get(i).getEndTime();
			row[4] = String.valueOf(list.get(i).getTotalAmount());
			model.addRow(row);
		}
		
		resizeColumnWidth(innerTable);
		add(jsp);
		
		//버튼 이벤트
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new MainScreenUI());
			}
		});
		
	}
	
}
