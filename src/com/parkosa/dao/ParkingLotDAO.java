package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertParkingLotDTO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.CarTypeVO;
import com.parkosa.vo.FeePolicyVO;
import com.parkosa.vo.ParkingLotVO;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAO {

    public void insertParkLot(InsertParkingLotDTO insertParkingLotDTO) {

        String proc = "{ ? = call parking_lot_pack.insert_parking_lot(?, ?, ?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            //변수 할당
            callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
            callableStatement.setString(2, insertParkingLotDTO.getTelNumber());
            callableStatement.setString(3, insertParkingLotDTO.getName());
            callableStatement.setString(4, insertParkingLotDTO.getAddress());
            callableStatement.setInt(5, insertParkingLotDTO.getLocationId());
            callableStatement.setString(6, insertParkingLotDTO.getImageLink());

            callableStatement.executeUpdate();
            
            int result = callableStatement.getInt(1);
            System.out.println("결과 = " + result);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }


  /*  //주차장리스트 조회
    public List<ParkingLotVO> parkingLotList(){
        List <ParkingLotVO> carTypeList = new ArrayList<>();
        String sql = "{ call parking_lot_pack.list_parking_lot(?, ?) }";
        int locationId = 0;
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setInt(1, locationId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(1);
            while(rs.next()){
                String name = rs.getString("name");
                String telNumber = rs.getString("telNumber");
                String address = rs.getString("telNumber");

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
    }*/



}
