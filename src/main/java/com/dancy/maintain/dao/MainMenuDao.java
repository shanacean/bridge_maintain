package com.dancy.maintain.dao;

import com.dancy.maintain.pojo.MainMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainMenuDao extends JpaRepository<MainMenu, Integer> {
}
