package com.example.projectOrderService.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_sq",sequenceName="user_sq")
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "fio")
    private String fio;

    @Column(name = "user_status")
    private String userStatus;

    @Override
    public String toString() {
        return "Users{" +
                "login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
