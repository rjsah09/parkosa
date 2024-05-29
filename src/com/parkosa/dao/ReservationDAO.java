package com.parkosa.dao;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.GetAvailableParkingSpaceDTO;
import com.parkosa.dto.InsertReservationDTO;
import com.parkosa.dto.RegisteredParkingLotDTO;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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


    //수정해야함.
    public ArrayList<GetAvailableParkingSpaceDTO> getParkingSpacelist (String startTime, String endTime, String carCode, int locationId) {
        ArrayList<GetAvailableParkingSpaceDTO> list = new ArrayList<>();
        String sql = "{call list_available_reservation(?, ?)}";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.setInt(1, locationId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);

            while (rs.next()) {
                 String parkingLotName=rs.getString("");
                 String locationName = rs.getString("");
                 String parkingSpaceDescription = rs.getString("");
                 int predictPrice = rs.getInt("");
                list.add(new GetAvailableParkingSpaceDTO(parkingLotName, locationName, parkingSpaceDescription, predictPrice));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return list;
    }
}
