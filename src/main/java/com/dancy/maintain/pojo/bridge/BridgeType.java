package com.dancy.maintain.pojo.bridge;

import com.dancy.maintain.pojo.intermediate.TypeComponent;
import com.dancy.maintain.pojo.intermediate.TypePart;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @program: maintain
 * @description: 桥梁类型实体
 * @author: Bangyang Shan
 * @create: 2021-06-29 21:06
 **/
@Entity
@Table(name = "bridge_type")
@org.hibernate.annotations.Table(appliesTo = "bridge_type", comment = "桥梁类型表")
@Data
@NoArgsConstructor
public class BridgeType implements Serializable {
    private static final long serialVersionUID = -2109537706881498920L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;

    @OneToMany(mappedBy = "bridgeType", orphanRemoval = true)
    private Set<Bridge> bridges;

    @OneToMany(targetEntity = TypePart.class, mappedBy = "bridgeType")
    private Set<TypePart> typeParts;

    @OneToMany(targetEntity = TypeComponent.class, mappedBy = "bridgeType")
    private Set<TypeComponent> typeComponentSet;
}