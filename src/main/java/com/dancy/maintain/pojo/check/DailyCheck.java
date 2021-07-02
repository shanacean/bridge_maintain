package com.dancy.maintain.pojo.check;

import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.pojo.bridge.Bridge;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @program: maintain
 * @description: 日常检测实体
 * @author: Bangyang Shan
 * @create: 2021-07-01 15:05
 **/
@Entity
@Table(name = "daily_check")
@org.hibernate.annotations.Table(appliesTo = "daily_check", comment = "日常检测表")
public class DailyCheck implements Serializable {
    private static final long serialVersionUID = 5007011556373332183L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private Long checkId;

    @Column(name = "daily_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dailyDate;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "bridge_id", referencedColumnName = "bridge_id")
    private Bridge bridge;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "dailyCheck")
    private Set<DailyItem> dailyItemSet;
}
