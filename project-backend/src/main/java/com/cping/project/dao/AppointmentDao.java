package com.cping.project.dao;

import com.cping.project.bean.Appointment;
import com.cping.project.bean.User;
import com.cping.project.bean.vo.AppointmentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDao {

    //public List<User> getAllAppointment(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    //public int getAppointmentCounts(@Param("username") String username);

    public List<Appointment> getAllAppointment();

    public int addAppointment(Appointment appointment);

    public int deleteAppointment(int appointment_id);

    public Appointment getUpdateAppointment(int appointment_id);

    public int editAppointment(Appointment appointment);

    public List<Appointment> getSearch(AppointmentVo appointmentVo);
}
