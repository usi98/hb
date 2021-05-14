package com.bjtuhbxy.hb.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "violation_log")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ViolationLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    Integer buildingId;
    Integer roomId;
    String sno;
    String description;
    String updatetime;
}
