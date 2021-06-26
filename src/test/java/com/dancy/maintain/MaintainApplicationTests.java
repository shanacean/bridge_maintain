package com.dancy.maintain;

import com.dancy.maintain.dao.MainMenuDao;
import com.dancy.maintain.pojo.MainMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MaintainApplicationTests {
    @Autowired
    MainMenuDao mainMenuDao;

    @Test
    void contextLoads() {
    }

}
