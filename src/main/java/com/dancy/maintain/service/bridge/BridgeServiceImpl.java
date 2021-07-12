package com.dancy.maintain.service.bridge;

import com.dancy.maintain.dao.bridge.BridgeDao;
import com.dancy.maintain.pojo.bridge.Bridge;
import com.dancy.maintain.service.bridge.interf.BridgeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: maintain
 * @description: 桥梁业务层
 * @author: Bangyang Shan
 * @create: 2021-07-02 19:42
 **/
@Service
public class BridgeServiceImpl implements BridgeService {
    final BridgeDao bridgeDao;

    public BridgeServiceImpl(BridgeDao bridgeDao) {
        this.bridgeDao = bridgeDao;
    }

    @Override
    public Bridge findById(Long bridgeId) {
        return bridgeDao.findById(bridgeId).orElse(null);
    }

    @Override
    public List<Bridge> findAll() {
        return bridgeDao.findAll(Sort.by(Sort.Direction.ASC, "bridgeId"));
    }

    @Override
    public Bridge bridgeCreate(Bridge bridge) {
        return bridgeDao.saveAndFlush(bridge);
    }

    @Override
    public Page<Bridge> findAllByPage(Integer pageNum, Integer pageSize, String bridgeName) {
        Pageable page = PageRequest.of(pageNum - 1, pageSize);
        Specification<Bridge> specification = (Specification<Bridge>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("bridgeName").as(String.class), "%" + bridgeName + "%");
        return bridgeDao.findAll(specification, page);
    }

    @Override
    @Transactional
    public Boolean bridgeDelete(Long bridgeId) {
        if (bridgeDao.existsById(bridgeId)) {
            bridgeDao.deleteById(bridgeId);
            return true;
        }
        return false;
    }
}
