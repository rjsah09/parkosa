package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.vo.AccountVO;
import com.parkosa.vo.FeePolicyVO;

import java.sql.*;

public class FeePolicyDAO {
    public void insertFeePolicy(FeePolicyVO feePolicyVO) {

        String proc = "{ call insert_fee_policy(?, ?, ?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.setInt(1, feePolicyVO.getIncreaseMinute());
            callableStatement.setInt(2, feePolicyVO.getIncreaseFee());
            callableStatement.setInt(3, feePolicyVO.getMaximumTime());
            if(feePolicyVO.getCarTypeId() == 0){
                callableStatement.setNull(4, Types.INTEGER);
            }else {
                callableStatement.setInt(4, feePolicyVO.getCarTypeId());
            }
            callableStatement.setInt(5, feePolicyVO.getParkingLotId());

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
