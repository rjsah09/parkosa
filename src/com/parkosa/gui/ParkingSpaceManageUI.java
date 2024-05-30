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
import com.parkosa.dao.CarTypeDAO;
import com.parkosa.dao.FeePolicyDAO;
import com.parkosa.dao.ParkingSpaceDAO;
import com.parkosa.dto.InsertFeePolicyDTO;
import com.parkosa.dto.InsertParkingSpaceDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.RegisteredParkingSpaceDTO;
import com.parkosa.vo.CarTypeVO;
import com.parkosa.vo.ParkingSpaceVO;

public class ParkingSpaceManageUI extends UI {

	int parkingLotId;
	int feePolicyId;

	public ParkingSpaceManageUI(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public void placeComponents() {
		setLayout(null);

		JButton cancelButton = new JButton("뒤로가기");
		cancelButton.setBounds(10, 530, 100, 25);
		add(cancelButton);

		// -------주차공간-----//
		ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO();
		List<RegisteredParkingSpaceDTO> registeredParkingSpaces = parkingSpaceDAO.listParkingSpace(parkingLotId);

		DefaultTableModel parkingSpaceModel = new DefaultTableModel(
				new String[] { "차종", "단위시간(분)", "증가액", "최대시간", "주차구역" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable parkingSpaceTable = new JTable(parkingSpaceModel);
		parkingSpaceTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		parkingSpaceTable.setRowHeight(20);
		parkingSpaceTable.setShowVerticalLines(false);
		parkingSpaceTable.setShowHorizontalLines(false);

		JScrollPane parkingSpacePane = new JScrollPane(parkingSpaceTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		parkingSpacePane.setBounds(10, 10, 768, 220);
		add(parkingSpacePane);

		for (int i = 0; i < registeredParkingSpaces.size(); i++) {
			String[] row = new String[5];
			row[4] = registeredParkingSpaces.get(i).getCarTypeName();
			row[1] = Integer.toString(registeredParkingSpaces.get(i).getIncereaseMinute());
			row[2] = Integer.toString(registeredParkingSpaces.get(i).getIncreaseFee());
			row[3] = Integer.toString(registeredParkingSpaces.get(i).getMaximumTime());
			row[0] = registeredParkingSpaces.get(i).getDescription();

			parkingSpaceModel.addRow(row);
		}

		// -----주차 공간 끝-----//

		// -----요금 정책----//
		FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
		List<InsertFeePolicyDTO> feePolicies = feePolicyDAO.getListFeePolicy(parkingLotId);

		DefaultTableModel feePolicyModel = new DefaultTableModel(
				new String[] { "요금정책ID", "단위시간(분)", "최대시간", "증가액", "차종" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable feePolicyTable = new JTable(feePolicyModel);
		feePolicyTable.addMouseListener(new feePolicyAdaptor());
		feePolicyTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		feePolicyTable.setRowHeight(20);
		feePolicyTable.setShowVerticalLines(false);
		feePolicyTable.setShowHorizontalLines(false);

		JScrollPane feePolicyPane = new JScrollPane(feePolicyTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		feePolicyPane.setBounds(10, 240, 768, 220);
		add(feePolicyPane);

		for (int i = 0; i < feePolicies.size(); i++) {
			String[] row = new String[5];
			row[0] = Integer.toString(feePolicies.get(i).getId());
			row[1] = Integer.toString(feePolicies.get(i).getIncreaseMinute());
			row[2] = Integer.toString(feePolicies.get(i).getMaximumTime());
			row[3] = Integer.toString(feePolicies.get(i).getIncreaseFee());

			if (feePolicies.get(i).getCarTypeName() == null) {
				row[4] = "모든 차종";
			} else {
				row[4] = feePolicies.get(i).getCarTypeName();
			}

			feePolicyModel.addRow(row);
		}

		// -----요금 정책 끝-----//

		JLabel descriptionLabel = new JLabel("주차공간명");
		descriptionLabel.setBounds(10 + 200, 470, 60, 25);
		add(descriptionLabel);

		JTextField descriptionfield = new JTextField();
		descriptionfield.setBounds(100 + 200, 470, 275, 25);
		add(descriptionfield);

		JButton insertButton = new JButton("생성");
		insertButton.setBounds(350, 500, 100, 30);
		add(insertButton);

		// Event listener for the cancel button
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertParkingSpaceDTO insertParkingSpaceDTO = new InsertParkingSpaceDTO(parkingLotId,
						descriptionfield.getText(), feePolicyId);
				
				String validateResult = doValidate(insertParkingSpaceDTO);
				
				if (validateResult.equals("")) {
					ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO();

					int result = parkingSpaceDAO.insertFeePolicy(insertParkingSpaceDTO);
					String output = result == 1 ? "등록되었습니다." : "등록에 실패했습니다,";
					JOptionPane.showMessageDialog(null, output);

					if (result == 1) {
						parkingSpaceModel.setNumRows(0);
						List<RegisteredParkingSpaceDTO> registeredParkingSpaces = parkingSpaceDAO
								.listParkingSpace(parkingLotId);
						for (int i = 0; i < registeredParkingSpaces.size(); i++) {
							String[] row = new String[5];
							row[0] = registeredParkingSpaces.get(i).getDescription();
							row[1] = Integer.toString(registeredParkingSpaces.get(i).getIncereaseMinute());
							row[2] = Integer.toString(registeredParkingSpaces.get(i).getIncreaseFee());
							row[3] = Integer.toString(registeredParkingSpaces.get(i).getMaximumTime());
							row[4] = registeredParkingSpaces.get(i).getCarTypeName();
							parkingSpaceModel.addRow(row);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, validateResult);
				}
			}

		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new ParkingLotManageUI());
			}
		});

	}

	class feePolicyAdaptor implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int row = table.getSelectedRow();
			System.out.println("id = " + (String) table.getValueAt(row, 0));
			feePolicyId = Integer.parseInt((String) table.getValueAt(row, 0));

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

	public String doValidate(InsertParkingSpaceDTO dto) {
		if (dto.getFeePolicyId() == 0) {
			return "요금정책을 선택해주세요";
		} else if (dto.getDescription() == null || dto.getDescription().equals("")) {
			return "주차공간명을 입력해주세요.";
		}
		
		return "";
	}
}
