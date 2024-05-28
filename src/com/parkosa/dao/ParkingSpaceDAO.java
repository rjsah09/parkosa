package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertParkingSpaceDTO;

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
    
    
}
