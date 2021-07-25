package com.dancy.maintain.pojo.check;

import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.pojo.bridge.Bridge;
import com.dancy.maintain.pojo.intermediate.DailyItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: maintain
 * @description: 日常检测实体
 * @author: Bangyang Shan
 * @create: 2021-07-01 15:05
 **/
@Entity
@Table(name = "daily_check")
@org.hibernate.annotations.Table(appliesTo = "daily_check", comment = "日常检测表")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"bridge", "user", "dailyItemList"})
@EntityListeners(AuditingEntityListener.class)
public class DailyCheck implements Serializable {
    private static final long serialVersionUID = 5007011556373332183L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private Long checkId;

    @Column(name = "daily_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dailyDate;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "bridge_id", referencedColumnName = "bridge_id")
    private Bridge bridge;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "dailyCheck", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"dailyCheck"})
    private List<DailyItem> dailyItemList;
}
