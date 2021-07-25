package com.dancy.maintain.service.bridge;

import com.dancy.maintain.dao.bridge.BridgeTypeDao;
import com.dancy.maintain.pojo.bridge.BridgeType;
import com.dancy.maintain.service.bridge.interf.BridgeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @program: maintain
 * @description: 桥梁类型业务层
 * @author: Bangyang Shan
 * @create: 2021-07-07 19:59
 **/
@Service
public class BridgeTypeServiceImpl implements BridgeTypeService {
    @Autowired
    BridgeTypeDao bridgeTypeDao;

    @Override
    public List<BridgeType> findAll() {
        return bridgeTypeDao.findAll(Sort.by(Sort.Direction.ASC, "typeId"));
    }

    @Override
    public Page<BridgeType> findAllByPage(Integer pageNum, Integer pageSize, String typeName) {
        Pageable page = PageRequest.of(pageNum - 1, pageSize);
        Specification<BridgeType> specification = (Specification<BridgeType>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate p = criteriaBuilder.conjunction();
            p = criteriaBuilder.and(p, criteriaBuilder.like(root.get("typeName"), "%" + typeName + "%"));
            return p;
        };
        return bridgeTypeDao.findAll(specification, page);
    }
}