package com.dancy.maintain.service.check;

import com.dancy.maintain.dao.check.DailyCheckDao;
import com.dancy.maintain.pojo.check.DailyCheck;
import com.dancy.maintain.service.check.interf.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private DailyCheckDao dailyCheckDao;


    @Override
    public Boolean deleteOneByCheckId(Long checkId) {
        if(dailyCheckDao.existsById(checkId)){
            dailyCheckDao.deleteById(checkId);
            return true;
        }
        return false;
    }

    @Override
    public Page<DailyCheck> findAllByPage(Integer pageNum, Integer pageSize, String checkId) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Specification<DailyCheck> specification = ((root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("checkId").as(String.class), "%" + checkId + "%"));
        return dailyCheckDao.findAll(specification,pageable);
    }
}
