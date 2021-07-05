package com.dancy.maintain.pojo.intermediate;

import com.dancy.maintain.pojo.check.CheckItem;
import com.dancy.maintain.pojo.check.DailyCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: maintain
 * @description: 日常检测项和检测表的中间表
 * @author: Bangyang Shan
 * @create: 2021-07-02 15:59
 **/
@Entity
@Table(name = "daily_item")
@org.hibernate.annotations.Table(appliesTo = "daily_item", comment = "日常检测项和检测表的中间关系")
@Data
@NoArgsConstructor
public class DailyItem implements Serializable {
    private static final long serialVersionUID = 1549030862966142031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_item_id")
    private Long dailyItemId;

    @Column(name = "is_well")
    private Boolean isWell;

    @Column(name = "damage_num")
    private Integer damageNum;

    @Column(name = "damage_position", length = 50)
    private String damagePosition;

    @Column(name = "comments", length = 50)
    private String comments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_id", referencedColumnName = "check_id")
    private DailyCheck dailyCheck;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private CheckItem checkItem;
}
