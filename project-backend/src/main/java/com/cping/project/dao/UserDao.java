package com.cping.project.dao;

import com.cping.project.bean.User;
import com.cping.project.bean.vo.LoginVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    //使用shiro
    public User getUserByMassage(@Param("username") String username);
    //public User getUserByMassage(@Param("username") String username,@Param("password") String password);

    //public User getUserByMassage(@Param("username") String username, @Param("password") String password);

    public List<User> getAllUser(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    public int getUserCounts(@Param("username") String username);

    public int updateState(Integer id, Boolean state);

    public int addUser(User user);

    public int deleteUser(int id);

    public User getUpdateUser(int id);

    public int editUser(User user);
}
