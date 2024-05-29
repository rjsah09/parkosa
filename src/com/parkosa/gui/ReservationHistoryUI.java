package com.parkosa.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dao.CarTypeDAO;
import com.parkosa.dao.ReservationDAO;
import com.parkosa.dto.GetAvailableParkingSpaceDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.RegisteredReservationDTO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.CarVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReservationHistoryUI extends UI{

	public void placeComponents() {
		setLayout(null);

		ReservationDAO reservationDAO = new ReservationDAO();
		JButton cancelButton = new JButton("뒤로가기");
		cancelButton.setBounds(10, 10, 100, 25);
		add(cancelButton);

		CarDAO carDAO = new CarDAO();
		ArrayList<RegisteredReservationDTO> list = reservationDAO.getReservations();

		DefaultTableModel model = new DefaultTableModel(new String[] {"주차장이름", "주차공간", "입차시간", "출차시간", "최종요금"}, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		JTable innerTable = new JTable(model);
		innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		innerTable.setRowHeight(20);
		innerTable.setShowVerticalLines(false);
		innerTable.setShowHorizontalLines(false);

		JScrollPane jsp = new JScrollPane(innerTable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(10, 50, 365, 500);
		add(jsp);
		
		for (int i = 0 ; i < list.size() ; i++) {
			String[] row = new String[5];
			row[0] = list.get(i).getParkingLotname();
			row[1] = list.get(i).getParkingSpaceDescription();
			row[2] = list.get(i).getStartTime();
			row[3] = list.get(i).getEndTime();
			row[4] = String.valueOf(list.get(i).getTotalAmount());
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
