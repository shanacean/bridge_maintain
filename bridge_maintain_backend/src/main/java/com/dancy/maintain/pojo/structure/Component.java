package com.dancy.maintain.pojo.structure;

import com.dancy.maintain.pojo.intermediate.TypeComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @program: maintain
 * @description: 构件实体
 * @author: Bangyang Shan
 * @create: 2021-06-30 21:40
 **/
@Entity
@Table(name = "component")
@org.hibernate.annotations.Table(appliesTo = "component", comment = "构件类型")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"part", "typeComponentList"})
public class Component implements Serializable {
    private static final long serialVersionUID = -7215071215776028131L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "component_name", length = 50)
    private String componentName;

    @ManyToOne
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    private Part part;

    @OneToMany(mappedBy = "component")
    private List<TypeComponent> typeComponentList;
}
