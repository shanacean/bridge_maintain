package com.dancy.maintain.service.admin;

import com.dancy.maintain.dao.admin.RoleDao;
import com.dancy.maintain.dao.admin.UserDao;
import com.dancy.maintain.pojo.admin.Role;
import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.service.admin.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @program: bridge_maintain_backend
 * @description:
 * @author: Bangyang Shan
 * @create: 2021-06-21 15:09
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public User login(User user) {
        User userDB = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println(userDB);
        if (userDB != null)
            return userDB;
        throw new RuntimeException("登录失败");
    }


    @Override
    public Page<User> findAllByPage(Integer pageNum, Integer pageSize, String username) {
        Pageable pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));

        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<Object> objectPath = root.get("username");
            return criteriaBuilder.like(objectPath.as(String.class), username + "%");
        };
        return userDao.findAll(specification, pageRequest);
    }

    @Override
    public User userCreate(User user) {
        if (!user.getState()) user.setState(Boolean.FALSE);
        return userDao.saveAndFlush(user);
    }

    @Transactional
    @Override
    public int userStateUpdate(Integer id, Boolean state) {
        return userDao.updateUserState(id, state);
    }

    @Override
    public Boolean userDelete(Integer id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }
}
