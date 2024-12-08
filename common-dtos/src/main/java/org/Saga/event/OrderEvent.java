package org.Saga.event;

import org.Saga.dto.OrderRequestDto;

import java.util.Date;
import java.util.UUID;

public class OrderEvent implements Event{
    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }
}
