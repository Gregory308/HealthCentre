package DbTools;

import Entities.Doctor;
import Entities.Patient;
import Mappers.DoctorMapper;
import Mappers.PatientMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDbTools {

    private final static String ALL_PATIENTS_QUERY = "SELECT patientId, fName, sName, pesel, address FROM patient";

    private final static String INSERT_PATIENT_QUERY = "INSERT INTO patient (fName, sName, pesel, address) VALUES (?, ?, ?, ?)";

    private final static String DELETE_PATIENT_QUERY = "DELETE patient FROM patient where patientId = ?";

    private final static String UPDATE_PATIENT_QUERY = "UPDATE patient SET fName = ?, sName = ?, pesel = ?, address = ? where patientId = ?";


    public static List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(ALL_PATIENTS_QUERY);
                while(rs.next()) {
                    patientList.add(PatientMapper.mapToPatientObject(rs));
                }
            }
            catch (SQLException e) {
                System.err.println("Exception getAllPatients" + e.getMessage() + e.getStackTrace());
            }
        }
        return patientList;
    }

    public static void insertPatient(String fName, String sName, String pesel, String address){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(INSERT_PATIENT_QUERY);
                stm.setString(1,fName);
                stm.setString(2,sName);
                stm.setString(3,pesel);
                stm.setString(4,address);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception insertPatient" + e.getMessage() + e.getStackTrace());
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

    public static void deletePatient(int patientId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(DELETE_PATIENT_QUERY);
                stm.setInt(1,patientId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception deletePatient" + e.getMessage() + e.getStackTrace());
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

    public static void updatePatient(String fName, String sName, String pesel, String address, int patientId) {
        Connection conn = ConnectionManager.getConnection();
        if (conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(UPDATE_PATIENT_QUERY);
                stm.setString(1, fName);
                stm.setString(2, sName);
                stm.setString(3, pesel);
                stm.setString(4, address);
                stm.setInt(5, patientId);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Exception updatePatient" + e.getMessage() + e.getStackTrace());
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
