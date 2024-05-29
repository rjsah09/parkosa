package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.InsertReservationDTO;
import com.parkosa.dto.RegisteredReservationDTO;
import com.parkosa.sign.SignedAccount;

import oracle.jdbc.OracleTypes;

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
    
    public List<RegisteredReservationDTO> getReservations() {
    	String proc = "{ call RESERVATION_PACK.get_reservation (?,?) }";
    	List<RegisteredReservationDTO> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(proc);
            
            //변수 할당
            callableStatement.setString(2, SignedAccount.getPhoneNumber());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

            while (rs.next()) {
                String parkingLotName = rs.getString("parking_lot_name");
                String parkingSpaceDescription = rs.getString("parking_space_description");
                String startTime = dateFormat.format(rs.getDate("start_time"));
                String endTime = dateFormat.format(rs.getDate("end_time"));               
                int totalAmont = rs.getInt("total_amount");
                
                list.add(new RegisteredReservationDTO(parkingLotName, parkingSpaceDescription, startTime, endTime, totalAmont));
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
