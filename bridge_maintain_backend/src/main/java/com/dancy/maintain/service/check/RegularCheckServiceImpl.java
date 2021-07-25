package com.dancy.maintain.service.check;

import com.dancy.maintain.dao.admin.UserDao;
import com.dancy.maintain.dao.bridge.BridgeTypeDao;
import com.dancy.maintain.dao.check.RegularCheckDao;
import com.dancy.maintain.dao.intermediate.RegularComponentDao;
import com.dancy.maintain.dao.intermediate.TypeComponentDao;
import com.dancy.maintain.pojo.bridge.BridgeType;
import com.dancy.maintain.pojo.check.RegularCheck;
import com.dancy.maintain.pojo.intermediate.RegularComponent;
import com.dancy.maintain.pojo.intermediate.TypePart;
import com.dancy.maintain.service.check.interf.RegularCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private UserDao user;

    @Autowired
    private TypeComponentDao typeComponentDao;

    @Autowired
    private RegularComponentDao regularComponentDao;

    @Autowired
    private BridgeTypeDao bridgeTypeDao;

    @Override
    public Page<RegularCheck> findAllByPage(Integer pageNum, Integer pageSize, String bridgeName) {
        Pageable page = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.ASC, "regularId"));
        Specification<RegularCheck> specification = new Specification<>() {
            private static final long serialVersionUID = 2205335550675781581L;

            @Override
            public Predicate toPredicate(Root<RegularCheck> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p = criteriaBuilder.conjunction();
                p = criteriaBuilder.and(p, criteriaBuilder.like(root.get("regularId").as(String.class), "%" + bridgeName + "%"));
                return p;
            }
        };
        return regularCheckDao.findAll(page);
    }

    @Override
    public RegularCheck regularCheckCreate(RegularCheck regularCheck) {
        return regularCheckDao.saveAndFlush(regularCheck);
    }

    @Transactional
    @Modifying
    public Boolean computed(Long regularId) {
        try {
            RegularCheck check = regularCheckDao.findById(regularId).orElse(null);
            assert check != null;
            List<RegularComponent> regularComponentList = check.getRegularComponentList();
            System.out.println(regularComponentList);
            Long tc = regularComponentList.get(0).getTypeComponent().getTypeComponentId();
            Long tmp = typeComponentDao.getById(tc).getBridgeType().getTypeId();

            BridgeType bridgeType = bridgeTypeDao.getById(tmp);
            Set<TypePart> parts = bridgeType.getTypePartSet();
            double part1 = 0;
            double part2 = 0;
            double part3 = 0;
            for (TypePart part : parts) {
                if (part.getPart().getPartName().equals("桥面系")) {
                    part1 = part.getWeight();
                } else if (part.getPart().getPartName().equals("上部结构")) {
                    part2 = part.getWeight();
                } else {
                    part3 = part.getWeight();
                }
            }

            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            double sum1 = 0;
            double sum2 = 0;
            double sum3 = 0;
            double min1 = 100;
            double min2 = 100;
            double min3 = 100;

            for (RegularComponent item : regularComponentList) {
                if (item.getTypeComponent().getComponent().getPart().getPartId() == 1) {
                    num1 += 1;
                    sum1 += ( item.getValue() * (item.getTypeComponent().getWeight()));
                    min1 = Math.min(min1, (100 - item.getValue()));
                } else if (item.getTypeComponent().getComponent().getPart().getPartId() == 2) {
                    num2 += 1;
                    sum2 += ( item.getValue() * item.getTypeComponent().getWeight());
                    min2 = Math.min(min2, (100 - item.getValue()));

                } else {
                    num3 += 1;
                    sum3 += (item.getValue()) * item.getTypeComponent().getWeight();
                    min3 = Math.min(min3, (100 - item.getValue()));
                }
            }
            double bci = 100 -  (((sum1 / num1) * part1 + (sum2 / num2) * part2 + (sum3 / num3) * part3));
            double bsi = Math.min(min1, Math.min(min2, min3));
            check.setBciValue(bci);
            check.setBsiValue(bsi);
            regularCheckDao.saveAndFlush(check);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean regularComponentList(List<RegularComponent> regularComponents) {
        try {
            List<RegularComponent> regulars = regularComponentDao.saveAllAndFlush(regularComponents);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean regularDelete(Long regularId) {
        if (regularCheckDao.existsById(regularId)) {
            regularCheckDao.deleteById(regularId);
            return true;
        }
        return false;
    }
}
