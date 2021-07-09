package com.dancy.maintain.dao.bridge;

import com.dancy.maintain.pojo.bridge.BridgeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BridgeTypeDao extends JpaRepository<BridgeType, Long>, JpaSpecificationExecutor<BridgeType> {
}
