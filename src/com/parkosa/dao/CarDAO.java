package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.vo.CarVO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CarDAO {
        public void insertCar(CarVO CarVO){
            String proc = "{ call CAR_PACK.CAR_INSERT(?,?,?) }";

            try{
                Connection conn = DBConnection.getConnection();
                CallableStatement callableStatement = conn.prepareCall(proc);

                callableStatement.setString(1, CarVO.getCode());
                callableStatement.setString(2, CarVO.getAccountPhoneNumber());
                callableStatement.setInt(3, CarVO.getCarTypeId());

                callableStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

