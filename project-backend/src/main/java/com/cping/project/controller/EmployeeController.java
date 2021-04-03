package com.cping.project.controller;

import com.cping.project.bean.Employee;
import com.cping.project.common.Result;
import com.cping.project.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

 /*   @PostMapping("/employee")
    public Result insertAddAppointment(@RequestBody Employee employee) {
        int i = employeeDao.addEmployee(employee);
        System.out.println(i);
        return Result.succ(200, "success", null);
    }*/

    @GetMapping("allEmployee")
    public Result getAllEmployee() {
        List<Employee> allEmployee = employeeDao.getAllEmployee();
        return Result.succ(allEmployee);
    }
}