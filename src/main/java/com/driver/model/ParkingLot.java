package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Spot> spotList;

    public ParkingLot(){
        this.spotList=new ArrayList<>();
    }
    public ParkingLot(String name,String address){
        this.name=name;
        this.address=address;
    }

}
