package com.parkosa.dao;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertParkingLotDTO;
import com.parkosa.dto.RegisteredParkingLotDTO;
import com.parkosa.image.ImageSaver;

import oracle.jdbc.OracleTypes;

public class ParkingLotDAO {

    public int insertParkLot(InsertParkingLotDTO insertParkingLotDTO) {

        String proc = "{ ? = call parking_lot_pack.insert_parking_lot(?, ?, ?, ?, ?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);
            
            String newImageLink = ImageSaver.saveImage(insertParkingLotDTO.getImageLink());

            //변수 할당
            callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
            callableStatement.setString(2, insertParkingLotDTO.getTelNumber());
            callableStatement.setString(3, insertParkingLotDTO.getName());
            callableStatement.setString(4, insertParkingLotDTO.getAddress());
            callableStatement.setInt(5, insertParkingLotDTO.getLocationId());
            callableStatement.setString(6, newImageLink);

            callableStatement.executeUpdate();
            
            return callableStatement.getInt(1);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        
        return 0;
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
    
    public ArrayList<RegisteredParkingLotDTO> listParkingLot() {
        ArrayList<RegisteredParkingLotDTO> listParkingLot = new ArrayList<>();
        String sql = "{call parking_lot_pack.list_parking_lot(?)}";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(1);

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
