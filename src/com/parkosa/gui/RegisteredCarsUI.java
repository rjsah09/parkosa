package com.parkosa.gui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.parkosa.dao.AccountDAO;
import com.parkosa.dao.CarDAO;
import com.parkosa.dto.RegisteredCarDTO;

public class RegisteredCarsUI extends UI {
	
	
	public void placeComponents() {
        setLayout(null);
        
        AccountDAO accountDAO = new AccountDAO();
        String name = accountDAO.getName();
        
        JLabel nameLabel  = new JLabel(name + "님", JLabel.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setBounds(100, 10, 200, 50);
        add(nameLabel);
        
    	CarDAO carDAO = new CarDAO();
    	List <RegisteredCarDTO> registeredCars = carDAO.getRegisteredCars();
    	
    	for (int i = 0 ; i < registeredCars.size() ; i++) {
    		JTextField carList = new JTextField();
    		carList.setText(registeredCars.get(i).getCarCode()+" "+
    						registeredCars.get(i).getCarTypeName());
    		carList.setBounds(60, 100+(i*30), 295, 30);
    		add(carList);
    	}
    	
        
;

        JScrollPane jsp = new JScrollPane(null,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp);
        
	}
}
