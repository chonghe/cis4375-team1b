package com.cping.project.controller;

import com.cping.project.bean.Appointment;
import com.cping.project.common.Result;
import com.cping.project.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AppointmentController {
    @Autowired
    AppointmentDao appointmentDao;

    @PostMapping("/appointment")
    public Result insertAddAppointment(@RequestBody Appointment appointment) {
        int i = appointmentDao.addAppointment(appointment);
        System.out.println(i);
        return Result.succ(200, "success", null);
    }

    @GetMapping("allAppointment")
    public Result getAllAppoint() {
        List<Appointment> allAppointment = appointmentDao.getAllAppointment();
        return Result.succ(allAppointment);
    }
}
