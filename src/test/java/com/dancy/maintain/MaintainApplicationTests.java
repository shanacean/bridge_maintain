package com.dancy.maintain;

import com.dancy.maintain.dao.admin.RoleDao;
import com.dancy.maintain.pojo.admin.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MaintainApplicationTests {
    @Autowired
    RoleDao roleDao;

    @Test
    void contextLoads() {
        List<Role> all = roleDao.findAll();
        System.out.println(all);
    }

}
