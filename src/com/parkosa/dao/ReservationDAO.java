package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertReservationDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ReservationDAO {

    public void insertReservation(InsertReservationDTO insertReservationDTO) {
        String proc = "{ call RESERVATION_PACK.insert_reservation (?,?,?,?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);

            callableStatement.setDate(1, insertReservationDTO.getStartTime());
            callableStatement.setDate(2, insertReservationDTO.getEndTime());
            callableStatement.setString(3, insertReservationDTO.getCarCode());
            callableStatement.setInt(4, insertReservationDTO.getParkingLotId());

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
