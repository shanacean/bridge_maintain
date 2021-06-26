package com.dancy.maintain.service;

import com.dancy.maintain.dao.MainMenuDao;
import com.dancy.maintain.pojo.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bridge_maintain_backend
 * @description: 菜单业务
 * @author: Bangyang Shan
 * @create: 2021-06-22 19:03
 **/
@Service
public class MenuService {
    @Autowired
    private MainMenuDao mainMenuDao;

    public List<MainMenu> list() {
        return mainMenuDao.findAll();
    }
}
