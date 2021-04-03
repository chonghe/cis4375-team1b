package com.cping.project.dao;

import com.cping.project.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    //public List<User> getAllAppointment(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    //public int getAppointmentCounts(@Param("username") String username);

    public List<Employee> getAllEmployee();

/*    public int addAppointment(Appointment appointment);

    public int deleteAppointment(int id);

    public Appointment getUpdateAppointment(int id);

    public int editUser(Appointment appointment);*/
}