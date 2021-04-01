package com.cping.project.dao;

import com.cping.project.bean.Appointment;
import com.cping.project.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDao {

    //public List<User> getAllAppointment(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    //public int getAppointmentCounts(@Param("username") String username);

    public List<Appointment> getAllAppointment();

    public int addAppointment(Appointment appointment);

    public int deleteAppointment(int id);

    public Appointment getUpdateAppointment(int id);

    public int editUser(Appointment appointment);
}
