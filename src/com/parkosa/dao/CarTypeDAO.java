package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.parkosa.connection.DBConnection;

public class CarTypeDAO {
 //수정
    public int selectCarNo(String carTypeName) {

        String function = "{ ? = call car_type_pack.fn_selectcarno(?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(function);

            //변수 할당
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, carTypeName);
            callableStatement.executeUpdate();

            int id = callableStatement.getInt(1);
            System.out.println("테스트 : "+id);
            return id;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return 0;
    }

}
