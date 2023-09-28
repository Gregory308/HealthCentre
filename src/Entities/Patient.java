package Entities;

public class Patient {
    //id, imię, nazwisko, pesel, miejscowość

    int patientId;
    String fName;
    String sName;
    String pesel;
    String address;

    public Patient(int patientId, String fName, String sName, String pesel, String address) {
        this.patientId = patientId;
        this.fName = fName;
        this.sName = sName;
        this.pesel = pesel;
        this.address = address;
    }

    public Patient(String fName, String sName, String pesel, String address) {
        this.fName = fName;
        this.sName = sName;
        this.pesel = pesel;
        this.address = address;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
