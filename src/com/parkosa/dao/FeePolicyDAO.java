package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertFeePolicyDTO;

public class FeePolicyDAO {
    public void insertFeePolicy(InsertFeePolicyDTO feePolicyDTO) {

        String proc = "{ call fee_policy_pack.insert_fee_policy(?, ?, ?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.setInt(1, feePolicyDTO.getIncreaseMinute());
            callableStatement.setInt(2, feePolicyDTO.getIncreaseFee());
            callableStatement.setInt(3, feePolicyDTO.getMaximumTime());
            if(feePolicyDTO.getCarTypeId() == 0){
                callableStatement.setNull(4, Types.INTEGER);
            }else {
                callableStatement.setInt(4, feePolicyDTO.getCarTypeId());
            }
            callableStatement.setInt(5, feePolicyDTO.getParkingLotId());

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
