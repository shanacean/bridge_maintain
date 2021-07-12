package com.dancy.maintain.dao.intermediate;

import com.dancy.maintain.pojo.intermediate.RegularComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegularComponentDao extends JpaRepository<RegularComponent, Long> {
    @Query(nativeQuery = true, value = "select * from regular_component where regular_id=?1")
    List<RegularComponent> findAllSpecial(Long id);
}
