package com.parkosa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.parkosa.connection.DBConnection;
import com.parkosa.dto.RegisteredCarDTO;
import com.parkosa.sign.SignedAccount;
import com.parkosa.vo.CarTypeVO;
import com.parkosa.vo.CarVO;

import oracle.jdbc.OracleTypes;

public class CarDAO {
	public void insertCar(CarVO CarVO) {
		String proc = "{ call car_pack.car_insert(?,?,?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(proc);

			callableStatement.setString(1, CarVO.getCode());
			callableStatement.setString(2, CarVO.getAccountPhoneNumber());
			callableStatement.setInt(3, CarVO.getCarTypeId());

			callableStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkCarCodeDuplicated(String code) {

		String function = "{ ? = call car_pack.fn_code_check(?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(function);

			// 변수 할당
			callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			callableStatement.setString(2, code);
			callableStatement.executeUpdate();

			int duplicated = callableStatement.getInt(1);
			System.out.println(duplicated);

			boolean result = duplicated == 1 ? true : false;

			return result;
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return true;
	}

	public List<RegisteredCarDTO> getRegisteredCars() {
		List <RegisteredCarDTO> registeredCars = new ArrayList<>();
        String sql = "{ call car_pack.user_car_data_list(?, ?) }";
        
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            //변수 할당
            callableStatement.setString(1, SignedAccount.getPhoneNumber());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);
            
            while(rs.next()){
                String carCode = rs.getString("car_code");
                String carTypeName = rs.getString("car_type_name");
                System.out.println(carCode + ", " + carTypeName);
                registeredCars.add(new RegisteredCarDTO(carCode, carTypeName));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return registeredCars;
	}

}
