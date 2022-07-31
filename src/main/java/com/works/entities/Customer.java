package com.works.entities;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Customer {

    @Id
    private Integer fid;
    private Integer uid;
    private String name;
    private String plate;
    private String brand;
    private String model;
    private int km;
    private String fault;
    private String detail;
    private String situation;
    private String lastModifiedBy;
    private long lastModifiedDate;
    private int price;


}
