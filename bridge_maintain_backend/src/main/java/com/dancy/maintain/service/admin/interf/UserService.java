package com.dancy.maintain.service.admin.interf;

import com.dancy.maintain.pojo.admin.Role;
import com.dancy.maintain.pojo.admin.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User login(User user);
    User userCreate(User user);
    Page<User> findAllByPage(Integer pageNum, Integer pageSize, String username);
    int userStateUpdate(Integer id, Boolean state);
    Boolean userDelete(Integer id);
    List<Role> findAllRoles();
}
