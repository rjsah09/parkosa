package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertParkingSpaceDTO;
import com.parkosa.dto.RegisteredParkingSpaceDTO;

import oracle.jdbc.OracleTypes;

public class ParkingSpaceDAO {
    public void insertFeePolicy(InsertParkingSpaceDTO insertParkingSpaceDTO) {

        String proc = "{ call parking_space_pack.insert_parking_space(?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.setInt(1, insertParkingSpaceDTO.getParkingLotId());
            callableStatement.setString(2, insertParkingSpaceDTO.getDescription());
            callableStatement.setInt(3, insertParkingSpaceDTO.getFeePolicyId());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
    
    public List<RegisteredParkingSpaceDTO> getRegisteredParkingSpaces() {
		List <RegisteredParkingSpaceDTO> registeredParkingSpaces = new ArrayList<>();
        String sql = "{ call parking_space_pack.get_parking_space_info(?) }";
        
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(5);
            
            while(rs.next()){
            	String carTypeName = rs.getString("car_type_name");
                int incereaseMinute = rs.getInt("fee_policy_increase_minute");
                int increaseFee = rs.getInt("fee_policy_increase_fee");
                int maximumTime = rs.getInt("fee_policy_maximum_time");
                String description = rs.getString("parking_space_description");
                
                
                registeredParkingSpaces.add(new RegisteredParkingSpaceDTO(carTypeName, incereaseMinute, increaseFee, maximumTime, description ));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return registeredParkingSpaces;
	}
    
    public ArrayList<RegisteredParkingSpaceDTO> listParkingSpace() {
        ArrayList<RegisteredParkingSpaceDTO> listParkingSpace = new ArrayList<>();
        String sql = "{call parking_space_pack.get_parking_space_info(?)}";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(1);

            while (rs.next()) {
                String name = rs.getString("name");
                int increaseMinute = rs.getInt("increase_minute");
                int increaseFee = rs.getInt("increase_fee");
                int maximumTime = rs.getInt("maximum_time");
                String decription = rs.getString("decription");
				listParkingSpace.add(new RegisteredParkingSpaceDTO(name, increaseMinute, increaseFee, maximumTime, decription));
            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return listParkingSpace; 
    }
}
