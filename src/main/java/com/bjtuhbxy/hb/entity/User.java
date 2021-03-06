package com.bjtuhbxy.hb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String username;
    String password;
    String salt;
    String name;
    String phone;
    String email;
    Integer enabled;
    Integer buildingId;
    Integer roomId;
    String sex;
    String date;
    String major;
    String classes;

}

