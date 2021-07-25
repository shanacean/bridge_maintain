package com.dancy.maintain.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.stereotype.Component;

/**
 * @program: maintain
 * @description: 数据引擎字符集
 * @author: Bangyang Shan
 * @create: 2021-06-29 22:54
 **/
@Component
public class MySQL8TableType extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
