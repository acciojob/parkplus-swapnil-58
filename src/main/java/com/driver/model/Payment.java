package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean paymentCompleted;

    private PaymentMode paymentMode;

    @OneToOne(mappedBy = "reservation")
    private Reservation reservation;
    public Payment(){

    }
}
