package com.dancy.maintain.dao.admin;

import com.dancy.maintain.pojo.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
