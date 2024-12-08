package org.Saga.service;

import org.Saga.dto.OrderRequestDto;
import org.Saga.entity.PurchaseOrder;
import org.Saga.event.OrderStatus;
import org.Saga.event.PaymentStatus;
import org.Saga.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto){
            return orderRepository.save(convertDtoToEntity(orderRequestDto));
    }

    public PurchaseOrder convertDtoToEntity(OrderRequestDto dto){
        PurchaseOrder order = new PurchaseOrder();
        order.setUserId(dto.getUserId());
        order.setProductId(dto.getProductId());
        order.setPrice(dto.getAmount());
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        order.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
        return order;

    }
}
