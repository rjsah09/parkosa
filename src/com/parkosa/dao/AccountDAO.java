package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.ProfileDTO;
import com.parkosa.dto.SignInDTO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.AccountVO;

import oracle.jdbc.OracleTypes;


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
	
	public boolean checkPhoneNumberDuplicated(String phoneNumber) {

		String function = "{ ? = call account_pack.fn_number_duplicate_check(?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(function);
			
			//변수 할당
			callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			callableStatement.setString(2, phoneNumber);
			callableStatement.executeUpdate();
			
			int duplicated = callableStatement.getInt(1);
			boolean result = duplicated == 1 ? true : false;
			
			return result;
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return true;
	}
	
	public String signIn(SignInDTO signInDTO) {
		
		String function = "{ ? = call account_pack.fn_number_pw_check(?,?) }";
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(function);
			
			//변수 할당
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.setString(2, signInDTO.getPhoneNumber());
			callableStatement.setString(3, signInDTO.getPassword());
			callableStatement.executeUpdate();
			
			String phoneNumber = callableStatement.getString(1);
			
			return phoneNumber;
			
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return null;
	}
	
	public ProfileDTO getProfileDTO() {
		
//		String function = "{ ? = call account_pack.fn_get_profile(?) }";
//		
//		try {
//			Connection conn = DBConnection.getConnection();
//			CallableStatement callableStatement = conn.prepareCall(function);
//			
//			//변수 할당
//			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
//			callableStatement.setString(2, SignedAccount.getPhoneNumber());
//			callableStatement.executeUpdate();
//			
//			//ProfileDTO profileDTO = (ProfileDTO) callableStatement.getObject(1);
//			ProfileDTO profileDTO = new ProfileDTO("양건모",  "35더7236");
//			
//			
//			
//			return profileDTO;
//			
//		} catch (SQLException e) {
//			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
		
		return new ProfileDTO("양건모",  "35더7236");
	}
	
}
