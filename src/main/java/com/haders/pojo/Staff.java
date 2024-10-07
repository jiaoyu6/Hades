package com.haders.pojo;

import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;

@Entity
@Table(name = "staff")
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

}
