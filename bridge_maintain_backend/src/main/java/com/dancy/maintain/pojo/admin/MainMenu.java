package com.dancy.maintain.pojo.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @program: bridge_maintain_backend
 * @description: el-submenu实体
 * @author: Bangyang Shan
 * @create: 2021-06-22 17:08
 **/
@Entity
@Table(name = "mainmenu")
@org.hibernate.annotations.Table(appliesTo = "mainmenu", comment = "主菜单表")
@Data
@NoArgsConstructor
public class MainMenu implements Serializable {
    private static final long serialVersionUID = -6308078006529536197L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;
    private String title;
    private String path;
    private String icon;

    @OneToMany(targetEntity = SubMenu.class, mappedBy = "mid", fetch = FetchType.EAGER)
    private List<SubMenu> subMenuList;

    @ManyToMany(targetEntity = Role.class, mappedBy = "mainMenus")
    @JsonIgnore
    private Set<Role> roles;
}
