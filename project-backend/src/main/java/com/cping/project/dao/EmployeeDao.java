package com.cping.project.dao;

import com.cping.project.bean.Employee;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    //public List<User> getAllAppointment(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    //public int getAppointmentCounts(@Param("username") String username);

    public List<Employee> getAllEmployee();

    public int addEmployee(Employee employee);

    public int deleteEmployee(int id);

    public Employee getUpdateEmployee(int id);

    public int editEmployee(Employee employee);
}