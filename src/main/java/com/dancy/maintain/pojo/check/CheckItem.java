package com.dancy.maintain.pojo.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: maintain
 * @description: 日常检测项
 * @author: Bangyang Shan
 * @create: 2021-07-02 15:53
 **/
@Entity
@Table(name = "check_item")
@org.hibernate.annotations.Table(appliesTo = "check_item", comment = "日常检测项表")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckItem implements Serializable {
    private static final long serialVersionUID = 7513815746546715044L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "item_name", length = 50, nullable = false)
    private String itemName;
}
