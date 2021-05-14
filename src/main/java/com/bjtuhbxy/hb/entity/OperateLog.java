package com.bjtuhbxy.hb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "operate_log")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class OperateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String username;
    String description;
    String date;

}
