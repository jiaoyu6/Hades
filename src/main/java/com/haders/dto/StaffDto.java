package com.haders.dto;

import com.haders.entity.StaffEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Data
public class StaffDto implements ExcelDto {
    private Integer id;

    private String name;

    private Integer age;


}
