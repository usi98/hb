package com.bjtuhbxy.hb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "meter")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String address;
    String info;

}
