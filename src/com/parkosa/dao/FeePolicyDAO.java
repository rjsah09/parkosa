package com.parkosa.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertFeePolicyDTO;
import oracle.jdbc.OracleTypes;

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

    public List<InsertFeePolicyDTO> getListFeePolicy(int parkingLotId){
        ArrayList<InsertFeePolicyDTO> listFeePolicy = new ArrayList<>();
        String proc = "{call fee_policy_pack.list_fee_policy(?, ?)}";

        try{
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);
            callableStatement.setInt(1, parkingLotId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);

            while (rs.next()){
            	int id = rs.getInt("ID");
                int increaseMinute= rs.getInt("INCREASE_MINUTE");
                int increaseFee = rs.getInt("INCREASE_FEE");
                int maximumTime = rs.getInt("MAXIMUM_TIME");
                String carTypeName = rs.getString("CAR_TYPE_NAME");

                listFeePolicy.add(new InsertFeePolicyDTO(id, increaseMinute, increaseFee, maximumTime, carTypeName));
                System.out.println("feePolicyId = " + id);
            }

            return listFeePolicy;
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return new ArrayList<InsertFeePolicyDTO>();
    }
}




