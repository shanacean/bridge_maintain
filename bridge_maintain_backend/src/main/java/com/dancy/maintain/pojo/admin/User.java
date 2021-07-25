package com.dancy.maintain.pojo.admin;

import com.dancy.maintain.pojo.check.DailyCheck;
import com.dancy.maintain.pojo.check.RegularCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @program: bridge_maintain_backend
 * @description:
 * @author: Bangyang Shan
 * @create: 2021-06-19 15:49
 **/
@Entity
@Table(name = "user")
@org.hibernate.annotations.Table(appliesTo = "user", comment = "用户表")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"dailyCheckSet", "regularCheckSet"})
public class User implements Serializable {

    private static final long serialVersionUID = 169161545953478080L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    private String username;
    private String password;
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<DailyCheck> dailyCheckSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RegularCheck> regularCheckSet;

    public User(Integer userId) {
        this.id = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }
}