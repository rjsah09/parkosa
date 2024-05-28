package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.vo.ParkingSpaceVO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ParkingSpaceDAO {
    public void insertFeePolicy(ParkingSpaceVO parkingSpaceVO) {

        String proc = "{ call parking_space_pack.insert_parking_space(?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.setInt(1, parkingSpaceVO.getParkingLotId());
            callableStatement.setString(2, parkingSpaceVO.getDescription());
            callableStatement.setInt(3, parkingSpaceVO.getFeePolicyId());
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
