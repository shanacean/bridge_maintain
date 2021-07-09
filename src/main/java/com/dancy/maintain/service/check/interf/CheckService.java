package com.dancy.maintain.service.check.interf;

import com.dancy.maintain.pojo.check.DailyCheck;
import org.springframework.data.domain.Page;

public interface CheckService {
    Boolean deleteOneByCheckId(Long checkId);
    Page<DailyCheck> findAllByPage(Integer pageNum, Integer pageSize, String checkId);
}
