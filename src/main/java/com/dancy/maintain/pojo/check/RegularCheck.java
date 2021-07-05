package com.dancy.maintain.pojo.check;

import com.dancy.maintain.pojo.admin.User;
import com.dancy.maintain.pojo.bridge.Bridge;
import com.dancy.maintain.pojo.intermediate.RegularComponent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
public class RegularCheck implements Serializable {
    private static final long serialVersionUID = 2586250927363456615L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regular_id")
    private Long regularId;

    @Column(name= "regular_date")
    private Date regularDate;

    @Column(name = "bsi_value")
    private Double bsiValue;

    @Column(name = "bci_value")
    private Double bciValue;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bridge_id", referencedColumnName = "bridge_id")
    private Bridge bridge;

    @OneToMany(mappedBy = "regularCheck")
    private Set<RegularComponent> regularComponentSet;
}
