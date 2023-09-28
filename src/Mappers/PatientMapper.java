package Mappers;

import Entities.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMapper {
    public static Patient mapToPatientObject(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getInt("patientId"),
                rs.getString("fName"),
                rs.getString("sName"),
                rs.getString("pesel"),
                rs.getString("address"));
    }
}
