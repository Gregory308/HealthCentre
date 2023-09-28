package Entities;

import java.util.Date;

public class Visit {
    //na wizytę- id, lekarzId, pacjentId, data

    int visitId;
    int doctorId;
    int patientId;
    Date dateVisit;

    public Visit(int doctorId, int patientId, Date dateVisit) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateVisit = dateVisit;
    }

    public Visit(int visitId, int doctorId, int patientId, Date dateVisit) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateVisit = dateVisit;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", dateVisit=" + dateVisit +
                '}';
    }
}
