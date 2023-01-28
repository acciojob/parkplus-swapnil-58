package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private SpotType spotType;
    private int pricePerHour;
    private boolean occupied;

    @ManyToOne
    @JoinColumn
    private ParkingLot parkingLot;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    public Spot(){
        this.reservationList=new ArrayList<>();
        this.occupied=false;
    }
    public Spot(Integer pricePerHour){
        this.pricePerHour=pricePerHour;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
