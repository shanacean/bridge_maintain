package com.dancy.maintain.service.bridge.interf;

import com.dancy.maintain.pojo.bridge.BridgeType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BridgeTypeService {
    List<BridgeType> findAll();
    Page<BridgeType> findAllByPage(Integer pageNum, Integer pageSize, String typeName);
}
