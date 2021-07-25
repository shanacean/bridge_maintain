package com.dancy.maintain.pojo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: bridge_maintain_backend
 * @description: el-menu-item实体
 * @author: Bangyang Shan
 * @create: 2021-06-22 17:10
 **/
@Entity
@Table(name = "submenu")
@org.hibernate.annotations.Table(appliesTo = "submenu", comment = "子菜单")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubMenu implements Serializable {
    private static final long serialVersionUID = -8991873351442252637L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String path;
    private String icon;
    private Integer mid;

    @Override
    public String toString() {
        return "SubMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", mid=" + mid +
                '}';
    }
}
