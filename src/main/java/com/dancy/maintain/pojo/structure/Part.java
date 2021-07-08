package com.dancy.maintain.pojo.structure;

import com.dancy.maintain.pojo.intermediate.TypePart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @program: maintain
 * @description: 桥梁部位实体：上部、下部、桥面
 * @author: Bangyang Shan
 * @create: 2021-06-30 21:34
 **/
@Entity
@Table(name = "part")
@org.hibernate.annotations.Table(appliesTo = "part", comment = "桥梁部位")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"components", "typeParts"})
public class Part implements Serializable {
    private static final long serialVersionUID = -1046116332743073815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id", nullable = false, unique = true)
    private Long partId;

    @Column(name = "part_name", nullable = false, length = 50)
    private String partName;

    @OneToMany(mappedBy = "part", orphanRemoval = true)
    private Set<Component> components;

    @OneToMany(mappedBy = "part")
    private Set<TypePart> typeParts;
}