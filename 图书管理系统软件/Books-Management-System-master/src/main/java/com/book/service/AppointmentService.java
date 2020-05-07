package com.book.service;

import com.book.dao.AppointmentDao;
import com.book.domain.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppointmentService {

    private AppointmentDao appointmentDao;

    @Autowired
    public void setAppointmentDao(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public ArrayList<Appointment> appointmentInfos(){
        return appointmentDao.getAllAppointment();
    }

    public boolean deleteAppointment(String name){
        return appointmentDao.deleteAppointment(name)>0;
    }

    public boolean updateAppointment(String name){
        return appointmentDao.updateAppointment(name)>0;
    }

    public boolean addAppointment(Appointment appointment){
        return appointmentDao.addAppointment(appointment)>0;
    }

    public ArrayList<Appointment> getMyAppointment(int readerId){
        return appointmentDao.getMyAppointment(readerId);
    }
}
