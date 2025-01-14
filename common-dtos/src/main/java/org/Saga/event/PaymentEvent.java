package org.Saga.event;

import org.Saga.dto.PaymentRequestDto;

import java.util.Date;
import java.util.UUID;

public class PaymentEvent implements Event{
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }


    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }
}
