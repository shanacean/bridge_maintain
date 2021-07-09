package com.dancy.maintain.dao.check;

import com.dancy.maintain.pojo.check.DailyCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DailyCheckDao extends JpaRepository<DailyCheck,Long>, JpaSpecificationExecutor<DailyCheck> {
    @Query(value = "select * from daily_check where check_id = ?", nativeQuery = true)
    DailyCheck findOneByCheckId(Long checkId);
}