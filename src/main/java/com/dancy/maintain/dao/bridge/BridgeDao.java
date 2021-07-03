package com.dancy.maintain.dao.bridge;

import com.dancy.maintain.pojo.bridge.Bridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BridgeDao extends JpaRepository<Bridge, Long>, JpaSpecificationExecutor<Bridge> {
}
