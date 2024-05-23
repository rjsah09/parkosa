package com.parkosa.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.parkosa.connection.DBConnection;
import com.parkosa.vo.AccountVO;


public class AccountDAO {
	
	public void insertAccount(AccountVO accountVO) {
		
		String proc = "{ call account_pack.account_insert(?, ?, ?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(proc);
			
			//변수 할당
			callableStatement.setString(1, accountVO.getPhoneNumber());
			callableStatement.setString(2, accountVO.getPassword());
			callableStatement.setString(3, accountVO.getName());
			callableStatement.setString(4, accountVO.getEMail());
			callableStatement.setDate(5, Date.valueOf(accountVO.getRegisterDate()));
			
			callableStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
	}
	
}
