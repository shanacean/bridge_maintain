package com.dancy.maintain.pojo.check;

import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.pojo.bridge.Bridge;
import com.dancy.maintain.pojo.intermediate.RegularComponent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @program: maintain
 * @description: 定期检测
 * @author: Bangyang Shan
 * @create: 2021-07-05 14:03
 **/
@Entity
@Table(name = "regular_check")
@org.hibernate.annotations.Table(appliesTo = "regular_check", comment = "定期检测表")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(exclude = {"user", "bridge", "regularComponentList"})
@ToString(exclude = {"bridge"})
public class RegularCheck implements Serializable {
    private static final long serialVersionUID = 2586250927363456615L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regular_id")
    private Long regularId;

    @Column(name= "regular_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date regularDate;

    @Column(name = "bsi_value")
    private Double bsiValue;

    @Column(name = "bci_value")
    private Double bciValue;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnoreProperties({"role", "dailyCheckSet", "regularCheckSet"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "bridge_id", referencedColumnName = "bridge_id")
    @JsonIgnoreProperties({"bridgeType", "dailyCheckSet", "regularCheckSet"})
    private Bridge bridge;

    @OneToMany(mappedBy = "regularCheck", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"regularCheck"})
    private List<RegularComponent> regularComponentList;

    public RegularCheck(Long regularId) {
        this.regularId = regularId;
    }
}