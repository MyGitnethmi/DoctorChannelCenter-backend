package com.example.doctorchannelcenter.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "appoinments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "doctorname")
    private String doctorname;

    @Column(name = "patientname")
    private String patientname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "appoinmentnumber")
    private int appoinmentnumber;

    @Column(name = "time")
    private Time time;

    @Column(name = "date")
    private Date date;


    public Appointment(String doctorname, String patientname, String phone, int appoinmentnumber, Time time, Date date) {
        this.doctorname = doctorname;
        this.patientname = patientname;
        this.phone = phone;
        this.appoinmentnumber = appoinmentnumber;
        this.time = time;
        this.date = date;
    }

    public Appointment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAppoinmentnumber() {
        return appoinmentnumber;
    }

    public void setAppoinmentnumber(int appoinmentnumber) {
        this.appoinmentnumber = appoinmentnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
