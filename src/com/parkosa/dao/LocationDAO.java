package com.parkosa.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.getLocationDTO;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class LocationDAO {
	public List<getLocationDTO> getLocations(Integer upperId) {
		ArrayList<getLocationDTO> locationList = new ArrayList<>();
		String proc ="{call getLocations(?, ?)}";
		try{
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(proc);

			if(upperId == null) {
				callableStatement.setNull(1, Types.INTEGER);
			}else {
				callableStatement.setInt(1, upperId);
			}
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			while(rs.next()){
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				locationList.add(new getLocationDTO(id, name));
				System.out.println("id : "+id+"  name : "+name);
			}
			return  locationList;
		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}
}
