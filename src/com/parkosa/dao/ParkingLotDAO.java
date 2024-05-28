package com.parkosa.dao;

import com.parkosa.Main;
import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertParkingLotDTO;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.dto.RegisteredParkingLotDTO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.CarTypeVO;
import com.parkosa.vo.FeePolicyVO;
import com.parkosa.vo.ParkingLotVO;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
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

    public ArrayList<RegisteredParkingLotDTO> listParkingLot(int locationId) {
        ArrayList<RegisteredParkingLotDTO> listParkingLot = new ArrayList<>();
        String sql = "{call parking_lot_pack.list_parking_lot(?, ?)}";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.setInt(1, locationId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                listParkingLot.add(new RegisteredParkingLotDTO(id, name, address));
            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return listParkingLot;
    }

}
