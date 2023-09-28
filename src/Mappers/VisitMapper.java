package Mappers;

import Entities.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitMapper {

    public static Visit mapToVisitObject(ResultSet rs) throws SQLException {
        return new Visit(
                rs.getInt("visitId"),
                rs.getInt("doctorId"),
                rs.getInt("patientId"),
                rs.getDate("dateVisit"));
    }
}
