package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        parkingLot.setName(name);
        parkingLot.setSpotList(new ArrayList<>());
        parkingLotRepository1.save(parkingLot);
        return parkingLot;

    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        // get parking lot of given id
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        // create spot
        Spot spot = new Spot();
        //setting spot atrribbutes perhr,occuied,parkinglot,spottype
        spot.setPricePerHour(pricePerHour);
        spot.setOccupied(false);
        spot.setParkingLot(parkingLot);

        if (numberOfWheels > 2) {

            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else if (numberOfWheels > 4) {

            spot.setSpotType(SpotType.OTHERS);
        }
        else  {

            spot.setSpotType(SpotType.TWO_WHEELER);
        }
        spot.setReservationList(new ArrayList<>());

        List<Spot> spotList = parkingLot.getSpotList();
        if (spotList == null) {
            spotList = new ArrayList<>();
        }
        spotList.add(spot);
        // set spot in parkinh lot
        parkingLot.setSpotList(spotList);
        // save parking lot
        parkingLotRepository1.save(parkingLot);
        // spotRepository1.save(spot);
        return spot;


    }
    @Override
    public void deleteSpot(int spotId) {
        // Spot spot = spotRepository1.findById(spotId).get();
        //  List<Spot> spots =
        //  ParkingLot parkingLot = spot.getParkingLot();
        //  List<Spot> spotList= parkingLot.getSpotList();
        // if(spotList.contains(spot))
        spotRepository1.deleteById(spotId);
        //  spotList.remove(spot);
        //parkingLotRepository1.save(parkingLot);
        // spotRepository1.delete(spot);


    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        // get spot from id
        //  Spot spot = spotRepository1.findById(spotId).get();
        //  ParkingLot parkingLot = spot.getParkingLot();
        Spot spot = null;
        ParkingLot parkingLot =parkingLotRepository1.findById(parkingLotId).get();
        List<Spot> spotList = parkingLot.getSpotList();
        for(Spot spot1 : spotList)
        {
            if(spot1.getId()==spotId)
            {
                spot1.setPricePerHour(pricePerHour);
                spotRepository1.save(spot1);
                spot =spot1;
                break;
            }
        }


        //  parkingLotRepository1.save(parkingLot);

        return spot;



    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);

    }
}
