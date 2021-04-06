package com.cping.project.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointment_id;
    private String customer_firstname;
    private String customer_lastname;
    private String time;
    private String date;
    private String service_type;
    private String notes;

    public Appointment() {
    }

    public Appointment(String customer_firstname, String customer_lastname, String time, String date, String service_type, String notes) {
        this.customer_firstname = customer_firstname;
        this.customer_lastname = customer_lastname;
        this.time = time;
        this.date = date;
        this.service_type = service_type;
        this.notes = notes;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getCustomer_firstname() {
        return customer_firstname;
    }

    public void setCustomer_firstname(String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }

    public String getCustomer_lastname() {
        return customer_lastname;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", customer_firstname='" + customer_firstname + '\'' +
                ", customer_lastname='" + customer_lastname + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", service_type='" + service_type + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
