package com.dancy.maintain.service.bridge.interf;

import com.dancy.maintain.pojo.bridge.Bridge;
import org.springframework.data.domain.Page;

public interface BridgeService {
    //添加桥梁资料卡
    Bridge bridgeCreate(Bridge bridge);

    //分页模糊查询
    Page<Bridge> findAllByPage(Integer pageNum, Integer pageSize, String bridgeName);

    //根据id删除桥梁资料
    Boolean bridgeDelete(Long bridgeId);
}
