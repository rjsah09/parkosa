package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.GetAvailableParkingSpaceDTO;
import com.parkosa.dto.InsertReservationDTO;

import oracle.jdbc.OracleTypes;

public class ReservationDAO {
    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();
        String startTime = "2024-04-30 08:50:00";
        String endTime = "2024-05-01 02:00:00";

        dao.getParkingSpaceList(startTime, endTime,"111나1111", 360);
    }
	//예약 생성 메서드
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

    //예약 가능한 ParkingSpace 조회 메서드
    public ArrayList<GetAvailableParkingSpaceDTO> getParkingSpaceList (String startTime, String endTime, String carCode, int locationId) {
        ArrayList<GetAvailableParkingSpaceDTO> list = new ArrayList<>();

        String sql = "{call reservation_pack.list_available_reservation(?, ?, ?, ?, ?)}";

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date startParseDate = dateFormat.parse(startTime);
            java.util.Date endParseDate = dateFormat.parse(endTime);

            // java.util.Date를 java.sql.Timestamp로 변환
            Timestamp startTimeStamp = new Timestamp(startParseDate.getTime());
            Timestamp endTimeStamp = new Timestamp(endParseDate.getTime());
            callableStatement.setTimestamp(1, startTimeStamp);
            callableStatement.setTimestamp(2, endTimeStamp);
            callableStatement.setString(3, carCode);
            callableStatement.setInt(4, locationId);
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(5);

            while (rs.next()) {
                 String parkingLotName=rs.getString("parking_lot_name");
                String locationName = rs.getString("location_name");
                 String parkingSpaceDescription = rs.getString("parking_space_description");
                 int predictPrice = rs.getInt("Predict_price");
                list.add(new GetAvailableParkingSpaceDTO(parkingLotName, locationName, parkingSpaceDescription, predictPrice));
            }
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i).getLocationName());
                System.out.println(list.get(i).getParkingLotName());
                System.out.println(list.get(i).getParkingSpaceDescription());
                System.out.println(list.get(i).getPredictPrice());
                System.out.println();
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
    
    //예약 내역 조회 메서드
}
