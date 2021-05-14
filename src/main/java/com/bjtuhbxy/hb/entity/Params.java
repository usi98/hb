package com.bjtuhbxy.hb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "params")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Params {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String nameZh;
    String name;
    String value1;
    String value2;
    String value3;
    String description;
}
