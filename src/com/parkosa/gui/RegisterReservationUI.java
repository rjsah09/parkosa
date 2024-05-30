package com.parkosa.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dao.LocationDAO;
import com.parkosa.dao.ReservationDAO;
import com.parkosa.dto.GetAvailableParkingSpaceDTO;
import com.parkosa.dto.InsertReservationDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.getLocationDTO;
import com.parkosa.image.ImagePopUpFrame;

public class RegisterReservationUI extends UI {

	int provinceId, cityId, townId, parkingSpaceId;
	String carCode, startTime, endTime;
	List<GetAvailableParkingSpaceDTO> availableSpaceList;

	public void placeComponents() {

		setLayout(null);

		JButton cancelButton = new JButton("뒤로가기");
		cancelButton.setBounds(10, 10, 100, 25);
		add(cancelButton);

		JLabel carTypeLabel = new JLabel("차량");
		carTypeLabel.setBounds(10 + 200, 40, 50, 25);
		add(carTypeLabel);

		JLabel selectRegionLabel = new JLabel("지역");
		selectRegionLabel.setBounds(10 + 200, 70, 50, 25);
		add(selectRegionLabel);

		JLabel entryLabel = new JLabel("입차시간");
		entryLabel.setBounds(10 + 200, 100, 50, 25);
		add(entryLabel);

		JTextField entryYearField = new JTextField();
		entryYearField.setBounds(70 + 200, 100, 40, 25);
		add(entryYearField);

		JLabel entryYearLabel = new JLabel("년");
		entryYearLabel.setBounds(110 + 200, 100, 20, 25);
		add(entryYearLabel);

		JTextField entryMonthField = new JTextField();
		entryMonthField.setBounds(130 + 200, 100, 40, 25);
		add(entryMonthField);

		JLabel entryMonthLabel = new JLabel("월");
		entryMonthLabel.setBounds(170 + 200, 100, 20, 25);
		add(entryMonthLabel);

		JTextField entryDayField = new JTextField();
		entryDayField.setBounds(190 + 200, 100, 40, 25);
		add(entryDayField);

		JLabel entryDayLabel = new JLabel("일");
		entryDayLabel.setBounds(230 + 200, 100, 20, 25);
		add(entryDayLabel);

		JTextField entryHourField = new JTextField();
		entryHourField.setBounds(250 + 200, 100, 40, 25);
		add(entryHourField);

		JLabel entryHourLabel = new JLabel("시");
		entryHourLabel.setBounds(290 + 200, 100, 20, 25);
		add(entryHourLabel);

		JTextField entryMinuteField = new JTextField();
		entryMinuteField.setBounds(310 + 200, 100, 40, 25);
		add(entryMinuteField);

		JLabel entryMinuteLabel = new JLabel("분");
		entryMinuteLabel.setBounds(350 + 200, 100, 20, 25);
		add(entryMinuteLabel);

		JLabel exitLabel = new JLabel("출차시간");
		exitLabel.setBounds(10 + 200, 130, 60, 25);
		add(exitLabel);

		JTextField exitYearField = new JTextField();
		exitYearField.setBounds(70 + 200, 130, 40, 25);
		add(exitYearField);

		JLabel exitYearLabel = new JLabel("년");
		exitYearLabel.setBounds(110 + 200, 130, 20, 25);
		add(exitYearLabel);

		JTextField exitMonthField = new JTextField();
		exitMonthField.setBounds(130 + 200, 130, 40, 25);
		add(exitMonthField);

		JLabel exitMonthLabel = new JLabel("월");
		exitMonthLabel.setBounds(170 + 200, 130, 20, 25);
		add(exitMonthLabel);

		JTextField exitDayField = new JTextField();
		exitDayField.setBounds(190 + 200, 130, 40, 25);
		add(exitDayField);

		JLabel exitDayLabel = new JLabel("일");
		exitDayLabel.setBounds(230 + 200, 130, 20, 25);
		add(exitDayLabel);

		JTextField exitHourField = new JTextField();
		exitHourField.setBounds(250 + 200, 130, 40, 25);
		add(exitHourField);

		JLabel exitHourLabel = new JLabel("시");
		exitHourLabel.setBounds(290 + 200, 130, 20, 25);
		add(exitHourLabel);

		JTextField exitMinuteField = new JTextField();
		exitMinuteField.setBounds(310 + 200, 130, 40, 25);
		add(exitMinuteField);

		JLabel exitMinuteLabel = new JLabel("분");
		exitMinuteLabel.setBounds(350 + 200, 130, 20, 25);
		add(exitMinuteLabel);

		JButton serchButton = new JButton("주차장 검색");
		serchButton.setBounds(10 + 200, 160, 365, 25);
		add(serchButton);

		AccountDAO accountDAO = new AccountDAO();
		String name = accountDAO.getName();

		CarDAO carDAO = new CarDAO();
		List<RegisteredCarDTO> registeredCars = carDAO.getRegisteredCars();
		String[] carItems = new String[registeredCars.size()];

		for (int i = 0; i < registeredCars.size(); i++) {
			carItems[i] = registeredCars.get(i).getCarCode();
		}

		JComboBox<String> carBox = new JComboBox<>(carItems);
		carBox.setBounds(70 + 200, 40, 90, 25);
		add(carBox);

		carBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carCode = carBox.getSelectedItem().toString();
			}
		});

		DefaultTableModel model = new DefaultTableModel(new String[] { "주차장", "위치", "주차공간", "최종금액", "주차구역" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable innerTable = new JTable(model);
		innerTable.addMouseListener(new ParkingSpaceIdAdaptor());
		innerTable.setFont(new Font("NanumGothic", Font.PLAIN, 16));
		innerTable.setRowHeight(20);
		innerTable.setShowVerticalLines(false);
		innerTable.setShowHorizontalLines(false);

		JScrollPane jsp = new JScrollPane(innerTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(45, 200, 700, 320);
		add(jsp);

		JButton reserveButton = new JButton("예약하기");
		reserveButton.setBounds(10 + 200, 530, 365, 25);
		add(reserveButton);

		serchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				startTime = entryYearField.getText() + "-" + entryMonthField.getText() + "-" + entryDayField.getText()
						+ " " + entryHourField.getText() + ":" + entryMinuteField.getText() + ":" + "00";
				endTime = exitYearField.getText() + "-" + exitMonthField.getText() + "-" + exitDayField.getText() + " "
						+ exitHourField.getText() + ":" + exitMinuteField.getText() + ":" + "00";
				System.out.println(startTime + " " + endTime);

				InsertReservationDTO insertReservationDTO = new InsertReservationDTO(startTime, endTime, carCode,
						parkingSpaceId);

				String validateResult = doValidate(insertReservationDTO);

				if (validateResult.equals("")) {
					ReservationDAO reservationDAO = new ReservationDAO();
					availableSpaceList = reservationDAO.getParkingSpaceList(startTime, endTime, carCode, townId);

					for (int i = 0; i < availableSpaceList.size(); i++) {
						String[] row = new String[5];
						row[0] = availableSpaceList.get(i).getParkingLotName();
						row[1] = availableSpaceList.get(i).getLocationName();
						row[2] = availableSpaceList.get(i).getParkingSpaceDescription();
						row[3] = Integer.toString(availableSpaceList.get(i).getPredictPrice());
						row[4] = "이미지 파일";
						model.addRow(row);
					}
				} else {
					JOptionPane.showMessageDialog(null, validateResult);
				}
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIController.changeUI(ui, new MainScreenUI());
			}
		});
		
		
		
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = innerTable.getSelectedRow();
				
				if (idx == -1) {
					JOptionPane.showMessageDialog(null, "주차장을 선택하지 않았습니다. 주차장을 선택해주세요.");
				} else {
				
				startTime = entryYearField.getText() + "-" + entryMonthField.getText() + "-" + entryDayField.getText()
						+ " " + entryHourField.getText() + ":" + entryMinuteField.getText() + ":" + "00";
				endTime = exitYearField.getText() + "-" + exitMonthField.getText() + "-" + exitDayField.getText() + " "
						+ exitHourField.getText() + ":" + exitMinuteField.getText() + ":" + "00";

				InsertReservationDTO insertReservationDTO = new InsertReservationDTO(startTime, endTime, carCode,
						parkingSpaceId);


				GUIController.changeUI(ui, new MainScreenUI());

				String validateResult = doValidate(insertReservationDTO);
				if (validateResult.equals("")) {
					ReservationDAO reservationDAO = new ReservationDAO();
					reservationDAO.insertReservation(insertReservationDTO);

					GUIController.changeUI(ui, new MainScreenUI());
				} else {
					JOptionPane.showMessageDialog(null, validateResult);

				}
			}
			}
		});

		LocationDAO locationDAO = new LocationDAO();
		List<getLocationDTO> provinceList = locationDAO.getLocations(null);
		String[] provinceItems = new String[provinceList.size()];
		for (int i = 0; i < provinceList.size(); i++) {
			provinceItems[i] = provinceList.get(i).getName();
		}

		JComboBox<String> provinceBox = new JComboBox<>(provinceItems);
		provinceBox.setBounds(70 + 200, 70, 90, 25);
		add(provinceBox);

		JComboBox<String> cityBox = new JComboBox<>();
		cityBox.setBounds(170 + 200, 70, 90, 25);
		add(cityBox);

		JComboBox<String> townBox = new JComboBox<>();
		townBox.setBounds(270 + 200, 70, 90, 25);
		add(townBox);

		provinceBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				provinceId = 0;
				cityId = 0;
				townId = 0;

				if (provinceBox.getSelectedItem() != null) {
					int index = ((JComboBox) e.getSource()).getSelectedIndex();
					provinceId = provinceList.get(index).getId();

					List<getLocationDTO> cityList = locationDAO.getLocations(provinceId);
					cityBox.removeAllItems();
					for (getLocationDTO city : cityList) {
						cityBox.addItem(city.getName());
					}
				}
			}
		});

		// 시/군/구 버튼 이벤트
		cityBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				townId = 0;

				if (cityBox.getSelectedItem() != null) {
					int index = ((JComboBox) e.getSource()).getSelectedIndex();

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

		// 읍/면/동 버튼 이벤트
		townBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (townBox.getSelectedItem() != null) {
					int index = ((JComboBox) e.getSource()).getSelectedIndex();

					if (index >= 0) {
						List<getLocationDTO> townList = locationDAO.getLocations(cityId);
						townId = townList.get(index).getId();
						System.out.println(townId);
					}
				}
			}
		});
	}

	public String doValidate(InsertReservationDTO dto) {
		String startTime = dto.getStartTime();
		String endTime = dto.getEndTime();
		int parkingSpaceId = dto.getParkingSpaceId();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		if (carCode.equals("") || carCode == null) {
			return "주차 하려는 차량을 선택해주세요.";
		} else if (startTime.equals("") || startTime == null) {
			return "입차 시간을 입력해주세요.";
		} else if (endTime.equals("") || endTime == null) {
			return "출차 시간을 입력해주세요.";
		}

		Date parseStartTime;
		Date parseEndTime;
		Date nowTime = new Date();
		try {
			parseStartTime = dateFormat.parse(startTime);
			parseEndTime = dateFormat.parse(endTime);
		} catch (ParseException e) {
			return "입차시간 혹은 출차시간의 입력이 비정상적입니다.";
		}

		if (nowTime.compareTo(parseStartTime) >= 0 || nowTime.compareTo(parseEndTime) >= 0) {
			return "현재 시각보다 앞의 시각에 예약할 수 없습니다.";
		} else if (parseStartTime.compareTo(parseEndTime) >= 0) {
			return "입차 시간이 출차 시간보다 앞서야 합니다.";
		}
		return "";
	}

// 이미지
	class ParkingSpaceIdAdaptor implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			int row = table.getSelectedRow(); // 행 번호 -> list.get(i)의 i와 같음
			int col = table.getSelectedColumn();

			if (col == 4) {
				System.out.println(availableSpaceList.get(row).getImageLink());
				ImagePopUpFrame popUpFrame = new ImagePopUpFrame();
				popUpFrame.loadFile(availableSpaceList.get(row).getImageLink());
				popUpFrame.setVisible(true);
				popUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popUpFrame.setAlwaysOnTop(true); // 항상 제일 위에
				popUpFrame.setSize(800, 800); // 프레임창 사이즈
				popUpFrame.setVisible(true);
			}
			parkingSpaceId = availableSpaceList.get(row).getParkingSpaceId();
			System.out.println("name = " + (String) table.getValueAt(row, 0));
			System.out.println("row 번호 = " + row);
			System.out.println("parkingSpaceId = " + parkingSpaceId);
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
