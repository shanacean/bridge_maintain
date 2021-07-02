package com.dancy.maintain.pojo.intermediate;

import com.dancy.maintain.pojo.bridge.BridgeType;
import com.dancy.maintain.pojo.structure.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: maintain
 * @description: 桥梁类型和构件中间关系
 * @author: Bangyang Shan
 * @create: 2021-07-01 14:39
 **/
@Entity
@Table(name = "type_component")
@org.hibernate.annotations.Table(appliesTo = "type_component", comment = "桥梁类型和构件中间表")
@Data
@NoArgsConstructor
public class TypeComponent implements Serializable {
    private static final long serialVersionUID = -7816276069038713525L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeComponentId;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private BridgeType bridgeType;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "component_id")
    private Component component;
}
