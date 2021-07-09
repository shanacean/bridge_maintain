package com.dancy.maintain.dao.check;

import com.dancy.maintain.pojo.check.RegularCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegularCheckDao extends JpaRepository<RegularCheck, Long>, JpaSpecificationExecutor<RegularCheck> {
}
