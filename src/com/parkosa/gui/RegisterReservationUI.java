package com.parkosa.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterReservationUI extends UI{
	private JTextField inYear, inMonth, inDay, inHour, inMinute;
	private JTextField outYear, outMonth, outDay, outHour, outMinute;
	private JButton searchButton;
	private JTextArea resultArea;
	private JComboBox<String> cityComboBox, districtComboBox, townComboBox;
	private JTextField carSelectionField;
	private JComboBox<String> carNumberComboBox;
	@Override
	public void placeComponents() {
		GridBagConstraints gbc = new GridBagConstraints();

		// Create car selection
		JLabel carLabel = new JLabel("차량선택");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(carLabel, gbc);


		String[] carNumbers = {"123가 4567", "789나 1011", "112다 1314"}; // 차량번호 예시 데이터
		carNumberComboBox = new JComboBox<>(carNumbers);
		gbc.gridx = 1;
		gbc.gridwidth = 4;
		add(carNumberComboBox, gbc);

		// Create region selection
		JLabel regionLabel = new JLabel("지역선택");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(regionLabel, gbc);

		String[] cities = {"서울특별시", "부산광역시", "대구광역시"}; // 시/도 예시 데이터
		String[] districts = {"강남구", "해운대구", "수성구"}; // 시/군/구 예시 데이터
		String[] towns = {"압구정동", "중동", "범어동"}; // 읍/면/동 예시 데이터

		cityComboBox = new JComboBox<>(cities);
		districtComboBox = new JComboBox<>(districts);
		townComboBox = new JComboBox<>(towns);

		gbc.gridx = 1;
		gbc.gridwidth = 3;
		add(cityComboBox, gbc);
		gbc.gridx = 4;
		gbc.gridwidth = 3;
		add(districtComboBox, gbc);
		gbc.gridx = 7;
		gbc.gridwidth = 4;
		add(townComboBox, gbc);

		// Create input fields for entry time
		JLabel inLabel = new JLabel("입차시간");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		add(inLabel, gbc);

		inYear = new JTextField(4);
		inMonth = new JTextField(2);
		inDay = new JTextField(2);
		inHour = new JTextField(2);
		inMinute = new JTextField(2);

		gbc.gridx = 1;
		add(inYear, gbc);
		gbc.gridx = 2;
		add(new JLabel("년"), gbc);
		gbc.gridx = 3;
		add(inMonth, gbc);
		gbc.gridx = 4;
		add(new JLabel("월"), gbc);
		gbc.gridx = 5;
		add(inDay, gbc);
		gbc.gridx = 6;
		add(new JLabel("일"), gbc);
		gbc.gridx = 7;
		add(inHour, gbc);
		gbc.gridx = 8;
		add(new JLabel("시"), gbc);
		gbc.gridx = 9;
		add(inMinute, gbc);
		gbc.gridx = 10;
		add(new JLabel("분"), gbc);

		// Create input fields for exit time
		JLabel outLabel = new JLabel("출차시간");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(outLabel, gbc);

		outYear = new JTextField(4);
		outMonth = new JTextField(2);
		outDay = new JTextField(2);
		outHour = new JTextField(2);
		outMinute = new JTextField(2);

		gbc.gridx = 1;
		add(outYear, gbc);
		gbc.gridx = 2;
		add(new JLabel("년"), gbc);
		gbc.gridx = 3;
		add(outMonth, gbc);
		gbc.gridx = 4;
		add(new JLabel("월"), gbc);
		gbc.gridx = 5;
		add(outDay, gbc);
		gbc.gridx = 6;
		add(new JLabel("일"), gbc);
		gbc.gridx = 7;
		add(outHour, gbc);
		gbc.gridx = 8;
		add(new JLabel("시"), gbc);
		gbc.gridx = 9;
		add(outMinute, gbc);
		gbc.gridx = 10;
		add(new JLabel("분"), gbc);

		// Create search button
		searchButton = new JButton("검색버튼");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 11;
		add(searchButton, gbc);

		// Create result area
		resultArea = new JTextArea(20, 30);
		resultArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 11;
		gbc.fill = GridBagConstraints.BOTH;
		add(scrollPane, gbc);

		// Add action listener to the search button
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disableInputFields();
				displayResults();
			}
		});
	}

	private void disableInputFields() {
		carNumberComboBox.setEnabled(false);
		cityComboBox.setEnabled(false);
		districtComboBox.setEnabled(false);
		townComboBox.setEnabled(false);
		inYear.setEnabled(false);
		inMonth.setEnabled(false);
		inDay.setEnabled(false);
		inHour.setEnabled(false);
		inMinute.setEnabled(false);
		outYear.setEnabled(false);
		outMonth.setEnabled(false);
		outDay.setEnabled(false);
		outHour.setEnabled(false);
		outMinute.setEnabled(false);
	}

	private void displayResults() {
		// Here you can fetch and display the data. This is just a sample.
		resultArea.setText("Displaying results for the given time period and region...");
	}

}
