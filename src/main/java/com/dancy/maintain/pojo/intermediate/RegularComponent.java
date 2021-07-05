package com.dancy.maintain.pojo.intermediate;

import com.dancy.maintain.pojo.check.RegularCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @program: maintain
 * @description: 定期检测表和桥梁类型对应构件的中间表
 * @author: Bangyang Shan
 * @create: 2021-07-05 15:53
 **/
@Entity
@Table(name = "regular_component")
@org.hibernate.annotations.Table(appliesTo = "regular_component", comment = "定期检测表和构件值关系")
@Data
@NoArgsConstructor
public class RegularComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regular_component_id")
    private Long regularComponentId;

    @Column(name = "value")
    private Double value;

    @ManyToOne
    @JoinColumn(name = "type_component_id", referencedColumnName = "type_component_id")
    private TypeComponent typeComponent;

    @ManyToOne
    @JoinColumn(name = "regular_id", referencedColumnName = "regular_id")
    private RegularCheck regularCheck;
}