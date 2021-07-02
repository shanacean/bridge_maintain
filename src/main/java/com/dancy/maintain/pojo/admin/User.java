package com.dancy.maintain.pojo.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
//        @JsonIgnoreProperties({"users"})
        private Role role;

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
