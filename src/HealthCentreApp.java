import DbTools.ConnectionManager;
import DbTools.DoctorDbTools;
import DbTools.PatientDbTools;
import Entities.Doctor;
import Entities.Patient;
import Entities.Visit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HealthCentreApp {
    public static void main(String[] args) {
        HealthCentreApp healthCentre = new HealthCentreApp();
        Integer nextMove = -1;
        try {
            do {
                System.out.println("Podaj co chcesz zrobić: ");
                System.out.println("1 - wyświetlić listę doktorów\n2 - dodać doktora\n3 - usunąć doktora\n4 - edytować doktora\n5 - wyświetlić listę pacjentów\n6 - dodać pacjenta\n7 - usunąć pacjenta\n8 - edytować pacjenta\n0 - zakończyć działanie programu");
                nextMove = healthCentre.getUserMove();
                if(!healthCentre.newMove(nextMove)) {
                    System.err.println("Wybrano niepoprawne działanie");
                }
            } while(nextMove != 0);
        }
        finally {
            ConnectionManager.freeConnection();;
        }
    }

    private Integer getUserMove() {
        Integer retValue = -1;
        try{
            Scanner scanner = new Scanner(System.in);
            retValue = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.err.println("Podaj poprawną wartość");
        }
        return retValue;
    }

    private static boolean newMove(Integer nextMove) {
        boolean retVal = false;
        if(nextMove >= 0 && nextMove <=9) {
            retVal = true;
            switch (nextMove) {
                case 1:
                    displayDoctors(DoctorDbTools.getAllDoctors());
                    break;
                case 2:
                    insertDoctor();
                    break;
                case 3:
                    deleteDoctor();
                    break;
                case 4:
                    updateDoctor();
                    break;
                case 5:
                    displayPatients(PatientDbTools.getAllPatients());
                    break;
                case 6:
                    insertPatient();
                    break;
                case 7:
                    deletePatient();
                    break;
                case 8:
                    updatePatient();
                    break;
            }
        }
        return retVal;
    }

    private static void displayDoctors(List<Doctor> doctorList){
        System.out.println("Dostępna lista lekarzy w bazie danych to:");
        doctorList.forEach(System.out::println);
        System.out.println("----------");
        System.out.println("");
    }

    private static void insertDoctor() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                Doctor doctor = tryGetDoctor();
                DoctorDbTools.insertDoctor(doctor.getsName(), doctor.getfName(), doctor.getSpecialization());
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void deleteDoctor() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Doctor> doctorList = DoctorDbTools.getAllDoctors();
                displayDoctors(doctorList);
                List<Integer> doctorIds = doctorList.stream().map(p -> p.getId()).collect(Collectors.toList());
                Integer doctorId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!doctorIds.contains(doctorId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id osoby do usunięcia:");
                    doctorId = scanner.nextInt();
                }
                DoctorDbTools.deleteDoctor(doctorId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void updateDoctor() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Doctor> doctorList = DoctorDbTools.getAllDoctors();
                displayDoctors(doctorList);
                List<Integer> doctorIds = doctorList.stream().map(p -> p.getId()).collect(Collectors.toList());
                Integer doctorId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!doctorIds.contains(doctorId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id książki do uaktualnienia:");
                    doctorId = scanner.nextInt();
                }
                Doctor doctor = tryGetDoctor();
                DoctorDbTools.updateDoctor(doctor.getfName(), doctor.getsName(), doctor.getSpecialization(), doctorId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static Doctor tryGetDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imie:");
        String fName = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj nazwisko:");
        String sName = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj specjalizację:");
        String specialization = scanner.nextLine();
        System.out.println("----------");

        return new Doctor(fName,sName,specialization);
    }

    private static void displayPatients(List<Patient> patientList){
        System.out.println("Dostępna lista pacjentów w bazie danych to:");
        patientList.forEach(System.out::println);
        System.out.println("----------");
        System.out.println("");
    }

    private static void insertPatient() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                Patient patient = tryGetPatient();
                PatientDbTools.insertPatient(patient.getsName(), patient.getfName(), patient.getPesel(), patient.getAddress());
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void deletePatient() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Patient> patientList = PatientDbTools.getAllPatients();
                displayPatients(patientList);
                List<Integer> patientIds = patientList.stream().map(p -> p.getPatientId()).collect(Collectors.toList());
                Integer patientId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!patientIds.contains(patientId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id osoby do usunięcia:");
                    patientId = scanner.nextInt();
                }
                PatientDbTools.deletePatient(patientId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void updatePatient() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Patient> patientList = PatientDbTools.getAllPatients();
                displayPatients(patientList);
                List<Integer> patientIds = patientList.stream().map(p -> p.getPatientId()).collect(Collectors.toList());
                Integer patientId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!patientIds.contains(patientId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id książki do uaktualnienia:");
                    patientId = scanner.nextInt();
                }
                Patient patient = tryGetPatient();
                PatientDbTools.updatePatient(patient.getfName(), patient.getsName(), patient.getPesel(), patient.getAddress(), patientId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static Patient tryGetPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imie:");
        String fName = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj nazwisko:");
        String sName = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj pesel:");
        String pesel = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj adres:");
        String address = scanner.nextLine();
        System.out.println("----------");


        return new Patient(fName,sName,pesel,address);
    }

    private static void displayVisits(List<Visit> visitList){
        System.out.println("Dostępna lista wizyt w bazie danych to:");
        visitList.forEach(System.out::println);
        System.out.println("----------");
        System.out.println("");
    }
/*
    private static void insertVisit() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                Visit visit = tryGetVisit();
                PatientDbTools.insertPatient(patient.getsName(), patient.getfName(), patient.getPesel(), patient.getAddress());
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void deletePatient() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Patient> patientList = PatientDbTools.getAllPatients();
                displayPatients(patientList);
                List<Integer> patientIds = patientList.stream().map(p -> p.getPatientId()).collect(Collectors.toList());
                Integer patientId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!patientIds.contains(patientId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id osoby do usunięcia:");
                    patientId = scanner.nextInt();
                }
                PatientDbTools.deletePatient(patientId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void updatePatient() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Patient> patientList = PatientDbTools.getAllPatients();
                displayPatients(patientList);
                List<Integer> patientIds = patientList.stream().map(p -> p.getPatientId()).collect(Collectors.toList());
                Integer patientId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!patientIds.contains(patientId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id książki do uaktualnienia:");
                    patientId = scanner.nextInt();
                }
                Patient patient = tryGetPatient();
                PatientDbTools.updatePatient(patient.getfName(), patient.getsName(), patient.getPesel(), patient.getAddress(), patientId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static Visit tryGetVisit() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID doktora:");
        int doctorId = Integer.parseInt(scanner.nextLine());
        System.out.println("----------");
        System.out.println("Podaj ID pacjenta:");
        int patientId = Integer.parseInt(scanner.nextLine());
        System.out.println("----------");
        System.out.println("Podaj datę wizyty:");
        Date visitDate = assertTrue(dateFormatter.format(scanner.nextLine()));
        System.out.println("----------");


        return new Visit(doctorId,patientId,visitDate);
    }
 */
}
