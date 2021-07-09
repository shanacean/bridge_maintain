package com.dancy.maintain.dao.check;

import com.dancy.maintain.pojo.check.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CheckItemDao extends JpaRepository<CheckItem,Long>, JpaSpecificationExecutor<CheckItem> {
}
