package com.cping.project.controller;

import com.alibaba.fastjson.JSON;
import com.cping.project.bean.Appointment;
import com.cping.project.bean.User;
import com.cping.project.bean.vo.AppointmentVo;
import com.cping.project.common.Result;
import com.cping.project.dao.AppointmentDao;
import org.apache.ibatis.annotations.Param;
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

    @PostMapping("getSearch")
    public Result getValidAppointment(@RequestBody AppointmentVo appointmentVo) {
        List<Appointment> search = appointmentDao.getSearch(appointmentVo);
        return Result.succ(search);
    }

    @RequestMapping("/deleteAppointment")
    public String deleteUser(int id) {
        int i = appointmentDao.deleteAppointment(id);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/getUpdate")
    public Result getUpdateUser(int id){
        Appointment appointment = appointmentDao.getUpdateAppointment(id);
        return Result.succ(appointment);
    }

    @RequestMapping("/editAppointment")
    public String editUser(@RequestBody Appointment appointment) {
        int i = appointmentDao.editAppointment(appointment);
        return i > 0 ? "success" : "error";
    }
}
