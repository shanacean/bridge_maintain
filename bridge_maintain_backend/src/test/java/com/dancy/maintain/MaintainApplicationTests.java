package com.dancy.maintain;

import com.dancy.maintain.dao.bridge.BridgeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MaintainApplicationTests {
    @Autowired
    BridgeDao bridgeDao;

    @Test
    void contextLoads() {
        bridgeDao.deleteById((long)2);
    }

}
