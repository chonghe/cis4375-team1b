package com.cping.project.dao;

import com.cping.project.bean.Customer;
import com.cping.project.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao {

    //public List<User> getAllCustomer(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    //public int getCustomerCounts(@Param("username") String username);

    public List<Customer> getAllCustomer();

    public int addCustomer(Customer customer);

    public int deleteCustomer(int customer_id);

    public Customer getUpdateCustomer(int customer_id);

    public int editCustomer(Customer customer);

}
