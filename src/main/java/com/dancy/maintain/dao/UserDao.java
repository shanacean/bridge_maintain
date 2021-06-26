package com.dancy.maintain.dao;

import com.dancy.maintain.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByUsernameAndPassword(String username, String password);

    @Override
    Page<User> findAll(Specification<User> specification, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE user u SET u.state=?2 WHERE u.id=?1", nativeQuery = true)
    int updateUserState(Integer id, Boolean state);
}
