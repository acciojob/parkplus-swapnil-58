package com.driver.model;

import javax.persistence.*;
@Entity
@Table(name="resevations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberOfHours;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Spot spot;
    @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
    private Payment payment;

    public Reservation(int id, int numberOfHours, User user, Spot spot, Payment payment) {
        this.id = id;
        this.numberOfHours = numberOfHours;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
