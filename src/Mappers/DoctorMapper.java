package Mappers;

import Entities.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper {

    public static Doctor mapToDoctorObject(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getInt("id"),
                rs.getString("fName"),
                rs.getString("sName"),
                rs.getString("specialization"));
    }
}
