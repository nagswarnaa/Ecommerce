package org.Saga.service;

import org.Saga.dto.OrderRequestDto;
import org.Saga.entity.PurchaseOrder;
import org.Saga.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.Saga.event.OrderStatus;
import org.Saga.event.PaymentStatus;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp(){
        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1);
        orderRequestDto.setProductId(2);
        orderRequestDto.setAmount(3);
        orderRequestDto.setOrderId(121);
    }
    @Test
    @DisplayName("Testing create order service method")
    public void testCreateOrder(){

        PurchaseOrder orderToSave = new PurchaseOrder();

        orderToSave.setUserId(orderRequestDto.getUserId());
        orderToSave.setProductId(orderRequestDto.getProductId());
        orderToSave.setPrice(orderRequestDto.getAmount());
        orderToSave.setOrderStatus(OrderStatus.ORDER_CREATED);
        orderToSave.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);

        given(orderRepository.save(any(PurchaseOrder.class))).willReturn(orderToSave);

        PurchaseOrder orderCreated = orderService.createOrder(orderRequestDto);

        assertThat(orderCreated.getUserId()).isEqualTo(1);
        assertThat(orderCreated.getProductId()).isEqualTo(2);
        assertThat(orderCreated.getPrice()).isEqualTo(3);
        assertThat(orderCreated.getOrderStatus()).isEqualTo(OrderStatus.ORDER_CREATED);
        assertThat(orderCreated.getPaymentStatus()).isEqualTo(PaymentStatus.PAYMENT_COMPLETED);
    }
}
