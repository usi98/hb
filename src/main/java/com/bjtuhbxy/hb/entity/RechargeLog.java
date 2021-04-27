package com.bjtuhbxy.hb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

//getter setter equals hashcode toString
@Data
@Entity
@Table(name = "recharge_log")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class RechargeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String username;
    String name;
    int buildingId;
    int roomId;
    BigDecimal money;
    String description;
    String date;
}
