package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        //Reserve a spot in the given parkingLot such that the total price is minimum. Note that the price per hour for each spot is different
        //Note that the vehicle can only be parked in a spot having a type equal to or larger than given vehicle
        //If parkingLot is not found, user is not found, or no spot is available, throw "Cannot make reservation" exception.
        User user=userRepository3.findById(userId).get();
        ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
        List<Spot> spotList=parkingLot.getSpotList();
        int reservationAmount=Integer.MAX_VALUE;

        Reservation reservation=new Reservation();

        if(!parkingLotRepository3.existsById(parkingLotId)){
            throw new Exception("Cannot make reservation");
        }
        if(!userRepository3.existsById(userId)){
            throw new Exception("Cannot make reservation");
        }
        boolean spotAvailable=false;
        Spot spot1=new Spot();
        for(Spot spot:spotList){
            int fair=spot.getPricePerHour()*timeInHours;
            if(fair<reservationAmount && isParkPossible(spot,numberOfWheels)){
                spot1=spot;
                reservationAmount=fair;
                spotAvailable=true;
            }
        }
        if(!spotAvailable){
            throw new Exception("Cannot make reservation");
        }

        spot1.setOccupied(true);
        reservation.setSpot(spot1);
        spot1.getReservationList().add(reservation);
        reservation.setUser(user);
        spotRepository3.save(spot1);
        reservation.setBill(reservationAmount);
        reservationRepository3.save(reservation);
        return reservation;
    }
    public boolean isParkPossible(Spot spot,Integer numberOfWheels){
        if((numberOfWheels==2 && spot.getSpotType()==SpotType.TWO_WHEELER) || (numberOfWheels==4 && spot.getSpotType()==SpotType.FOUR_WHEELER) || (numberOfWheels>4 && spot.getSpotType()==SpotType.OTHERS))
            return true;
        else
            return false;
    }
}
