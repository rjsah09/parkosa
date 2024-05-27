package com.parkosa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.sign.SignedAccount;

public class RegisteredCarsUI extends UI {
	
	
	public void placeComponents() {
        setLayout(null);
        
        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();
        
        JLabel nameLabel  = new JLabel(name + "님", JLabel.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setBounds(100, 10, 200, 50);
        add(nameLabel);
        
        JButton cancelButton = new JButton("뒤로가기");
        cancelButton.setBounds(40, 40, 295, 25);
        add(cancelButton);
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SignedAccount.signOut();
            	GUIController.changeUI(ui, new ParkKosaUI());
            }
        });
        
    	CarDAO carDAO = new CarDAO();
    	List <RegisteredCarDTO> registeredCars = carDAO.getRegisteredCars();
    	String[] items = new String[carDAO.getRegisteredCars().size()];
    	
    	for (int i = 0 ; i < registeredCars.size() ; i++) {
    		JTextField carList = new JTextField();
    		carList.setText(registeredCars.get(i).getCarCode()+" "+
    						registeredCars.get(i).getCarTypeName());
    		carList.setBounds(60, 100+(i*40), 240, 30);
    		add(carList);
    		JButton deleteButton = new JButton("삭제");
    		carList.setBounds(330, 100+(i*40), 40, 30);
    		add(deleteButton);
    	}
    	
        
;

        JScrollPane jsp = new JScrollPane(null,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp);
        
	}
}
