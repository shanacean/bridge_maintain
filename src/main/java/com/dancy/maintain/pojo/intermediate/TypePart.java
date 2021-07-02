package com.dancy.maintain.pojo.intermediate;

import com.dancy.maintain.pojo.bridge.BridgeType;
import com.dancy.maintain.pojo.structure.Part;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: maintain
 * @description: 桥梁类型和部位中间表
 * @author: Bangyang Shan
 * @create: 2021-07-01 14:16
 **/
@Entity
@Table(name = "bridgetype_part")
@org.hibernate.annotations.Table(appliesTo = "bridgetype_part", comment = "类型和部位的关系")
@Data
@NoArgsConstructor
public class TypePart implements Serializable {
    private static final long serialVersionUID = 4284338484080670446L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_part_id")
    private Long typePartId;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(targetEntity = BridgeType.class)
    @JoinColumn(name = "type_Id", referencedColumnName = "type_Id")
    private BridgeType bridgeType;

    @ManyToOne(targetEntity = Part.class)
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    private Part part;
}
