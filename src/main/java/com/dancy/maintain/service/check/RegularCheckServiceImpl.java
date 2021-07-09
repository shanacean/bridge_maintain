package com.dancy.maintain.service.check;

import com.dancy.maintain.dao.check.RegularCheckDao;
import com.dancy.maintain.pojo.check.RegularCheck;
import com.dancy.maintain.service.check.interf.RegularCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @program: maintain
 * @description: 定期检测逻辑层
 * @author: Bangyang Shan
 * @create: 2021-07-09 11:29
 **/
@Service
public class RegularCheckServiceImpl implements RegularCheckService {
    @Autowired
    private RegularCheckDao regularCheckDao;

    @Override
    public Page<RegularCheck> findAllByPage(Integer pageNum, Integer pageSize, String bridgeName) {
        Pageable page = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.ASC, "regularId"));
        Specification<RegularCheck> specification = new Specification<>() {
            private static final long serialVersionUID = 2205335550675781581L;
            @Override
            public Predicate toPredicate(Root<RegularCheck> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p = criteriaBuilder.conjunction();
                p = criteriaBuilder.and(p, criteriaBuilder.like(root.get("bridge"), "%" + bridgeName + "%"));
                return p;
            }
        };
        return regularCheckDao.findAll(page);
    }
}
