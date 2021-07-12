package com.dancy.maintain.pojo.intermediate;

import com.dancy.maintain.pojo.bridge.BridgeType;
import com.dancy.maintain.pojo.structure.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"bridgeType", "component", "regularComponentSet"})
public class TypeComponent implements Serializable {
    private static final long serialVersionUID = -7816276069038713525L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_component_id")
    private Long typeComponentId;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    @JsonIgnoreProperties({"bridgeSet", "typePartSet", "typeComponentList"})
    private BridgeType bridgeType;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "component_id")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"typeComponentList"})
    private Component component;

    @OneToMany(mappedBy = "typeComponent")
    @JsonIgnore
    private Set<RegularComponent> regularComponentSet;

    public TypeComponent(Long typeComponentId) {
        this.typeComponentId = typeComponentId;
    }

    @Override
    public String toString() {
        return "TypeComponent{" +
                "typeComponentId=" + typeComponentId +
                ", weight=" + weight +
                '}';
    }
}