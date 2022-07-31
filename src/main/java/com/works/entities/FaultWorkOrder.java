package com.works.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class FaultWorkOrder extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    @NotNull
    private Integer uid;
    private String name;
    private String plate;
    private String brand;
    private String model;
    private int km;
    @NotNull
    private String fault;
    @NotNull
    private String detail;
    @NotNull
    private String situation;
    private int price;
}
