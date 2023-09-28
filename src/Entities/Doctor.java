package Entities;

public class Doctor {
    int id;
    String fName;
    String sName;
    String specialization;

    public Doctor(String fName, String sName, String specialization) {
        this.fName = fName;
        this.sName = sName;
        this.specialization = specialization;
    }

    public Doctor(int id, String fName, String sName, String specialization) {
        this.id = id;
        this.fName = fName;
        this.sName = sName;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
