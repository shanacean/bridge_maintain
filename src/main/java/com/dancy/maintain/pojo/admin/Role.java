package com.dancy.maintain.pojo.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @program: maintain
 * @description: 角色表
 * @author: Bangyang Shan
 * @create: 2021-06-30 11:28
 **/
@Entity
@Table(name = "role")
@org.hibernate.annotations.Table(appliesTo = "role", comment = "角色表")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "mainMenus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements Serializable {
    private static final long serialVersionUID = -1417771534236830363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;


    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<User> users;

    @ManyToMany(targetEntity =MainMenu.class, fetch = FetchType.EAGER)
    @JoinTable(name = "role_menu", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "mid")})
    private Set<MainMenu> mainMenus;
}