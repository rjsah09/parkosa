package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.vo.FeePolicyVO;
import com.parkosa.vo.ParkingLotVO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ParkingLotDAO {

    public void insertFeePolicy(ParkingLotVO parkingLotVO) {

        String proc = "{ call fee_policy_pack.insert_fee_policy(?, ?, ?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.setString(1, parkingLotVO.getName());
            callableStatement.setString(2, parkingLotVO.getPhoneNumber());
            callableStatement.setString(3, parkingLotVO.getAddresss());
            callableStatement.setInt(4, parkingLotVO.getAvailablePark());
            callableStatement.setInt(5, parkingLotVO.getLocationId());

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
