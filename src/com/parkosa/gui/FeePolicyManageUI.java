package com.parkosa.gui;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dao.CarTypeDAO;
import com.parkosa.dao.FeePolicyDAO;
import com.parkosa.dto.InsertFeePolicyDTO;
import com.parkosa.dto.InsertReservationDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.RegisteredParkingLotDTO;
import com.parkosa.vo.CarTypeVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FeePolicyManageUI extends UI {

	int parkingLotId;

	public FeePolicyManageUI(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public void placeComponents() {
		setLayout(null);

		AccountDAO accountDAO = new AccountDAO();
		String name = accountDAO.getName();

		JButton cancelButton = new JButton("뒤로가기");
		cancelButton.setBounds(10, 530, 100, 25);
		add(cancelButton);

		FeePolicyDAO dao = new FeePolicyDAO();
		List<InsertFeePolicyDTO> dtos = dao.getListFeePolicy(parkingLotId);

		DefaultTableModel model = new DefaultTableModel(new String[] { "단위시간(분)", "최대시간", "증가액", "차종" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable innerTable = new JTable(model);
		innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		innerTable.setRowHeight(20);
		innerTable.setShowVerticalLines(false);
		innerTable.setShowHorizontalLines(false);

		JScrollPane jsp = new JScrollPane(innerTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(70, 10, 650, 280);
		add(jsp);

		JLabel increaseMinuteLabel = new JLabel("기본 증가시간");
		increaseMinuteLabel.setBounds(10 + 200, 310, 100, 25);
		add(increaseMinuteLabel);

		JTextField increaseMinuteField = new JTextField("10");
		increaseMinuteField.setBounds(120 + 200, 310, 255, 25);
		add(increaseMinuteField);

		JLabel increaseFeeLabel = new JLabel("단위시간당 이용료");
		increaseFeeLabel.setBounds(10 + 200, 350, 100, 25);
		add(increaseFeeLabel);

		JTextField increaseFeeField = new JTextField();
		increaseFeeField.setBounds(120 + 200, 350, 255, 25);
		add(increaseFeeField);

		JLabel maximumTimeLabel = new JLabel("최대 이용시간");
		maximumTimeLabel.setBounds(10 + 200, 390, 100, 25);
		add(maximumTimeLabel);

		JTextField maximumTimeField = new JTextField();
		maximumTimeField.setBounds(120 + 200, 390, 255, 25);
		add(maximumTimeField);

		JLabel carTypeIdLabel = new JLabel("차종ID");
		carTypeIdLabel.setBounds(10 + 200, 430, 100, 25);
		add(carTypeIdLabel);

		CarTypeDAO carTypeDAO = new CarTypeDAO();
		List<CarTypeVO> list = carTypeDAO.carTypeList();
		String[] items = new String[list.size() + 1];

		for (int i = 0; i < list.size(); i++) {
			items[i] = list.get(i).getName();
		}

		items[list.size()] = "모든 차종";

		JComboBox<String> comboSelectCarBox = new JComboBox<>(items);
		comboSelectCarBox.setBounds(120 + 200, 430, 255, 25);
		add(comboSelectCarBox);

		JButton insertButton = new JButton("생성");
		insertButton.setBounds(350, 480, 100, 30);
		add(insertButton);

		// "단위시간(분)", "최대시간", "증가액", "차종"
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
			model.addRow(row);
		}

		// Event listener for the cancel button
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = (String) (comboSelectCarBox.getSelectedItem());
				int carTypeId = 0;

				if (str.equals("모든 차종 가능")) {
					carTypeId = 0;
				} else {
					carTypeId = carTypeDAO.selectCarNo(str);
				}
				
				//______________________________________________//
				String validateResult = doValidate(increaseMinuteField.getText(),
						increaseFeeField.getText(),
						maximumTimeField.getText());
				
				if (validateResult.equals("")) {
					
					InsertFeePolicyDTO insertFeePolicyDTO = new InsertFeePolicyDTO(
							Integer.valueOf(increaseMinuteField.getText()), Integer.valueOf(increaseFeeField.getText()),
							Integer.valueOf(maximumTimeField.getText()), Integer.valueOf(carTypeId), parkingLotId);
					
					FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
					feePolicyDAO.insertFeePolicy(insertFeePolicyDTO);

					// 새로고침 코드
					JOptionPane.showMessageDialog(null, "등록되었습니다.");
					model.setNumRows(0);
					List<InsertFeePolicyDTO> dtos = feePolicyDAO.getListFeePolicy(parkingLotId);
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
						model.addRow(row);
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

	public String doValidate(String min, String fee, String max) {
		final String REGEX = "[0-9]+";

		if (min == null || min.trim().isEmpty()) {
			return "단위 시간(분)을 입력해주세요.";
		} else if (fee == null || fee.trim().isEmpty()) {
			return "단위 당 요금을 입력해주세요.";
		} else if (max == null || max.trim().isEmpty()) {
			return "최대시간을 입력해주세요.";
		}

		if (min.matches(REGEX)) {
			if (fee.matches(REGEX)) {
				if (max.matches(REGEX)) {
					return "";
				} else {
					return "최대 시간이 숫자가 아닙니다.";
				}
			} else {
				return "단위 당 요금이 숫자가 아닙니다.";
			}
		} else {
			return "기본 증가시간이 숫자가 아닙니다.";
		}
		
	}

}
