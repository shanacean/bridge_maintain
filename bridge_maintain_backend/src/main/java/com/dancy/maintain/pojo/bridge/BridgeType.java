package com.dancy.maintain.pojo.bridge;

import com.dancy.maintain.pojo.intermediate.TypeComponent;
import com.dancy.maintain.pojo.intermediate.TypePart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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
@EqualsAndHashCode(exclude = {"bridgeSet", "typePartSet", "typeComponentList"})
@JsonIgnoreProperties({"bridgeSet"})
public class BridgeType implements Serializable {
    private static final long serialVersionUID = -2109537706881498920L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;

    @OneToMany(mappedBy = "bridgeType", orphanRemoval = true)
    @JsonIgnore
    private Set<Bridge> bridgeSet;

    @OneToMany(targetEntity = TypePart.class, mappedBy = "bridgeType", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Set<TypePart> typePartSet;

    @OneToMany(targetEntity = TypeComponent.class, mappedBy = "bridgeType", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Set<TypeComponent> typeComponentList;

    public BridgeType(Long typeId) {
        this.typeId = typeId;
    }
}