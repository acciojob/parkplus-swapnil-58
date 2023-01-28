package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;
    @Autowired
    SpotRepository spotRepository;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        // //Attempt a payment of amountSent for reservationId using the given mode ("cASh", "card", or "upi")
        //        //If the amountSent is less than bill, throw "Insufficient Amount" exception,
        //        otherwise update payment attributes
        //        //If the mode contains a string other than "cash", "card", or "upi"
        //        (any character in uppercase or lowercase), throw "Payment mode not detected" exception.
        //        //Note that the reservationId always exists

        Payment payment = new Payment();
        Reservation reservation = reservationRepository2.findById(reservationId).get();
        int price = reservation.getSpot().getPricePerHour();
        int bill = reservation.getNumberOfHours()*price;
        if(amountSent < bill)
        {
//            payment.setPaymentCompleted(false);
//            payment.setReservation(reservation);
//            reservation.setPayment(payment);
//            reservationRepository2.save(reservation);
//            paymentRepository2.save(payment);
            throw new Exception("Insufficient Amount");
        }
        if(mode.equalsIgnoreCase("CASH") || mode.equalsIgnoreCase("CARD")|| mode.equalsIgnoreCase("UPI"))
        {

            // set payment attributes
            if(mode.equalsIgnoreCase("CASH"))
            {
                payment.setPaymentMode(PaymentMode.CASH);
            }
            else if (mode.equalsIgnoreCase("CARD"))
            {
                payment.setPaymentMode(PaymentMode.CARD);
            } else if (mode.equalsIgnoreCase("UPI")) {
                payment.setPaymentMode(PaymentMode.UPI);

            }
            payment.setPaymentCompleted(true);
            payment.setReservation(reservation);
            // set reservation attributes
            reservation.setPayment(payment);

            // need to free spot
            // Spot spot = reservation.getSpot();
            // spot.setOccupied(false);
            //  spotRepository.save(spot);
            reservationRepository2.save(reservation);
            // paymentRepository2.save(payment);
            return payment;
        }
        else
        {
            throw new Exception("Payment mode not detected");
        }


    }
}
