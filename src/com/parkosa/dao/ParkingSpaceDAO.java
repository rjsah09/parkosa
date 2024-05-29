package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertParkingSpaceDTO;
import com.parkosa.dto.RegisteredParkingSpaceDTO;

import oracle.jdbc.OracleTypes;

public class ParkingSpaceDAO {
    public int insertFeePolicy(InsertParkingSpaceDTO insertParkingSpaceDTO) {

        String proc = "{ call parking_space_pack.insert_parking_space(?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.setInt(1, insertParkingSpaceDTO.getParkingLotId());
            callableStatement.setString(2, insertParkingSpaceDTO.getDescription());
            callableStatement.setInt(3, insertParkingSpaceDTO.getFeePolicyId());
            callableStatement.executeUpdate();
            
            return 1;
            
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return 0;
    }
    
    public ArrayList<RegisteredParkingSpaceDTO> listParkingSpace(int parkingLotId) {
        ArrayList<RegisteredParkingSpaceDTO> listParkingSpace = new ArrayList<>();
        String sql = "{call parking_space_pack.get_parking_space_info(?, ?)}";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.setInt(1, parkingLotId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);

            while (rs.next()) {
                String name = rs.getString("car_type_name");
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
