package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phoneNumber;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    public User(){
        reservationList=new ArrayList<>();
    }
    public User(String name,String phoneNumber,String password){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }
}
