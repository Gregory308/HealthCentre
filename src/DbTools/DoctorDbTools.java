package DbTools;

import Entities.Doctor;
import Mappers.DoctorMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDbTools {

    private final static String ALL_DOCTORS_QUERY = "SELECT id, fName, sName, specialization FROM doctor";

    private final static String INSERT_DOCTOR_QUERY = "INSERT INTO doctor (fName, sName, specialization) VALUES (?, ?, ?)";

    private final static String DELETE_DOCTOR_QUERY = "DELETE doctor FROM doctor where id = ?";

    private final static String UPDATE_DOCTOR_QUERY = "UPDATE doctor SET fName = ?, sName = ?, specialization = ? where id = ?";


    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(ALL_DOCTORS_QUERY);
                while(rs.next()) {
                    doctorList.add(DoctorMapper.mapToDoctorObject(rs));
                }
            }
            catch (SQLException e) {
                System.err.println("Exception getAllDoctors" + e.getMessage() + e.getStackTrace());
            }
        }
        return doctorList;
    }

    public static void insertDoctor(String fName, String sName, String specialization){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(INSERT_DOCTOR_QUERY);
                stm.setString(1,fName);
                stm.setString(2,sName);
                stm.setString(3,specialization);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception insertDoctor" + e.getMessage() + e.getStackTrace());
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

    public static void deleteDoctor(int id){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(DELETE_DOCTOR_QUERY);
                stm.setInt(1,id);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception deleteDoctor" + e.getMessage() + e.getStackTrace());
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

    public static void updateDoctor(String fName, String sName, String specialization, int id) {
        Connection conn = ConnectionManager.getConnection();
        if (conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(UPDATE_DOCTOR_QUERY);
                stm.setString(1, fName);
                stm.setString(2, sName);
                stm.setString(3, specialization);
                stm.setInt(4, id);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Exception updateDoctor" + e.getMessage() + e.getStackTrace());
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
