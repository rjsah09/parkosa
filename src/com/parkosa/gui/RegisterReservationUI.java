package com.parkosa.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterReservationUI extends UI{

	
	private JTextField inYearField, inMonthField, inDayField, inHourField, inMinuteField;
	private JTextField outYearField, outMonthField, outDayField, outHourField, outMinuteField;
	private JButton decideButton;
	
	@Override
	public void placeComponents() {
		
//		//---입차시간 필드---//
//		JLabel startTimeLabel = new JLabel("입차시간!", Label.LEFT);
//        startTimeLabel.setBounds(10, 20, 400, 30);
//        startTimeLabel.setOpaque(true);
//        startTimeLabel.setBackground(Color.white);
//        add(startTimeLabel);        
//
//        //입차 년도
//        JTextField startYearField = new JTextField();
//        startYearField.setBounds(50, 20, 200, 30);
//        add(startYearField);
//        
//        JLabel startYearLabel = new JLabel("년", Label.CENTER);
//        startYearLabel.setBounds(260, 50, 30, 30);
//        startYearLabel.setOpaque(true);
//        startYearLabel.setBackground(Color.white);
//        add(startYearLabel);

	        setSize(400, 250);

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new GridLayout(5, 3, 5, 5));

	        mainPanel.add(new JLabel("입차시간"));
	        
	        inYearField = new JTextField(4);
	        mainPanel.add(inYearField);
	        mainPanel.add(new JLabel("년"));
	        inMonthField = new JTextField(2);
	        mainPanel.add(inMonthField);
	        mainPanel.add(new JLabel("월"));
	        inDayField = new JTextField(2);
	        mainPanel.add(inDayField);
	        mainPanel.add(new JLabel("일"));
	        inHourField = new JTextField(2);
	        mainPanel.add(inHourField);
	        mainPanel.add(new JLabel("시"));
	        inMinuteField = new JTextField(2);
	        mainPanel.add(inMinuteField);
	        mainPanel.add(new JLabel("분"));

	        mainPanel.add(new JLabel("출차시간"));
	        mainPanel.add(new JLabel());
	        mainPanel.add(new JLabel());

	        outYearField = new JTextField(4);
	        mainPanel.add(outYearField);
	        mainPanel.add(new JLabel("년"));
	        outMonthField = new JTextField(2);
	        mainPanel.add(outMonthField);
	        mainPanel.add(new JLabel("월"));
	        outDayField = new JTextField(2);
	        mainPanel.add(outDayField);
	        mainPanel.add(new JLabel("일"));
	        outHourField = new JTextField(2);
	        mainPanel.add(outHourField);
	        mainPanel.add(new JLabel("시"));
	        outMinuteField = new JTextField(2);
	        mainPanel.add(outMinuteField);
	        mainPanel.add(new JLabel("분"));

	        mainPanel.add(new JLabel());
	        mainPanel.add(new JLabel());
	        decideButton = new JButton("결정");
	        //decideButton.addActionListener(this);
	        mainPanel.add(decideButton);

	        add(mainPanel);
	        setVisible(true);
	}

	    /*public void actionPerformed(ActionEvent e) {
	        // '결정' 버튼 클릭 시 수행할 동작 구현
	        // 입력된 값들을 가져와서 처리할 수 있어
	        String inYear = inYearField.getText();
	        String inMonth = inMonthField.getText();
	        String inDay = inDayField.getText();
	        String inHour = inHourField.getText();
	        String inMinute = inMinuteField.getText();

	        String outYear = outYearField.getText();
	        String outMonth = outMonthField.getText();
	        String outDay = outDayField.getText();
	        String outHour = outHourField.getText();
	        String outMinute = outMinuteField.getText();

	        // 여기서 필요한 처리를 수행하면 돼
	        // 예를 들면 입력된 값을 이용하여 계산하거나 다른 작업을 수행할 수 있어
	    }*/

        

	
	

}
