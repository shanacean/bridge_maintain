package com.dancy.maintain.service.check.interf;

import com.dancy.maintain.pojo.check.RegularCheck;
import org.springframework.data.domain.Page;

public interface RegularCheckService {
    Page<RegularCheck> findAllByPage(Integer pageNum, Integer pageSize, String regularId);
}