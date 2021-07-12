package com.dancy.maintain.service.check.interf;

import com.dancy.maintain.pojo.check.RegularCheck;
import com.dancy.maintain.pojo.intermediate.RegularComponent;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegularCheckService {
    Page<RegularCheck> findAllByPage(Integer pageNum, Integer pageSize, String regularId);

    RegularCheck regularCheckCreate(RegularCheck regularCheck);

    Boolean regularComponentList(List<RegularComponent> regularComponents);

    Boolean regularDelete(Long regularId);
}