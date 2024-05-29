package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertReservationDTO;

import java.sql.*;
import java.text.SimpleDateFormat;

public class ReservationDAO {
/*    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();
        String endTime = "2024-05-03 11:50:00";
        String startTime = "2024-05-03 08:50:00";



        InsertReservationDTO dto = new InsertReservationDTO(startTime, endTime,"111가1111", 1);
        dao.insertReservation(dto);
    }*/
    public void insertReservation(InsertReservationDTO insertReservationDTO) {
        String proc = "{ call RESERVATION_PACK.insert_reservation (?,?,?,?) }";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date startParseDate = dateFormat.parse(insertReservationDTO.getStartTime());
            java.util.Date endParseDate = dateFormat.parse(insertReservationDTO.getEndTime());

            // java.util.Date를 java.sql.Timestamp로 변환
            Timestamp startTime = new Timestamp(startParseDate.getTime());
            Timestamp endTime = new Timestamp(endParseDate.getTime());

            callableStatement.setTimestamp(1, startTime);
            callableStatement.setTimestamp(2, endTime);
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
