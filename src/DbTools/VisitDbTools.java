package DbTools;

import Entities.Doctor;
import Entities.Visit;
import Mappers.DoctorMapper;
import Mappers.VisitMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitDbTools {

    private final static String ALL_VISITS_QUERY = "SELECT visitId, doctorId, patientId, visitDate FROM visit";

    private final static String INSERT_VISIT_QUERY = "INSERT INTO visit (doctorId, patientId, visitDate) VALUES (?, ?, ?)";

    private final static String DELETE_VISIT_QUERY = "DELETE visit FROM visit where id = ?";

    private final static String UPDATE_VISIT_QUERY = "UPDATE visit SET doctorId = ?, patientId = ?, visitDate = ? where visitId = ?";


    public static List<Visit> getAllVisits() {
        List<Visit> visitList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(ALL_VISITS_QUERY);
                while(rs.next()) {
                    visitList.add(VisitMapper.mapToVisitObject(rs));
                }
            }
            catch (SQLException e) {
                System.err.println("Exception getAllVisits" + e.getMessage() + e.getStackTrace());
            }
        }
        return visitList;
    }

    public static void insertVisit(int doctorId, int patientId, Date visitDate){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(INSERT_VISIT_QUERY);
                stm.setInt(1,doctorId);
                stm.setInt(2,patientId);
                stm.setDate(3,visitDate);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception insertVisit" + e.getMessage() + e.getStackTrace());
            }
            finally {
                try{
                    stm.close();
                }catch (SQLException e) {
                    System.err.println("Nie udało się zamknąć statementu");
                }
            }
        }
    }

    public static void deleteVisit(int visitId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(DELETE_VISIT_QUERY);
                stm.setInt(1,visitId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception deleteVisit" + e.getMessage() + e.getStackTrace());
            }
            finally {
                try{
                    stm.close();
                }catch (SQLException e) {
                    System.err.println("Nie udało się zamknąć statementu");
                }
            }
        }
    }

    public static void updateVisit(int doctorId, int patientId, Date visitDate, int visitId) {
        Connection conn = ConnectionManager.getConnection();
        if (conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(UPDATE_VISIT_QUERY);
                stm.setInt(1, doctorId);
                stm.setInt(2, patientId);
                stm.setDate(3, visitDate);
                stm.setInt(4, visitId);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Exception updateVisit" + e.getMessage() + e.getStackTrace());
            } finally {
                try {
                    stm.close();
                } catch (SQLException e) {
                    System.err.println("Nie udało się zamknąć statementu");
                }
            }
        }
    }
}
