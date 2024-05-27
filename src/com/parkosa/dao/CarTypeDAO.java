package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.vo.CarTypeVO;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarTypeDAO {

    public static void main(String[] args) {
        CarTypeDAO dao = new CarTypeDAO();
        dao.carTypeList();

    }
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

    public List<CarTypeVO> carTypeList(){
        List <CarTypeVO> carTypeList = new ArrayList<>();
        String sql = "{ call car_type_pack.car_type_list(?) }";
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(1);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                carTypeList.add(new CarTypeVO(id, name));
                System.out.println(id+" "+name);
            }
            System.out.println();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return carTypeList;
    }
}
