package com.driver.services.impl;

import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.driver.model.Payment;

import java.util.Locale;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        //Attempt a payment of amountSent for reservationId using the given mode ("cASh", "card", or "upi")
        //If the amountSent is less than bill, throw "Insufficient Amount" exception, otherwise update payment attributes
        //If the mode contains a string other than "cash", "card", or "upi" (any character in uppercase or lowercase), throw "Payment mode not detected" exception.
        //Note that the reservationId always exists
        Reservation reservation=reservationRepository2.findById(reservationId).get();
        if(reservation.getBill()>amountSent)
            throw new Exception("Insufficient Amount");
        String modeOfPayment=mode.toLowerCase();
        if(!reservation.getPayment().getPaymentMode().toString().equals(modeOfPayment))
            throw new Exception("Payment mode not detected");
        Payment payment=new Payment();
        payment.setReservation(reservation);
        payment.setPaymentCompleted(true);
        paymentRepository2.save(payment);
        reservation.setPayment(payment);
        return payment;
    }
}
