package com.dancy.maintain.dao;

import com.dancy.maintain.pojo.intermediate.TypePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypePartDao extends JpaRepository<Long, TypePart> {
}