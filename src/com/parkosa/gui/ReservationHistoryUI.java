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

public class ReservationHistoryUI extends UI{

	List<RegisteredReservationDTO> list;
	int reservationId;
	JTable innerTable;
	
	public void placeComponents() {
		setLayout(null);

		ReservationDAO reservationDAO = new ReservationDAO();
		JButton cancelButton = new JButton("뒤로가기");
		cancelButton.setBounds(10, 530, 100, 25);
		add(cancelButton);

		CarDAO carDAO = new CarDAO();
		list = reservationDAO.getReservations();

		DefaultTableModel model = new DefaultTableModel(new String[] {"주차장이름", "주차공간", "입차시간", "출차시간", "주차비", "상태", "예약 취소"}, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		innerTable = new JTable(model);
		innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		innerTable.addMouseListener(new CancelReservationAdaptor());
		innerTable.setRowHeight(20);
		innerTable.setShowVerticalLines(false);
		innerTable.setShowHorizontalLines(false);

		JScrollPane jsp = new JScrollPane(innerTable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(10, 10, 768, 510);
		add(jsp);
		
		for (int i = 0 ; i < list.size() ; i++) {
			String[] row = new String[7];
			row[0] = list.get(i).getParkingLotname();
			row[1] = list.get(i).getParkingSpaceDescription();
			row[2] = list.get(i).getStartTime();
			row[3] = list.get(i).getEndTime();
			row[4] = String.valueOf(list.get(i).getTotalAmount());
			row[5] = list.get(i).getStatus();
			if (list.get(i).getStatus().equals("주차 예정")) {
				row[6] = "예약취소";
			}
			model.addRow(row);
		}
		resizeColumnWidth(innerTable);
		
		//버튼 이벤트
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new MainScreenUI());
			}
		});

	}
	
	class CancelReservationAdaptor implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int row = table.getSelectedRow();	//행 번호 -> list.get(i)의 i와 같음
			int col = table.getSelectedColumn();
			reservationId = list.get(row).getReservationId();
			
			if (col == 6) {
				String status = list.get(row).getStatus();			

				if (status.equals("주차 예정")) {
					int result = JOptionPane.showConfirmDialog(null, "예약을 취소하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
					
					if (result == JOptionPane.YES_NO_OPTION) {
						ReservationDAO dao = new ReservationDAO();
						boolean succeed = dao.cancelReservation(list.get(row).getReservationId());
						String output = succeed ? "예약을 취소했습니다." : "예약을 취소할 수 없습니다.";
						JOptionPane.showMessageDialog(null, output);
						
						ReservationDAO reservationDAO = new ReservationDAO();
						list = reservationDAO.getReservations();
						DefaultTableModel model = (DefaultTableModel) innerTable.getModel();
						model.setNumRows(0);
						
						for (int i = 0 ; i < list.size() ; i++) {
							String[] newRow = new String[7];
							newRow[0] = list.get(i).getParkingLotname();
							newRow[1] = list.get(i).getParkingSpaceDescription();
							newRow[2] = list.get(i).getStartTime();
							newRow[3] = list.get(i).getEndTime();
							newRow[4] = String.valueOf(list.get(i).getTotalAmount());
							newRow[5] = list.get(i).getStatus();
							if (list.get(i).getStatus().equals("주차 예정")) {
								newRow[6] = "예약취소";
							}
							model.addRow(newRow);
						}
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
}
