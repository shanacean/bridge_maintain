package com.dancy.maintain.pojo.bridge;

import com.dancy.maintain.pojo.check.DailyCheck;
import com.dancy.maintain.pojo.check.RegularCheck;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @program: maintain
 * @description: 桥梁实体
 * @author: Bangyang Shan
 * @create: 2021-06-29 21:04
 **/
@Entity
@Table(name = "bridge")
@org.hibernate.annotations.Table(appliesTo = "bridge", comment = "桥梁实体")
@Data
@NoArgsConstructor
@ToString(exclude = {"bridgeType", "dailyCheckSet", "regularCheckSet"})
public class Bridge implements Serializable {
    private static final long serialVersionUID = -8660893588125815150L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bridge_id", nullable = false)
    private Long bridgeId;

    @Column(name = "bridge_name", nullable = false, length = 50)
    private String bridgeName;

    @Column(name = "road_name", nullable = false, length = 50)
    private String roadName;

    @Column(name = "manage_unit", nullable = false, length = 50)
    private String manageUnit;

    @Column(name = "maintain_unit", nullable = false, length = 50)
    private String maintainUnit;

    @Column(name = "watch_unit", nullable = false, length = 50)
    private String watchUnit;

    @Column(name = "build_unit", nullable = false, length = 50)
    private String buildUnit;

    @Column(name = "build_year", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date buildYear;

    @Column(name = "maintain_type", nullable = false, length = 50)
    private String maintainType;

    @Column(name = "maintain_class", nullable = false, length = 50)
    private String maintainClass;

    @Column(name = "design_load", nullable = false, precision = 10, scale = 3)
    private Double load;

    @Column(name = "total_length", nullable = false, precision = 10, scale = 3)
    private Double length;

    @Column(name = "total_width", nullable = false, precision = 10, scale = 3)
    private Double width;

    @Column(name = "span_num", nullable = false)
    private Integer spanNum;

    @Column(name = "pier_num", nullable = false)
    private Integer pierNum;

    @Column(name = "abutment_num", nullable = false)
    private Integer abutmentNum;

    @Column(name = "road_width", nullable = false, precision = 10, scale = 3)
    private Double roadWidth;

    @Column(name = "sidewalk_width", nullable = false, precision = 10, scale = 3)
    private Double sideWalkWidth;

    @Column(name = "max_waterline", nullable = false, precision = 10, scale = 3)
    private Double maxWaterline;

    @ManyToOne(targetEntity = BridgeType.class)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private BridgeType bridgeType;

    @OneToMany(mappedBy = "bridge")
    @JsonIgnore
    private Set<DailyCheck> dailyCheckSet;

    @OneToMany(mappedBy = "bridge")
    @JsonIgnore
    private Set<RegularCheck> regularCheckSet;
}