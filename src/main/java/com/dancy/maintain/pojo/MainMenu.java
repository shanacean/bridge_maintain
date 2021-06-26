package com.dancy.maintain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: bridge_maintain_backend
 * @description: el-submenu实体
 * @author: Bangyang Shan
 * @create: 2021-06-22 17:08
 **/
@Data
@AllArgsConstructor
@ToString
@Entity
@NoArgsConstructor
@Table(name = "mainmenu")
public class MainMenu implements Serializable {
    private static final long serialVersionUID = -6308078006529536197L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;
    private String title;
    private String path;
    private String icon;
    @OneToMany(targetEntity = SubMenu.class, mappedBy = "mid")
    private List<SubMenu> subMenuList;
}
