package com.bjtuhbxy.hb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

//getter setter equals hashcode toString
@Data
@Entity
@Table(name = "room")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Room {
    //@Id 标注用于声明一个实体类的属性映射为数据库的主键列。该属性通常置于属性声明语句之前，可与声明语句同行，也可写在单独行上。
    //@GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：SqlServer对应identity，MySQL 对应 auto increment。
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int buildingId;
    int roomId;
    int surplus;
    int price;
    String stime;
    String etime;
    int powerMax;
    BigDecimal balance;
    String meterAddress;
    String meterInfo;
    int enable;

    @Transient
    List<User> users;



}
