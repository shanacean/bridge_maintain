package com.dancy.maintain.service.monitor;

import com.dancy.maintain.dao.intermediate.TypeComponentDao;
import com.dancy.maintain.dao.intermediate.TypePartDao;
import com.dancy.maintain.pojo.intermediate.TypeComponent;
import com.dancy.maintain.pojo.intermediate.TypePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: maintain
 * @description: 检测内容修改逻辑
 * @author: Bangyang Shan
 * @create: 2021-07-08 23:04
 **/
@Service
public class MonitorContentServiceImpl implements MonitorContentService{
    @Autowired
    TypePartDao typePartDao;

    @Autowired
    TypeComponentDao typeComponentDao;

    @Override
    @Transactional
    public Boolean updatePartWeight(List<TypePart> parts) {
        try{
            typePartDao.saveAllAndFlush(parts);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateComponentWeight(List<TypeComponent> components) {
        try{
            typeComponentDao.saveAllAndFlush(components);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
