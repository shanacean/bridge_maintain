package com.dancy.maintain.dao;

import com.dancy.maintain.pojo.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubMenuDao extends JpaRepository<SubMenu, Integer> {
}
