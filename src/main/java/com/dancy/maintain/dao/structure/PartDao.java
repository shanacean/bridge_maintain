package com.dancy.maintain.dao.structure;

import com.dancy.maintain.pojo.structure.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartDao extends JpaRepository<Long, Part> {

}
